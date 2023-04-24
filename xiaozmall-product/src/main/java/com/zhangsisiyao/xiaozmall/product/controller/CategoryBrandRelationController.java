package com.zhangsisiyao.xiaozmall.product.controller;

import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.common.utils.R;
import com.zhangsisiyao.common.vo.BrandVo;
import com.zhangsisiyao.common.vo.CategoryBrandRelationVo;
import com.zhangsisiyao.xiaozmall.product.entity.BrandEntity;
import com.zhangsisiyao.xiaozmall.product.entity.CategoryBrandRelationEntity;
import com.zhangsisiyao.xiaozmall.product.entity.CategoryEntity;
import com.zhangsisiyao.xiaozmall.product.service.BrandService;
import com.zhangsisiyao.xiaozmall.product.service.CategoryBrandRelationService;
import com.zhangsisiyao.xiaozmall.product.service.CategoryService;
import com.zhangsisiyao.xiaozmall.product.vo.PageParamVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;



/**
 * 品牌分类关联
 *
 * @author zhangsisiyao
 * @email 2963456487@qq.com
 * @date 2023-02-26 09:32:23
 */
@RestController
@RequestMapping("product/categorybrandrelation")
@Api(tags = "商品分类与品牌关联操作")
public class CategoryBrandRelationController {
    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BrandService brandService;

    @GetMapping("catalog/list/{brandId}")
    @ApiOperation(value = "通过品牌id查询商品分类品牌关联信息")
    public R<List<CategoryBrandRelationVo>> catalogList(@PathVariable @ApiParam(value = "品牌Id") String brandId){
        List<CategoryBrandRelationVo> result=new ArrayList<>();
        categoryBrandRelationService.query().eq("brand_id", brandId).list().forEach((relation)->{
            CategoryBrandRelationVo cur=new CategoryBrandRelationVo();
            BeanUtils.copyProperties(relation,cur);
            result.add(cur);
        });
        return new R<List<CategoryBrandRelationVo>>().ok().put(result);
    }

    /**
     * 列表
     */
    @PostMapping("/list")
    @ApiOperation(value = "分页查询商品分类品牌关联信息")
    public R<PageUtils> list(@RequestParam @ApiParam(value = "分页查询参数")  PageParamVo params){
        PageUtils page = categoryBrandRelationService.queryPage(params);
        return new  R<PageUtils>().ok().put(page);
    }

    @GetMapping("/brands/list/{catId}")
    @ApiOperation("通过分类Id查询关联的品牌信息")
    public R<List<BrandVo>> brandsList(@PathVariable @ApiParam("分类Id") String catId){
        List<BrandVo> list = categoryBrandRelationService.queryBrand(catId);
        return new R<List<BrandVo>>().ok().put(list);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @ApiOperation(value = "查询商品分类品牌关联信息")
    public R<CategoryBrandRelationVo> info(@PathVariable @ApiParam("商品分类品牌关联信息Id") Long id){
		CategoryBrandRelationEntity categoryBrandRelation = categoryBrandRelationService.getById(id);
        CategoryBrandRelationVo result=new CategoryBrandRelationVo();
        BeanUtils.copyProperties(categoryBrandRelation,result);
        return new  R<CategoryBrandRelationVo>().ok().put(result);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增商品分类品牌关联信息")
    public R<Object> save(@RequestBody @ApiParam(value = "商品分类品牌关联信息") CategoryBrandRelationVo categoryBrandRelationVo){
        BrandEntity brand = brandService.query().eq("brand_id", categoryBrandRelationVo.getBrandId()).one();
        CategoryEntity category=categoryService.query().eq("cat_id",categoryBrandRelationVo.getCatalogId()).one();
        categoryBrandRelationVo.setBrandName(brand.getName());
        categoryBrandRelationVo.setCatalogName(category.getName());
        CategoryBrandRelationEntity entity=new CategoryBrandRelationEntity();
        BeanUtils.copyProperties(categoryBrandRelationVo,entity);
        categoryBrandRelationService.save(entity);
        return new R<>().ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "更新商品分类品牌关联信息")
    public R<Object> update(@RequestBody @ApiParam(value = "商品分类品牌关联信息") CategoryBrandRelationVo categoryBrandRelationVo){
        CategoryBrandRelationEntity entity=new CategoryBrandRelationEntity();
        BeanUtils.copyProperties(categoryBrandRelationVo,entity);
		categoryBrandRelationService.updateById(entity);
        return new R<>().ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @ApiOperation(value = "删除商品分类品牌关联信息")
    public R<Object> delete(@RequestBody @ApiParam("商品分类品牌关联信息id数组") Long[] ids){
		categoryBrandRelationService.removeByIds(Arrays.asList(ids));

        return new R<>().ok();
    }

}
