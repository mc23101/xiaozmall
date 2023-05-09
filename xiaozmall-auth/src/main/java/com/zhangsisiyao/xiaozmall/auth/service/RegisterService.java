package com.zhangsisiyao.xiaozmall.auth.service;

import com.zhangsisiyao.common.utils.R;

public interface RegisterService {

    R<Object> sendSms(String phone);
}
