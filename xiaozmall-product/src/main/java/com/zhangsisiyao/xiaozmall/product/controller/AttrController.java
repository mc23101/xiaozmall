package com.zhangsisiyao.xiaozmall.product.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.common.utils.R;
import com.zhangsisiyao.common.vo.product.AttrVo;
import com.zhangsisiyao.xiaozmall.product.entity.AttrEntity;
import com.zhangsisiyao.xiaozmall.product.service.AttrService;
import com.zhangsisiyao.common.vo.PageParamVo;
import com.zhangsisiyao.xiaozmall.product.vo.QueryVo.AttrQueryVo;
import io.swagger.annotations.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


/**
 * 商品属性
 *
 * @author zhangsisiyao
 * @email 2963456487@qq.com
 * @date 2023-02-17 23:30:14
 */
@RestController
@RequestMapping("product/attr")
@Api(tags = "属性操作")
@ApiSupport(order = 0)
public class AttrController {
    @Autowired
    private AttrService attrService;

    @PostMapping("/base/list/{catId}")
    @ApiOperation(value = "通过分类id获取spu属性")
    @ApiOperationSupport(order = 1)
    public R<PageUtils> baseList(@PathVariable @ApiParam(value = "分类Id",defaultValue = "225") Long catId,
                                 @RequestBody @ApiParam(value = "分页查询参数") PageParamVo params){
        PageUtils pageUtils = attrService.queryBaseAttr(catId,params);
        return new R<PageUtils>().ok().put(pageUtils);
    }

    @PostMapping("/sale/list/{catId}")
    @ApiOperation(value = "通过分类id获取sku属性")
    @ApiOperationSupport(order = 2)
    public R<PageUtils> saleList(@PathVariable @ApiParam(value = "分类Id",defaultValue = "225") Long catId,
                                 @RequestBody @ApiParam(value = "分页查询参数") PageParamVo params){
        PageUtils pageUtils = attrService.querySaleAttr(catId,params);
        return new R<PageUtils>().ok().put(pageUtils);
    }


    /**
     * 列表
     */
    @PostMapping("/list")
    @ApiOperation(value = "属性条件查询")
    @ApiOperationSupport(order = 3)
    public R<PageUtils> list(@RequestBody @ApiParam(value = "条件查询参数") AttrQueryVo params){
        System.out.println(params);
        PageUtils page = attrService.queryPage(params);
        return new R<PageUtils>().ok().put(page);
    }

    /**
     * 信息
     */
    @GetMapping("/info/{attrId}")
    @ApiOperation(value = "查询属性信息")
    @ApiOperationSupport(order = 4)
    public R<AttrVo> info(@PathVariable("attrId") @ApiParam(value = "属性id") Long attrId){
        AttrEntity attr = attrService.getById(attrId);
        AttrVo attrVo=new AttrVo();
        BeanUtils.copyProperties(attr,attrVo);
        return new R<AttrVo>().ok().put(attrVo);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增/保存属性")
    @ApiOperationSupport(order = 5)
    public R<String> save(@RequestBody @ApiParam(value = "属性信息") AttrVo attr){
        AttrEntity attrEntity=new AttrEntity();
        BeanUtils.copyProperties(attr,attrEntity);
        attrService.save(attrEntity);
        return new R<String>().ok();
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新属性信息")
    @ApiOperationSupport(order = 6)
    public R<String> update(@RequestBody @ApiParam(value = "属性信息") AttrVo attr){
        AttrEntity attrEntity=new AttrEntity();
        BeanUtils.copyProperties(attr,attrEntity);
        attrService.updateById(attrEntity);
        return new R<String>().ok();
    }


    @DeleteMapping("/delete")
    @ApiOperation("批量删除属性")
    @ApiOperationSupport(order = 8)
    public R<String> delete(@RequestBody @ApiParam(value = "属性id数组") Long[] attrIds){
        attrService.removeByIds(Arrays.asList(attrIds));
        return new R<String>().ok();
    }

}
