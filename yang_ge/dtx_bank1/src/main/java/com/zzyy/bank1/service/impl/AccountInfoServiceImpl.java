package com.zzyy.bank1.service.impl;

import com.zzyy.bank1.dao.AccountInfoDao;
import com.zzyy.bank1.model.AccountChangeEvent;
import com.zzyy.bank1.service.AccountInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @auther zzyy
 * @create 2023-03-09 17:21
 */
@Service
@Slf4j
public class AccountInfoServiceImpl implements AccountInfoService {

    @Resource
    AccountInfoDao accountInfoDao;
    @Resource
    RocketMQTemplate rocketMQTemplate;

    @Override
    public void sendUpdateAccountBalance(AccountChangeEvent accountChangeEvent) {
        //1 AccountChangeEvent对象换成json,fastjson有安全问题，技术分享省略。。。fastjson2
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("accountChange", accountChangeEvent);
        String jsonString = jsonObject.toJSONString();

        //2 生产MQ需要的Message消息实体，和MQ数据传递对接。
        Message<String> message = MessageBuilder
                .withPayload(jsonString)
                .build();

        //3 发送一条事务消息先通知MQ，我mysql需要干活update数据了，预先通知你一声，盼回复。
        //String txProducerGroup, String destination, Message<?> message, Object arg
        rocketMQTemplate.sendMessageInTransaction("producer_group_txmsg_bank1", "topic_txmsg", message, null);
        log.info("rocketMQTemplate:{}", "rocketMQTemplate模板调用结束。。。。。");
    }

    //被MQ通知，mysql开始执行本地事务，进行写操作，扣钱
    @Override
    @Transactional
    public void doUpdateAccountBalance(AccountChangeEvent accountChangeEvent) {
        //1 幂等性判断，大于零就不执行了，error
        if (accountInfoDao.isExistTx(accountChangeEvent.getTxNo()) > 0) return;

        //2 扣减金额
        accountInfoDao.updateAccountBalance(accountChangeEvent.getAccountNo(), accountChangeEvent.getAmount() * -1);
        //3 幂等性插入，向mysql里面的de_duplication表插入唯一流水，作为幂等判断初始数据
        accountInfoDao.addTx(accountChangeEvent.getTxNo());

        //4 异常测试，不是主流业务逻辑
        if (accountChangeEvent.getAmount() == -33) {
            throw new RuntimeException("人为制造异常-33");
        }
    }
}
