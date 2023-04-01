package com.zzyy.bank1.service;

import com.zzyy.bank1.model.AccountChangeEvent;

/**
 * @auther zzyy
 * @create 2023-03-09 17:19
 */
public interface AccountInfoService {

    /**
     * 向mq发送转账消息
     * @param accountChangeEvent
     */
    public void sendUpdateAccountBalance(AccountChangeEvent accountChangeEvent);

    /**
     * 更新账户，扣减金额
     * @param accountChangeEvent
     */
    public void doUpdateAccountBalance(AccountChangeEvent accountChangeEvent);

}
