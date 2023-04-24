package com.zhangsisiyao.xiaozmall.product.controller;

import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.common.utils.R;
import com.zhangsisiyao.common.vo.AttrAttrgroupRelationVo;
import com.zhangsisiyao.common.vo.AttrGroupVo;
import com.zhangsisiyao.common.vo.AttrVo;
import com.zhangsisiyao.xiaozmall.product.entity.AttrAttrgroupRelationEntity;
import com.zhangsisiyao.xiaozmall.product.entity.AttrEntity;
import com.zhangsisiyao.xiaozmall.product.entity.AttrGroupEntity;
import com.zhangsisiyao.xiaozmall.product.service.AttrGroupService;
import com.zhangsisiyao.xiaozmall.product.vo.PageParamVo;
import feign.Param;
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
 * 
 *
 * @author zhangsisiyao
 * @email 2963456487@qq.com
 * @date 2023-02-25 15:45:31
 */
@RestController
@RequestMapping("product/attrgroup")
@Api(tags = "属性分类操作")
public class AttrGroupController {
    @Autowired
    private AttrGroupService attrGroupService;

    @ApiOperation(value = "获取与属性分类组关联的属性")
    @GetMapping("/attr/relation/get/{attrGroupId}")
    public R<List<AttrVo>> getAttrRelation(@PathVariable @ApiParam(value = "属性分类组Id") String attrGroupId){
        List<AttrVo> attrEntities = attrGroupService.queryAttrRelation(attrGroupId);
        return new R<List<AttrVo>>().put(attrEntities);
    }

    @PostMapping("/attr/relation/add")
    @ApiOperation("添加属性分组关联信息")
    public R<Object> addAttrRelation(@RequestBody @ApiParam(value = "添加的关联信息") List<AttrAttrgroupRelationVo> relationEntities){
        attrGroupService.addAttrRelation(relationEntities);
        return new R<>().ok();
    }

    @DeleteMapping("/attr/relation/delete")
    @ApiOperation(value = "删除属性分组关联信息")
    public R<Object> deleteAttrRelation(@RequestBody @ApiParam(value = "删除的关联信息id") Long[] relationIds){
        attrGroupService.deleteAttrRelation(relationIds);
        return new R<>().ok();
    }

    @GetMapping("/withattr/{catalogId}")
    @ApiOperation(value = "获取商品分类下的属性")
    public R<List<AttrGroupVo>> withattrCatalogId(@PathVariable @ApiParam(value = "商品分类id") String catalogId){
        List<AttrGroupVo> list = attrGroupService.queryWithAttr(catalogId);
        return new R<List<AttrGroupVo>>().ok().put(list);
    }

    @PostMapping("/noattr/relation/{attrGroupId}")
    @ApiOperation(value = "获取未与属性分组关联的属性")
    public R<PageUtils> getNoattrAttrRelation(@PathVariable @ApiParam(value = "属性分组Id") String attrGroupId,@RequestBody @ApiParam(value = "分页查询参数") PageParamVo params){
        PageUtils page = attrGroupService.queryNoAttrRelation(attrGroupId, params);
        return new R<PageUtils>().ok().put(page);
    }

    @PostMapping("/list/{cat_Id}")
    @ApiOperation(value = "通过分类Id获取属性分组信息")
    public R<PageUtils> getListByCatId(@PathVariable @ApiParam(value = "分类Id") Long cat_Id,@RequestBody @ApiParam(value = "分页查询信息") PageParamVo params){
        PageUtils page;
        if(cat_Id==0){
            page = attrGroupService.queryPageByColumn(null,null,params);
        }else{
            page = attrGroupService.queryPageByColumn("catalog_id",cat_Id,params);
        }
        return new R<PageUtils>().ok().put(page);
    }

    /**
     * 列表
     */
    @PostMapping("/list")
    @ApiOperation(value = "分页查询属性分组")
    public R<PageUtils> list(@RequestBody @ApiParam(value = "分页查询参数") PageParamVo params){
        PageUtils page = attrGroupService.queryPage(params);
        return new R<PageUtils>().ok().put(page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{attrGroupId}")
    @ApiOperation(value = "查询属性分组信息")
    public R<Object> info(@PathVariable @ApiParam("属性分组Id") Long attrGroupId){
		AttrGroupEntity attrGroup = attrGroupService.getById(attrGroupId);

        return new R<>().ok().put(attrGroup);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation("新增属性分组")
    public R<Object> save(@RequestBody @ApiParam(value = "新增的属性分组信息") AttrGroupVo attrGroup){
        AttrGroupEntity attrGroupEntity=new AttrGroupEntity();
        BeanUtils.copyProperties(attrGroup,attrGroupEntity);
		attrGroupService.save(attrGroupEntity);

        return new R<>().ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "更属性新分组信息")
    public R<Object> update(@RequestBody @ApiParam("更新的属性分组信息") AttrGroupVo attrGroup){
        AttrGroupEntity attrGroupEntity=new AttrGroupEntity();
        BeanUtils.copyProperties(attrGroup,attrGroupEntity);
		attrGroupService.updateById(attrGroupEntity);
        return new R<>().ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @ApiOperation(value = "删除属性分组")
    public R<Object> delete(@RequestBody @ApiParam(value = "属性分组的id数组") Long[] attrGroupIds){
		attrGroupService.removeByIds(Arrays.asList(attrGroupIds));
        return new R<>().ok();
    }

}
