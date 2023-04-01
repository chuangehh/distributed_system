package com.zzyy.bank1.message;

import com.alibaba.fastjson.JSONObject;
import com.zzyy.bank1.dao.AccountInfoDao;
import com.zzyy.bank1.model.AccountChangeEvent;
import com.zzyy.bank1.service.AccountInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @auther zzyy
 * @create 2023-03-09 17:32
 * @RocketMQTransactionListener是一个生产端的消息监听器，需要配置监听的事务消息生产者组 该注解的参数值txProducerGroup，就是之前AccountInfoServiceImpl里面sendUpdateAccountBalance方法
 * 设置的生产组rocketMQTemplate.sendMessageInTransaction("producer_group_txmsg_bank1"......)
 */
@Component
@Slf4j
@RocketMQTransactionListener(txProducerGroup = "producer_group_txmsg_bank1")
public class ProducerTxmsgListener implements RocketMQLocalTransactionListener {

    @Autowired
    AccountInfoService accountInfoService;
    @Autowired
    AccountInfoDao accountInfoDao;

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
        try {
            //1 json处理
            String messageString = new String((byte[]) message.getPayload());
            JSONObject jsonObject = JSONObject.parseObject(messageString);
            String accountChangeString = jsonObject.getString("accountChange");

            //2 将accountChangeString 转换成为AccountChangeEvent消息实体
            AccountChangeEvent accountChangeEvent = JSONObject.parseObject(accountChangeString, AccountChangeEvent.class);

            //3 执行本地事务，通知mysql侧扣减转账金额,看mysql本地事务执行情况，
            // 3.1 mysql ok,commit
            // 3.2 mysql error,rollback;
            accountInfoService.doUpdateAccountBalance(accountChangeEvent);
            //4 当上一步执行成功,当返回RocketMQLocalTransactionState.COMMIT，自动向mq发送commit消息，mq将消息的状态改为可消费
            return RocketMQLocalTransactionState.COMMIT;
        } catch (Exception e) {
            e.printStackTrace();
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }

    //事务状态回查，查询bank1是否扣减金额成功，你那边数据库操作成功还是失败
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        //解析message，转成AccountChangeEvent
        String messageString = new String((byte[]) message.getPayload());
        JSONObject jsonObject = JSONObject.parseObject(messageString);
        String accountChangeString = jsonObject.getString("accountChange");

        //将accountChange（json）转成AccountChangeEvent
        AccountChangeEvent accountChangeEvent = JSONObject.parseObject(accountChangeString, AccountChangeEvent.class);

        //MQ里面的事务id
        String txNo = accountChangeEvent.getTxNo();
        int existTx = accountInfoDao.isExistTx(txNo);
        if (existTx > 0) {
            return RocketMQLocalTransactionState.COMMIT;
        } else {
            return RocketMQLocalTransactionState.UNKNOWN;
        }
    }
}

