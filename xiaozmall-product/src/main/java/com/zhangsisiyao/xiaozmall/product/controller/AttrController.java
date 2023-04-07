package com.zhangsisiyao.xiaozmall.product.controller;

import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.common.utils.R;
import com.zhangsisiyao.common.vo.BaseAttrValueVo;
import com.zhangsisiyao.xiaozmall.product.entity.AttrEntity;
import com.zhangsisiyao.xiaozmall.product.entity.ProductAttrValueEntity;
import com.zhangsisiyao.xiaozmall.product.service.AttrService;
import com.zhangsisiyao.xiaozmall.product.vo.AttrValueVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;



/**
 * 商品属性
 *
 * @author zhangsisiyao
 * @email 2963456487@qq.com
 * @date 2023-02-17 23:30:14
 */
@RestController
@RequestMapping("product/attr")
public class AttrController {
    @Autowired
    private AttrService attrService;

    @RequestMapping("/base/list/{catId}")
    public R baseList(@PathVariable Long catId,@RequestParam Map<String,Object> params){
        PageUtils pageUtils = attrService.queryBaseAttr(catId,params);
        return R.ok().put("page",pageUtils);
    }

    @RequestMapping("/base/listforspu/{spuId}")
    public R baseListForSpu(@PathVariable Long spuId){
        List<ProductAttrValueEntity> entities = attrService.queryListForSpu(spuId);
        return R.ok().put("data",entities);
    }

    @RequestMapping("/sale/list/{catId}")
    public R saleList(@PathVariable Long catId,@RequestParam Map<String,Object> params){
        PageUtils pageUtils = attrService.querySaleAttr(catId,params);
        return R.ok().put("page",pageUtils);
    }
    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = attrService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{attrId}")
    //@RequiresPermissions("product:attr:info")
    public R info(@PathVariable("attrId") Long attrId){
		AttrEntity attr = attrService.getById(attrId);

        return R.ok().put("attr", attr);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody AttrEntity attr){
		attrService.save(attr);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody AttrEntity attr){
		attrService.updateById(attr);
        return R.ok();
    }

    @RequestMapping("/update/{spuId}")
    public R updateSpu(@RequestBody List<AttrValueVo> attrs, @PathVariable String spuId){
        attrService.UpdateAttrsBySpuId(attrs,spuId);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] attrIds){
		attrService.removeByIds(Arrays.asList(attrIds));
        return R.ok();
    }

}
