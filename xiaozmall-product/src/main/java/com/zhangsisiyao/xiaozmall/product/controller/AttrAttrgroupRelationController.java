package com.zhangsisiyao.xiaozmall.product.controller;

import com.zhangsisiyao.xiaozmall.product.service.AttrAttrgroupRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



/**
 * 属性&属性分组关联
 *
 * @author zhangsisiyao
 * @email 2963456487@qq.com
 * @date 2023-02-26 09:32:23
 */
@RestController
@RequestMapping("product/attrattrgrouprelation")
public class AttrAttrgroupRelationController {
    @Autowired
    private AttrAttrgroupRelationService attrAttrgroupRelationService;
}
