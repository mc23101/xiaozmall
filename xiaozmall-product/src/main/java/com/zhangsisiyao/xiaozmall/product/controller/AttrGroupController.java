package com.zhangsisiyao.xiaozmall.product.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhangsisiyao.xiaozmall.product.entity.AttrAttrgroupRelationEntity;
import com.zhangsisiyao.xiaozmall.product.entity.AttrEntity;
import com.zhangsisiyao.xiaozmall.product.service.AttrAttrgroupRelationService;
import com.zhangsisiyao.xiaozmall.product.service.AttrService;
import com.zhangsisiyao.xiaozmall.product.vo.AttrGroupWithAttrsVo;
import com.zhangsisiyao.xiaozmall.product.entity.AttrGroupEntity;
import com.zhangsisiyao.xiaozmall.product.service.AttrGroupService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.common.utils.R;



/**
 * 
 *
 * @author zhangsisiyao
 * @email 2963456487@qq.com
 * @date 2023-02-25 15:45:31
 */
@RestController
@RequestMapping("product/attrgroup")
public class AttrGroupController {
    @Autowired
    private AttrGroupService attrGroupService;

    @Autowired
    private AttrAttrgroupRelationService relationService;

    @Autowired
    private AttrService attrService;

    @RequestMapping("/attr/relation/get/{attrGroupId}")
    public R getAttrRelation(@PathVariable String attrGroupId){
        List<AttrAttrgroupRelationEntity> attr_group_id = relationService.query().eq("attr_group_id", attrGroupId).list();
        List<AttrEntity> attrEntities=new ArrayList<>();
        for(AttrAttrgroupRelationEntity entity:attr_group_id){
            attrEntities.addAll(attrService.query().eq("attr_id",entity.getAttrId()).list());
        }
        return R.ok().put("data",attrEntities);
    }

    @RequestMapping("/attr/relation/add")
    public R addAttrRelation(@RequestBody List<AttrAttrgroupRelationEntity> relationEntities){
        relationEntities.forEach((entity)->relationService.save(entity));
        return R.ok();
    }

    @RequestMapping("/attr/relation/delete")
    public R deleteAttrRelation(@RequestBody List<AttrAttrgroupRelationEntity> relationEntities){
        relationEntities.forEach((entity)->relationService.remove(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id",entity.getAttrId()).eq("attr_group_id",entity.getAttrGroupId())));
        return R.ok();
    }

    @RequestMapping("/withattr/{catalogId}")
    public R withattrCatalogId(@PathVariable String catalogId){
        List<AttrGroupWithAttrsVo> list = attrGroupService.queryWithAttr(catalogId);
        return R.ok().put("data",list);
    }

    @RequestMapping("/noattr/relation/{attrGroupId}")
    public R getNoattrAttrRelation(@PathVariable String attrGroupId,@RequestParam Map<String, Object> params){
        PageUtils page = attrGroupService.queryNoAttrRelation(attrGroupId, params);
        return R.ok().put("page",page);
    }

    @RequestMapping("/list/{cat_Id}")
    public R getListByCatId(@PathVariable Long cat_Id,@RequestParam Map<String, Object> params){
        PageUtils page;
        if(cat_Id==0){
            page = attrGroupService.queryPageByColumn(null,null,params);
        }else{
            page = attrGroupService.queryPageByColumn("catalog_id",cat_Id,params);
        }
        return R.ok().put("page",page);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("product:attrgroup:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = attrGroupService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{attrGroupId}")
    //@RequiresPermissions("product:attrgroup:info")
    public R info(@PathVariable("attrGroupId") Long attrGroupId){
		AttrGroupEntity attrGroup = attrGroupService.getById(attrGroupId);

        return R.ok().put("attrGroup", attrGroup);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("product:attrgroup:save")
    public R save(@RequestBody AttrGroupEntity attrGroup){
		attrGroupService.save(attrGroup);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:attrgroup:update")
    public R update(@RequestBody AttrGroupEntity attrGroup){
		attrGroupService.updateById(attrGroup);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:attrgroup:delete")
    public R delete(@RequestBody Long[] attrGroupIds){
		attrGroupService.removeByIds(Arrays.asList(attrGroupIds));

        return R.ok();
    }

}
