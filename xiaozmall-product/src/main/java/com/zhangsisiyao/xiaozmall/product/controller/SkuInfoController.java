package com.zhangsisiyao.xiaozmall.product.controller;

import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.common.utils.R;
import com.zhangsisiyao.xiaozmall.product.entity.SkuInfoEntity;
import com.zhangsisiyao.xiaozmall.product.service.SkuInfoService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;



/**
 * sku信息
 *
 * @author zhangsisiyao
 * @email 2963456487@qq.com
 * @date 2023-02-17 23:30:14
 */
@RestController
@RequestMapping("product/skuinfo")
@Api(tags = "Sku信息操作")
public class SkuInfoController {
    @Autowired
    private SkuInfoService skuInfoService;

    /**
     * 列表
     */
    @PostMapping("/list")
    //@RequiresPermissions("product:skuinfo:list")
    public R<PageUtils> list(@RequestParam Map<String, Object> params){
        PageUtils page = skuInfoService.queryPageLimit(params);

        return new  R<PageUtils>().ok().put(page);
    }

    @PostMapping("/list/{catalog}/{brand}/{spu}")
    //@RequiresPermissions("product:skuinfo:list")
    public R<List<SkuInfoEntity>> listWithCatalogBrandSpu(@PathVariable String brand, @PathVariable String catalog, @PathVariable String spu){
        List<SkuInfoEntity> entities = skuInfoService.listWithCatalogBrandSpu(catalog, brand, spu);

        return new R<List<SkuInfoEntity>>().ok().put(entities);
    }


    /**
     * 信息
     */
    @PostMapping("/info/{skuId}")
    //@RequiresPermissions("product:skuinfo:info")
    public R<SkuInfoEntity> info(@PathVariable("skuId") Long skuId){
		SkuInfoEntity skuInfo = skuInfoService.getById(skuId);

        return new R<SkuInfoEntity>().ok().put( skuInfo);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public R save(@RequestBody SkuInfoEntity skuInfo){
		skuInfoService.save(skuInfo);
        return new R<>().ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public R update(@RequestBody SkuInfoEntity skuInfo){
		skuInfoService.updateById(skuInfo);

        return new R<>().ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    public R delete(@RequestBody Long[] skuIds){
		skuInfoService.removeByIds(Arrays.asList(skuIds));

        return new R<>().ok();
    }

}
