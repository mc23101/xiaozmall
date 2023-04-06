package com.zhangsisiyao.xiaozmall.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class XiaozmallSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(XiaozmallSearchApplication.class, args);
    }

}
