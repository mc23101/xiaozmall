package com.zhangsisiyao.xiaozmall.thirdpart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class XiaoZMallThirdPartApplication {

    public static void main(String[] args) {
        SpringApplication.run(XiaoZMallThirdPartApplication.class, args);
    }

}
