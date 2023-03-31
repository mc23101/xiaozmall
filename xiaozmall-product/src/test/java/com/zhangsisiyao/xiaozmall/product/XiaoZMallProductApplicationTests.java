package com.zhangsisiyao.xiaozmall.product;

import com.zhangsisiyao.xiaozmall.product.entity.BrandEntity;
import com.zhangsisiyao.xiaozmall.product.service.BrandService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class XiaoZMallProductApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;
    @Test
    public void test() throws InterruptedException {
       while (true){
           Thread.sleep(1000);
           rabbitTemplate.convertAndSend("ElasticSearch","product.spu.up","message");
       }
    }
}
