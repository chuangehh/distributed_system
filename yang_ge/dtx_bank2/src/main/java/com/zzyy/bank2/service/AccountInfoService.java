package com.zzyy.bank2.service;


import com.zzyy.bank2.model.AccountChangeEvent;


public interface AccountInfoService {

    //更新账户，增加金额
    public void addAccountInfoBalance(AccountChangeEvent accountChangeEvent);

}
