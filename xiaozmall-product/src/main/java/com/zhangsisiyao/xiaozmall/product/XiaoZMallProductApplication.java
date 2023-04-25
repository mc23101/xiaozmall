package com.zhangsisiyao.xiaozmall.product;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@MapperScan("com.zhangsisiyao.xiaozmall.product.dao")
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableCaching
@EnableKnife4j
public class XiaoZMallProductApplication {
	public static void main(String[] args) {
		SpringApplication.run(XiaoZMallProductApplication.class, args);
	}

}
