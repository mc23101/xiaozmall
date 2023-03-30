package com.zhangsisiyao.xiaozmall.product;

import com.zhangsisiyao.xiaozmall.product.entity.BrandEntity;
import com.zhangsisiyao.xiaozmall.product.service.BrandService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class XiaoZMallProductApplicationTests {
    @Autowired
    BrandService brandService;

    @Autowired
    StringRedisTemplate redisTemplate;
    @Test
    public void test(){
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        ops.set("在这种","dawdaw");
    }
}
