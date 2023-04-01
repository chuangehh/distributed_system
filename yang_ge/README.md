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
