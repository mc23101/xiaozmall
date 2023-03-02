package com.zhangsisiyao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@MapperScan("com.zhangsisiyao.xiaozmall.member.dao")
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class XiaoZMallMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(XiaoZMallMemberApplication.class, args);
    }

}
