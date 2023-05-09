package com.zhangsisiyao.xiaozmall.auth.feign;

import com.zhangsisiyao.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("thirdpart")
public interface ThirdPartService {

    @RequestMapping(value = "/thirdpart/sms/send/{phone}/{code}",method = RequestMethod.POST)
    R<Object> sendSms(@PathVariable String phone,@PathVariable String code);
}
