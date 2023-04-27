package com.zhangsisiyao.xiaozmall.product.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.common.utils.R;
import com.zhangsisiyao.common.vo.product.CatalogVo;
import com.zhangsisiyao.xiaozmall.product.entity.CategoryEntity;
import com.zhangsisiyao.xiaozmall.product.service.CategoryService;
import com.zhangsisiyao.xiaozmall.product.vo.QueryVo.CatalogQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;



/**
 * 商品三级分类
 *
 * @author zhangsisiyao
 * @email 2963456487@qq.com
 * @date 2023-02-17 23:30:14
 */
@RestController
@RequestMapping("product/category")
@Api(tags = "商品分类操作")
@ApiSupport(order = 11)
public class CategoryController {
    @Autowired
    private CategoryService categoryService;


    @GetMapping("/list/tree")
    @ApiOperation("获取树形商品分类列表")
    public R<List<CatalogVo>> listWithTree(){
        List<CatalogVo> entities = categoryService.listWithTree();
        return new  R<List<CatalogVo>>().ok().put(entities);
    }

    @GetMapping("/list/map")
    @ApiOperation("获取商品分类列表")
    public R<Map<Long, CatalogVo>> listWithMap(){
        Map<Long,CatalogVo> entityMap = categoryService.listWithMap();
        return new R<Map<Long, CatalogVo>>().ok().put(entityMap);
    }

    /**
     * 列表
     */
    @PostMapping("/list")
    @ApiOperation(value = "条件查询商品分类信息")
    public R<PageUtils> list(@RequestParam @ApiParam(value = "条件参数") CatalogQueryVo params){
        PageUtils page = categoryService.queryPage(params);

        return new  R<PageUtils>().ok().put( page);
    }


    /**
     * 信息
     */
    @PostMapping("/info/{catId}")
    @ApiOperation(value = "查询商品分类信息")
    public R<CategoryEntity> info(@PathVariable("catId") @ApiParam(value = "商品分类Id") Long catId){
		CategoryEntity category = categoryService.getById(catId);
        return new  R<CategoryEntity>().ok().put( category);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增商品分类")
    public R<Object> save(@RequestBody @ApiParam(value = "商品分类信息") CatalogVo categoryVo){
        CategoryEntity category=new CategoryEntity();
        BeanUtils.copyProperties(categoryVo,category);
		categoryService.save(category);
        return new R<>().ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "更新商品分类")
    public R<Object> update(@RequestBody @ApiParam(value = "商品分类信息") CatalogVo categoryVo){
        CategoryEntity entity=new CategoryEntity();
        BeanUtils.copyProperties(categoryVo,entity);
		categoryService.updateById(entity);

        return new R<>().ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation("删除商品分类")
    public R<Object> delete(@RequestBody @ApiParam(value = "商品分类id数组") Long[] catIds){
		categoryService.removeByIds(Arrays.asList(catIds));

        return new R<>().ok();
    }

}
