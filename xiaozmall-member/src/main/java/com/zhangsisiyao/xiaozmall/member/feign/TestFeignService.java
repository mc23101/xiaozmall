package com.zhangsisiyao.xiaozmall.member.feign;

import com.zhangsisiyao.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("coupon")
public interface TestFeignService {
    @RequestMapping("coupon/smscoupon/test")
     public R get();
}
