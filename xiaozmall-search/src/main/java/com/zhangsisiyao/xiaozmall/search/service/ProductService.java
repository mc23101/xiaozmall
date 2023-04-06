package com.zhangsisiyao.xiaozmall.search.service;

import com.zhangsisiyao.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("product")
public interface ProductService {

    @RequestMapping(value="/product/attr/info/{attrId}",method = RequestMethod.POST)
    R getAttrInfo(@PathVariable String attrId);

    @RequestMapping(value="/product/category/info/{catId}",method = RequestMethod.POST)
    R getCatalogInfo(@PathVariable String catId);


    @RequestMapping(value="/product/brand/info/{brandId}",method = RequestMethod.POST)
    R getBrandInfo(@PathVariable String brandId);
}
