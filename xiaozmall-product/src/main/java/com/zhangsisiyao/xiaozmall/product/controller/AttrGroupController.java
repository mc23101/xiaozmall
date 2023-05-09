package com.zhangsisiyao.xiaozmall.product.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.common.utils.R;
import com.zhangsisiyao.common.vo.product.AttrGroupVo.*;
import com.zhangsisiyao.common.vo.product.AttrGroupVo;
import com.zhangsisiyao.xiaozmall.product.entity.AttrGroupEntity;
import com.zhangsisiyao.xiaozmall.product.service.AttrGroupService;
import com.zhangsisiyao.common.vo.PageParamVo;
import com.zhangsisiyao.xiaozmall.product.vo.QueryVo.AttrGroupQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 
 *
 * @author zhangsisiyao
 * @email 2963456487@qq.com
 * @date 2023-02-25 15:45:31
 */
@RestController
@RequestMapping("product/attrgroup")
@Api(tags = "属性分组操作")
@ApiSupport(order = 1)
public class AttrGroupController {
    @Autowired
    private AttrGroupService attrGroupService;


    @PostMapping("/attr/relation/{attrGroupId}")
    @ApiOperation(value = "获取与属性分组关联的属性")
    @ApiOperationSupport(order = 1)
    public R<PageUtils> getAttrRelation( @ApiParam(value = "属性分组Id")  @PathVariable(value = "attrGroupId")String attrGroupId,
                                            @RequestBody @ApiParam(value = "分页查询参数") PageParamVo params){
        PageUtils page = attrGroupService.queryAttrRelation(attrGroupId,params);
        return new R<PageUtils>().put(page);
    }
    @PostMapping("/noattr/relation/{attrGroupId}")
    @ApiOperation(value = "获取未与属性分组关联的属性")
    @ApiOperationSupport(order = 2)
    public R<PageUtils> getNoattrAttrRelation(
            @ApiParam(value = "属性分组Id")  @PathVariable(value = "attrGroupId")String attrGroupId,
            @RequestBody @ApiParam(value = "分页查询参数") PageParamVo params){
        PageUtils page = attrGroupService.queryNoAttrRelation(attrGroupId, params);
        return new R<PageUtils>().ok().put(page);
    }

    @GetMapping("/withattr/{catalogId}")
    @ApiOperation(value = "获取商品分类下的属性分组")
    @ApiOperationSupport(order = 3)
    public R<List<AttrGroupVo>> withAttrGroupCatalogId(@PathVariable @ApiParam(value = "商品分类id") String catalogId){
        List<AttrGroupVo> list = attrGroupService.queryWithAttrGroup(catalogId);
        return new R<List<AttrGroupVo>>().ok().put(list);
    }

    @PostMapping("/list")
    @ApiOperation(value = "属性分组条件查询")
    @ApiOperationSupport(order = 4)
    public R<PageUtils> list(@RequestBody @ApiParam(value = "条件查询参数") AttrGroupQueryVo params){

        PageUtils page = attrGroupService.queryPage(params);
        return new R<PageUtils>().ok().put(page);
    }


    @GetMapping("/info/{attrGroupId}")
    @ApiOperation(value = "查询属性分组信息")
    @ApiOperationSupport(order = 5)
    public R<Object> info(@PathVariable @ApiParam("属性分组Id") Long attrGroupId){
        AttrGroupEntity attrGroup = attrGroupService.getById(attrGroupId);

        return new R<>().ok().put(attrGroup);
    }

    @PostMapping("/attr/relation/add")
    @ApiOperation("添加属性分组关联信息")
    @ApiOperationSupport(order = 6)
    public R<Object> addAttrRelation(@RequestBody @ApiParam(value = "添加的关联信息") List<AttrAttrgroupRelationVo> relationEntities){
        attrGroupService.addAttrRelation(relationEntities);
        return new R<>().ok();
    }

    @DeleteMapping("/attr/relation/delete")
    @ApiOperation(value = "删除属性分组关联信息")
    @ApiOperationSupport(order = 7)
    public R<Object> deleteAttrRelation(@RequestBody @ApiParam(value = "删除的关联信息id")  List<AttrAttrgroupRelationVo> relationEntities){
        attrGroupService.deleteAttrRelation(relationEntities);
        return new R<>().ok();
    }


    @PostMapping("/save")
    @ApiOperation(value = "新增属性分组")
    @ApiOperationSupport(order = 8)
    public R<Object> save(@RequestBody @ApiParam(value = "新增的属性分组信息") AttrGroupVo attrGroup){
        AttrGroupEntity attrGroupEntity=new AttrGroupEntity();
        BeanUtils.copyProperties(attrGroup,attrGroupEntity);
        attrGroupService.save(attrGroupEntity);
        return new R<>().ok();
    }



    @PostMapping("/update")
    @ApiOperation(value = "更新属性分组")
    @ApiOperationSupport(order = 9)
    public R<Object> update(@RequestBody @ApiParam("更新的属性分组信息") AttrGroupVo attrGroup){
        AttrGroupEntity attrGroupEntity=new AttrGroupEntity();
        BeanUtils.copyProperties(attrGroup,attrGroupEntity);
        attrGroupService.updateById(attrGroupEntity);
        return new R<>().ok();
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "删除属性分组")
    @ApiOperationSupport(order = 10)
    public R<Object> delete(@RequestBody @ApiParam(value = "属性分组的id数组") Long[] attrGroupIds){
        attrGroupService.deleteAttrGroup(attrGroupIds);
        return new R<>().ok();
    }

}
