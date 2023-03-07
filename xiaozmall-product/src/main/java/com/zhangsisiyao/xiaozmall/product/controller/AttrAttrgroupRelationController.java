package com.zhangsisiyao.xiaozmall.product.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.zhangsisiyao.xiaozmall.product.entity.AttrAttrgroupRelationEntity;
import com.zhangsisiyao.xiaozmall.product.service.AttrAttrgroupRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.common.utils.R;



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
