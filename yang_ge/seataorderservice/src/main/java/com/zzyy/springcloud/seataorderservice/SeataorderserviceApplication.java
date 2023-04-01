package com.zzyy.springcloud.seataorderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.zzyy.springcloud")
public class SeataorderserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeataorderserviceApplication.class, args);
    }

}
