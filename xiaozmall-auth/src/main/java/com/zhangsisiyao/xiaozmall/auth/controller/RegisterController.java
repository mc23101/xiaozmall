package com.zhangsisiyao.xiaozmall.auth.controller;

import com.zhangsisiyao.common.utils.R;
import com.zhangsisiyao.xiaozmall.auth.service.RegisterService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth/register")
@Api(value = "注册操作")
public class RegisterController {

    @Autowired
    RegisterService registerService;

    @RequestMapping ("/sendSms/{phone}")
    public R<Object> sendCode(@PathVariable String phone){
       return registerService.sendSms(phone);
    }

}
