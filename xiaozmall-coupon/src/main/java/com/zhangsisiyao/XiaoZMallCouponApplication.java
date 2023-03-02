package com.zhangsisiyao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@MapperScan("com.zhangsisiyao.xiaozmall.coupon.dao")
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class XiaoZMallCouponApplication {

    public static void main(String[] args) {
        SpringApplication.run(XiaoZMallCouponApplication.class, args);
    }

}
