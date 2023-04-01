# Seata 2PC 3PC

## nacos 1.4.4
* https://github.com/alibaba/nacos/releases/download/1.4.4/nacos-server-1.4.4.tar.gz
* 启动报错： java.lang.RuntimeException: [db-load-error]load jdbc.properties error
    * 解决：startup.cmd -m standalone

## nacos 1.4.4
* https://github.com/alibaba/nacos/releases/download/1.4.4/nacos-server-1.4.4.tar.gz

## 创建数据库
```shell
CREATE DATABASE `orderdb` CHARACTER SET utf8 COLLATE utf8_general_ci; 
CREATE DATABASE `storagedb` CHARACTER SET utf8 COLLATE utf8_general_ci; 
```

## 报错：can not get cluster name in registry config 'service.vgroupMapping.my_test_tx_group'

```shell
## 去NACOS 配置列表，添加配置
Data ID: service.vgroupMapping.my_test_tx_group
Group: SEATA_GROUP
配置内容: default
```


# RocketMQ消息最终一致性
## 银行转账
```shell
CREATE DATABASE `bank1`;

USE `bank1`;

DROP TABLE IF EXISTS `account_info`;

CREATE TABLE `account_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '学号',
  `account_name` varchar(100) DEFAULT NULL COMMENT '户主姓名',
  `account_no` varchar(100) DEFAULT NULL COMMENT '银行卡号',
  `account_password` varchar(100) DEFAULT NULL COMMENT '帐户密码',
  `account_balance` double NOT NULL COMMENT '账户余额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='账户信息';

insert  into `account_info`(`id`,`account_name`,`account_no`,`account_password`,`account_balance`) values (1,'z3','1','123456',994);


DROP TABLE IF EXISTS `de_duplication`;

CREATE TABLE `de_duplication` (
  `tx_no` varchar(64) COLLATE utf8_bin NOT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`tx_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;



CREATE DATABASE `bank2`;

USE `bank2`;

DROP TABLE IF EXISTS `account_info`;

CREATE TABLE `account_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '学号',
  `account_name` varchar(100) DEFAULT NULL COMMENT '户主姓名',
  `account_no` varchar(100) DEFAULT NULL COMMENT '银行卡号',
  `account_password` varchar(100) DEFAULT NULL COMMENT '帐户密码',
  `account_balance` double NOT NULL COMMENT '账户余额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='账户信息';


insert  into `account_info`(`id`,`account_name`,`account_no`,`account_password`,`account_balance`) values (1,'li4','2','123456',50);

DROP TABLE IF EXISTS `de_duplication`;

CREATE TABLE `de_duplication` (
  `tx_no` varchar(64) COLLATE utf8_bin NOT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`tx_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;
```

## 报错：org.apache.rocketmq.client.exception.MQClientException: No route info of this topic, topic_txmsg
* 解决： mqbroker.cmd -n 127.0.0.1:9876 autoCreateTopicEnable=true
  start bin\mqnamesrv.cmd
  start bin\mqbroker.cmd -c conf\broker.conf