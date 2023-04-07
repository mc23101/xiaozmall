package com.zhangsisiyao.xiaozmall.product.config.chache;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.interceptor.KeyGenerator;

import java.lang.reflect.Method;

public class CustomKeyGenerator implements KeyGenerator {
    @Override
    public Object generate(Object target, Method method, Object... params) {
        StringBuilder key= new StringBuilder(target.getClass().getSimpleName() + method.getName());
        ObjectMapper mapper=new ObjectMapper();
        for(Object param:params){
            try {
                key.append(mapper.writeValueAsString(param));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        return key.toString();
    }
}
