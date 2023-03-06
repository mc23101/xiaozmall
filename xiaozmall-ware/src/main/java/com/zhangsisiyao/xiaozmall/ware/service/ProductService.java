package com.zhangsisiyao.xiaozmall.ware.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;

@FeignClient("product")
@Service
public interface ProductService {
}
