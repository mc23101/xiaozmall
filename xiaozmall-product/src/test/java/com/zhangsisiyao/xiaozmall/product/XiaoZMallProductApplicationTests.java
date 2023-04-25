package com.zhangsisiyao.xiaozmall.product;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhangsisiyao.common.vo.product.ProductVo;
import com.zhangsisiyao.xiaozmall.product.service.SpuInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class XiaoZMallProductApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    SpuInfoService spuInfoService;
    @Test
    public void test() throws InterruptedException, JsonProcessingException {
        ProductVo product = spuInfoService.getProduct(17L);
        ObjectMapper mapper=new ObjectMapper();
        System.out.println(mapper.writeValueAsString(product));

    }
}
