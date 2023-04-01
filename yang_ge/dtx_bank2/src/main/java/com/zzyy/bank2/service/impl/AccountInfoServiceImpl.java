package com.zzyy.bank2.service.impl;

import com.zzyy.bank2.dao.AccountInfoDao;
import com.zzyy.bank2.model.AccountChangeEvent;
import com.zzyy.bank2.service.AccountInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @auther zzyy
 * @create 2023-03-12 17:49
 */
@Service
@Slf4j
public class AccountInfoServiceImpl implements AccountInfoService {

    @Autowired
    AccountInfoDao accountInfoDao;

    //更新账户，增加金额
    @Override
    @Transactional
    public void addAccountInfoBalance(AccountChangeEvent accountChangeEvent) {
        log.info("bank2更新本地账号，账号：{},金额：{}", accountChangeEvent.getAccountNo(), accountChangeEvent.getAmount());
        //1 幂等性判断
        if (accountInfoDao.isExistTx(accountChangeEvent.getTxNo()) > 0) {
            return;
        }

        //2 收款增加金额
        accountInfoDao.updateAccountBalance(accountChangeEvent.getAccountNo(), accountChangeEvent.getAmount());
        //3 添加事务记录，用于幂等
        accountInfoDao.addTx(accountChangeEvent.getTxNo());
        //4 人为制造异常，测试，不是主要业务流程
        if (accountChangeEvent.getAmount() == -44) {
            throw new RuntimeException("人为制造异常-44" + accountChangeEvent.getTxNo());
        }
    }
}

