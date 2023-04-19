package com.zhangsisiyao.xiaozmall.product.controller;

import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.common.utils.R;
import com.zhangsisiyao.common.vo.AttrGroupVo;
import com.zhangsisiyao.xiaozmall.product.entity.AttrAttrgroupRelationEntity;
import com.zhangsisiyao.xiaozmall.product.entity.AttrEntity;
import com.zhangsisiyao.xiaozmall.product.entity.AttrGroupEntity;
import com.zhangsisiyao.xiaozmall.product.service.AttrGroupService;
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
public class AttrGroupController {
    @Autowired
    private AttrGroupService attrGroupService;

    @RequestMapping("/attr/relation/get/{attrGroupId}")
    public R getAttrRelation(@PathVariable String attrGroupId){
        List<AttrEntity> attrEntities = attrGroupService.queryAttrRelation(attrGroupId);
        return R.ok().put("data",attrEntities);
    }

    @RequestMapping("/attr/relation/add")
    public R addAttrRelation(@RequestBody List<AttrAttrgroupRelationEntity> relationEntities){
        attrGroupService.addAttrRelation(relationEntities);
        return R.ok();
    }

    @RequestMapping("/attr/relation/delete")
    public R deleteAttrRelation(@RequestBody List<AttrAttrgroupRelationEntity> relationEntities){
        attrGroupService.deleteAttrRelation(relationEntities);
        return R.ok();
    }

    @RequestMapping("/withattr/{catalogId}")
    public R withattrCatalogId(@PathVariable String catalogId){
        List<AttrGroupVo> list = attrGroupService.queryWithAttr(catalogId);
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
