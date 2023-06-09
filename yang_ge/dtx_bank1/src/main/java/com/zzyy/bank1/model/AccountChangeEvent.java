package com.zzyy.bank1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @auther zzyy
 * @create 2023-03-09 17:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountChangeEvent implements Serializable {

    /**
     * 账号
     */
    private String accountNo;

    /**
     * 变动金额
     */
    private double amount;

    /**
     * 事务号
     */
    private String txNo;

}
