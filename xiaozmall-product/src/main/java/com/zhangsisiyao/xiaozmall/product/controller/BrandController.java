package com.zhangsisiyao.xiaozmall.product.controller;

import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.common.utils.R;
import com.zhangsisiyao.common.vo.product.BrandVo;
import com.zhangsisiyao.xiaozmall.product.entity.BrandEntity;
import com.zhangsisiyao.xiaozmall.product.service.BrandService;
import com.zhangsisiyao.common.vo.product.PageParamVo;
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
public class BrandController {
    @Autowired
    private BrandService brandService;

    /**
     * 列表
     */
    @PostMapping("/list")
    @ApiOperation(value = "分页查询品牌信息")
    public R<PageUtils> list(@RequestParam @ApiParam("分页查询参数") PageParamVo params){
        PageUtils page = brandService.queryPage(params);
        return new R<PageUtils>().ok().put( page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{brandId}")
    @ApiOperation(value = "查询品牌信息")
    public R<BrandVo> info(@PathVariable("brandId") @ApiParam(value = "品牌Id") Long brandId){
		BrandEntity brand = brandService.getById(brandId);
        BrandVo brandVo=new BrandVo();
        BeanUtils.copyProperties(brand,brandVo);
        return new R<BrandVo>().ok().put(brandVo);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增品牌")
    public R<Object> save(@RequestBody @ApiParam(value = "品牌信息") BrandVo brandVo){
        BrandEntity brand=new BrandEntity();
        BeanUtils.copyProperties(brandVo,brand);
        brandService.save(brand);

        return new R<>().ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "更新品牌信息")
    public R<Object> update(@RequestBody @ApiParam(value = "品牌信息") BrandVo brandVo){
        BrandEntity brand=new BrandEntity();
        BeanUtils.copyProperties(brandVo,brand);
		brandService.updateById(brand);
        return new R<>().ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @ApiOperation(value = "删除品牌信息")
    public R<Object> delete(@RequestBody @ApiParam(value = "品牌id数组") Long[] brandIds){
		brandService.removeByIds(Arrays.asList(brandIds));
        return new R<>().ok();
    }

}
