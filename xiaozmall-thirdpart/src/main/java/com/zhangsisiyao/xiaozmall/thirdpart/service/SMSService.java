package com.zhangsisiyao.xiaozmall.thirdpart.service;

import com.zhangsisiyao.common.utils.R;

public interface SMSService {
    R<Object> sendSms(String phone, String code);
}
