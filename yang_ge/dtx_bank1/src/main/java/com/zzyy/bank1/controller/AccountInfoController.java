package com.zzyy.bank1.controller;

import com.zzyy.bank1.model.AccountChangeEvent;
import com.zzyy.bank1.service.AccountInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @auther zzyy
 * @create 2023-03-09 17:34
 * <p>
 * <p>
 * http://localhost:1111/transfer?accountNo=1&amount=10
 */
@RestController
@Slf4j
public class AccountInfoController {

    @Resource
    private AccountInfoService accountInfoService;

    @GetMapping(value = "/transfer")
    public String transfer(@RequestParam("accountNo") String accountNo, @RequestParam("amount") Double amount) {
        //创建一个事务id，作为消息内容发到mq
        String tx_no = UUID.randomUUID().toString();
        AccountChangeEvent accountChangeEvent = new AccountChangeEvent(accountNo, amount, tx_no);
        //发送消息
        accountInfoService.sendUpdateAccountBalance(accountChangeEvent);
        return "转账成功" + "\t" + tx_no;
    }
}