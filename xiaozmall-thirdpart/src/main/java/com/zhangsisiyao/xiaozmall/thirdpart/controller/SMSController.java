package com.zhangsisiyao.xiaozmall.thirdpart.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.zhangsisiyao.common.utils.R;
import com.zhangsisiyao.xiaozmall.thirdpart.service.SMSService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("thirdpart/sms")
@Api("sms短信服务")
public class SMSController {

    @Autowired
    SMSService smsService;

    @RequestMapping("/send/{phone}/{code}")
    public R<Object> sendSms(@PathVariable String phone, @PathVariable String code) {
        return smsService.sendSms(phone,code);
    }
}
