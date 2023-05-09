package com.zhangsisiyao.xiaozmall.auth.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.zhangsisiyao.common.constant.AuthConstant;
import com.zhangsisiyao.common.utils.R;
import com.zhangsisiyao.xiaozmall.auth.feign.ThirdPartService;
import com.zhangsisiyao.xiaozmall.auth.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service("RegisterService")
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    ThirdPartService thirdPartService;

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public R<Object> sendSms(String phone) {
        String randCode= RandomUtil.randomNumbers(6);
        if(redisTemplate.hasKey(AuthConstant.REGISTER_SMS_CODE_PREFIX+phone)){
            String value = (String) redisTemplate.opsForValue().get(AuthConstant.REGISTER_SMS_CODE_PREFIX + phone);
            String[] split = value.split("_");
            long time=Long.parseLong(split[1]);
            if(System.currentTimeMillis()-time<=60000){
                return new R<>().error();
            }
        }
        thirdPartService.sendSms(phone,randCode);
        redisTemplate.opsForValue().set(AuthConstant.REGISTER_SMS_CODE_PREFIX+phone,randCode+"_"+ System.currentTimeMillis(),5, TimeUnit.MINUTES);
        return new R<>().ok();
    }
}
