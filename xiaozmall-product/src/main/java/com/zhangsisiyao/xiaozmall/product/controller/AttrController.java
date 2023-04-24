package com.zhangsisiyao.xiaozmall.product.controller;

import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.common.utils.R;
import com.zhangsisiyao.common.vo.AttrVo;
import com.zhangsisiyao.xiaozmall.product.entity.AttrEntity;
import com.zhangsisiyao.xiaozmall.product.entity.ProductAttrValueEntity;
import com.zhangsisiyao.xiaozmall.product.service.AttrService;
import com.zhangsisiyao.common.vo.AttrValueVo;
import com.zhangsisiyao.xiaozmall.product.vo.PageParamVo;
import io.swagger.annotations.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;



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
public class AttrController {
    @Autowired
    private AttrService attrService;

    @PostMapping("/base/list/{catId}")
    @ApiOperation(value = "通过分类id获取spu属性")
    public R<PageUtils> baseList(@PathVariable @ApiParam(value = "分类Id",defaultValue = "225") Long catId,
                                 @RequestBody @ApiParam(value = "分页查询参数") PageParamVo params){
        PageUtils pageUtils = attrService.queryBaseAttr(catId,params);
        return new R<PageUtils>().ok().put(pageUtils);
    }

    @PostMapping("/sale/list/{catId}")
    @ApiOperation(value = "通过分类id获取sku属性")
    public R<PageUtils> saleList(@PathVariable @ApiParam(value = "分类Id",defaultValue = "225") Long catId,
                                 @RequestBody @ApiParam(value = "分页查询参数") PageParamVo params){
        PageUtils pageUtils = attrService.querySaleAttr(catId,params);
        return new R<PageUtils>().ok().put(pageUtils);
    }

    @GetMapping("/base/listforspu/{spuId}")
    @ApiOperation(value = "通过spuId获取spu属性")
    public R<List<AttrValueVo>> baseListForSpu(@PathVariable @ApiParam(value = "spuId") Long spuId){
        List<AttrValueVo> entities = attrService.queryListForSpu(spuId);
        return new R<List<AttrValueVo>>().ok().put(entities);
    }

    /**
     * 列表
     */
    @PostMapping("/list")
    @ApiOperation(value = "分页查询属性")
    public R<PageUtils> list(@RequestBody @ApiParam(value = "分页查询参数") PageParamVo params){
        PageUtils page = attrService.queryPage(params);
        return new R<PageUtils>().ok().put(page);
    }

    /**
     * 信息
     */
    @GetMapping("/info/{attrId}")
    @ApiOperation(value = "查询属性信息")
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
    public R<String> save(@RequestBody @ApiParam(value = "属性信息") AttrVo attr){
        AttrEntity attrEntity=new AttrEntity();
        BeanUtils.copyProperties(attr,attrEntity);
		attrService.save(attrEntity);
        return new R<String>().ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "更新属性信息")
    public R<String> update(@RequestBody @ApiParam(value = "属性信息") AttrVo attr){
        AttrEntity attrEntity=new AttrEntity();
        BeanUtils.copyProperties(attr,attrEntity);
		attrService.updateById(attrEntity);
        return new R<String>().ok();
    }

    @PostMapping("/update/{spuId}")
    @ApiOperation("更新spu属性信息")
    public R<String> updateSpu(@RequestBody @ApiParam(value = "属性信息") List<AttrValueVo> attrs, @PathVariable @ApiParam(value = "spuId") String spuId){
        attrService.UpdateAttrsBySpuId(attrs,spuId);
        return new R<String>().ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @ApiOperation("批量删除属性")
    public R<String> delete(@RequestBody @ApiParam(value = "属性id数组") Long[] attrIds){
		attrService.removeByIds(Arrays.asList(attrIds));
        return new R<String>().ok();
    }

}
