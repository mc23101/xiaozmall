package com.zhangsisiyao.xiaozmall.product.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.common.utils.R;
import com.zhangsisiyao.common.vo.product.ImageVo;
import com.zhangsisiyao.xiaozmall.product.entity.SkuImagesEntity;
import com.zhangsisiyao.xiaozmall.product.service.SkuImagesService;
import com.zhangsisiyao.common.vo.product.PageParamVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


/**
 * sku图片
 *
 * @author zhangsisiyao
 * @email 2963456487@qq.com
 * @date 2023-02-17 23:30:14
 */
@RestController
@RequestMapping("product/skuimages")
@Api(tags = "Sku图片操作")
@ApiSupport(order = 7)
public class SkuImagesController {
    @Autowired
    private SkuImagesService skuImagesService;

    /**
     * 列表
     */
    @PostMapping("/list")
    @ApiOperation(value = "分页查询sku图片信息")
    public R<PageUtils> list(@RequestParam @ApiParam(value = "分页查询参数") PageParamVo params){
        PageUtils page = skuImagesService.queryPage(params);
        return new R<PageUtils>().ok().put( page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @ApiOperation(value = "查询sku图片信息")
    public R<ImageVo> info(@PathVariable("id") @ApiParam(value = "sku图片Id") Long id){
        ImageVo imageVo=new ImageVo();
		SkuImagesEntity skuImages = skuImagesService.getById(id);
        BeanUtils.copyProperties(skuImages,imageVo);
        return new R<ImageVo>().ok().put(imageVo);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation(value = "保存sku图片")
    public R save(@RequestBody @ApiParam(value = "sku图片信息")  ImageVo imageVo){
        SkuImagesEntity skuImages=new SkuImagesEntity();
        BeanUtils.copyProperties(imageVo,skuImages);
		skuImagesService.save(skuImages);
        return new R<>().ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改sku图片信息")
    public R update(@RequestBody @ApiParam(value = "sku图片") ImageVo imageVo){
        SkuImagesEntity skuImages=new SkuImagesEntity();
        BeanUtils.copyProperties(imageVo,skuImages);
		skuImagesService.updateById(skuImages);
        return new R<>().ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @ApiOperation(value = "删除Sku图片")
    public R delete(@RequestBody @ApiParam(value = "sku图片Id数组") Long[] ids){
		skuImagesService.removeByIds(Arrays.asList(ids));
        return new R<>().ok();
    }

}
