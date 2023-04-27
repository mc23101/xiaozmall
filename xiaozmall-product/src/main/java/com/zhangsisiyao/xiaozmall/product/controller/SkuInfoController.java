package com.zhangsisiyao.xiaozmall.product.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.common.utils.R;
import com.zhangsisiyao.common.vo.product.SkuInfoVo;
import com.zhangsisiyao.xiaozmall.product.entity.SkuInfoEntity;
import com.zhangsisiyao.xiaozmall.product.service.SkuInfoService;
import com.zhangsisiyao.xiaozmall.product.vo.QueryVo.SkuInfoQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


/**
 * sku信息
 *
 * @author zhangsisiyao
 * @email 2963456487@qq.com
 * @date 2023-02-17 23:30:14
 */
@RestController
@RequestMapping("product/skuinfo")
@Api(tags = "Sku操作")
@ApiSupport(order = 10)
public class SkuInfoController {
    @Autowired
    private SkuInfoService skuInfoService;

    /**
     * 列表
     */
    @PostMapping("/list")
    @ApiOperation(value = "分页搜索Sku信息")
    @ApiOperationSupport()
    public R<PageUtils> list(@RequestBody @ApiParam(value = "sku信息分页搜索参数") SkuInfoQueryVo queryVo){
        PageUtils page = skuInfoService.queryPage(queryVo);
        return new  R<PageUtils>().ok().put(page);
    }

    @PostMapping("/list/{catalog}/{brand}/{spu}")
    @ApiOperation(value = "通过分类id，品牌id，spuId查询Sku信息")
    @ApiOperationSupport(order = 1)
    public R<List<SkuInfoVo>> listWithCatalogBrandSpu (@PathVariable(value = "catalog") @ApiParam(value = "分类Id") String catalog,@PathVariable(value = "brand") @ApiParam(value = "品牌Id") String brand, @PathVariable(value = "spu") @ApiParam(value = "spuId") String spu){
        List<SkuInfoVo> entities = skuInfoService.listWithCatalogBrandSpu(catalog, brand, spu);
        return new R<List<SkuInfoVo>>().ok().put(entities);
    }


    /**
     * 信息
     */
    @PostMapping("/info/{skuId}")
    @ApiOperation(value = "查询Sku信息")
    @ApiOperationSupport(order = 2)
    public R<SkuInfoVo> info(@PathVariable("skuId") @ApiParam(value = "skuId") Long skuId){
		SkuInfoEntity skuInfo = skuInfoService.getById(skuId);
        SkuInfoVo skuInfoVo=new SkuInfoVo();
        BeanUtils.copyProperties(skuInfo,skuInfoVo);
        return new R<SkuInfoVo>().ok().put(skuInfoVo);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增Sku信息")
    @ApiOperationSupport(order = 3)
    public R save(@RequestBody @ApiParam(value = "sku信息") SkuInfoVo skuInfoVo){
        SkuInfoEntity skuInfo=new SkuInfoEntity();
        BeanUtils.copyProperties(skuInfoVo,skuInfo);
		skuInfoService.save(skuInfo);
        return new R<>().ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改Sku信息")
    @ApiOperationSupport(order = 4)
    public R update(@RequestBody @ApiParam(value = "sku信息") SkuInfoVo skuInfoVo){
        SkuInfoEntity skuInfo=new SkuInfoEntity();
        BeanUtils.copyProperties(skuInfoVo,skuInfo);
		skuInfoService.updateById(skuInfo);
        return new R<>().ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @ApiOperation(value = "删除Sku信息")
    @ApiOperationSupport(order = 5)
    public R delete(@RequestBody @ApiParam(value = "spu信息ID数组") Long[] skuIds){
		skuInfoService.removeByIds(Arrays.asList(skuIds));
        return new R<>().ok();
    }

}
