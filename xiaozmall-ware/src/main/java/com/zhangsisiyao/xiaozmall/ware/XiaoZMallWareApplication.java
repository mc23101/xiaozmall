package com.zhangsisiyao.xiaozmall.ware;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@MapperScan("com.zhangsisiyao.xiaozmall.ware.dao")
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class XiaoZMallWareApplication {

	public static void main(String[] args) {
		SpringApplication.run(XiaoZMallWareApplication.class, args);
	}

}
