package com.zhangsisiyao.xiaozmall.thirdpart.service.impl;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import com.zhangsisiyao.common.utils.R;
import com.zhangsisiyao.xiaozmall.thirdpart.service.SMSService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service("SMSService")
public class SMSServiceImpl implements SMSService {

    @SneakyThrows
    @Override
    public R<Object> sendSms(String phone, String code) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI5tHr1M1qwDVERUiqG9NA", "UFBGItdlgu8OISc1Loyu56g0cU9Hsf");
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Dysmsapi", "dysmsapi.aliyuncs.com");
        IAcsClient client = new DefaultAcsClient(profile);


        SendSmsRequest request = new SendSmsRequest();
        request.setTemplateCode("SMS_460650753");
        request.setTemplateParam("{code:"+code+"}");
        request.setPhoneNumbers(phone);
        request.setSignName("小张商城");

        try {
            SendSmsResponse response = client.getAcsResponse(request);
            System.out.println(new Gson().toJson(response));
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            System.out.println("ErrCode:" + e.getErrCode());
            System.out.println("ErrMsg:" + e.getErrMsg());
            System.out.println("RequestId:" + e.getRequestId());
        }
        return new R<>().ok();
    }
}
