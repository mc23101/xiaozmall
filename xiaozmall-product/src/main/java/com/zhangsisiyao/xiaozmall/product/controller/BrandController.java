package com.zhangsisiyao.xiaozmall.product.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.common.utils.R;
import com.zhangsisiyao.common.vo.product.BrandVo;
import com.zhangsisiyao.xiaozmall.product.entity.BrandEntity;
import com.zhangsisiyao.xiaozmall.product.service.BrandService;
import com.zhangsisiyao.xiaozmall.product.vo.QueryVo.BrandQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


/**
 * 品牌
 *
 * @author zhangsisiyao
 * @email 2963456487@qq.com
 * @date 2023-02-17 23:30:14
 */
@RestController
@RequestMapping("product/brand")
@Api(tags = "品牌操作")
@ApiSupport(order = 4)
public class BrandController {
    @Autowired
    private BrandService brandService;


    @PostMapping("/list")
    @ApiOperation(value = "品牌条件查询")
    @ApiOperationSupport(order = 1)
    public R<PageUtils> list(@RequestBody @ApiParam("条件查询参数") BrandQueryVo params){
        PageUtils page = brandService.queryPage(params);
        return new R<PageUtils>().ok().put( page);
    }



    @GetMapping("/info/{brandId}")
    @ApiOperation(value = "查询品牌信息")
    @ApiOperationSupport(order = 2)
    public R<BrandVo> info(@PathVariable("brandId") @ApiParam(value = "品牌Id") Long brandId){
		BrandEntity brand = brandService.getById(brandId);
        BrandVo brandVo=new BrandVo();
        BeanUtils.copyProperties(brand,brandVo);
        return new R<BrandVo>().ok().put(brandVo);
    }


    @PostMapping("/save")
    @ApiOperation(value = "新增品牌信息")
    @ApiOperationSupport(order = 3)
    public R<Object> save(@RequestBody @ApiParam(value = "品牌信息") BrandVo brandVo){
        BrandEntity brand=new BrandEntity();
        BeanUtils.copyProperties(brandVo,brand);
        brandService.save(brand);

        return new R<>().ok();
    }


    @PostMapping("/update")
    @ApiOperation(value = "更新品牌信息")
    @ApiOperationSupport(order = 4)
    public R<Object> update(@RequestBody @ApiParam(value = "品牌信息") BrandVo brandVo){
        BrandEntity brand=new BrandEntity();
        BeanUtils.copyProperties(brandVo,brand);
		brandService.updateById(brand);
        return new R<>().ok();
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "删除品牌信息")
    @ApiOperationSupport(order = 5)
    public R<Object> delete(@RequestBody @ApiParam(value = "品牌id数组") Long[] brandIds){
		brandService.removeByIds(Arrays.asList(brandIds));
        return new R<>().ok();
    }

}
