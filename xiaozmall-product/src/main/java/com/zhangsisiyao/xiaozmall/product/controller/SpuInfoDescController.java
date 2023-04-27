package com.zhangsisiyao.xiaozmall.product.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.common.utils.R;
import com.zhangsisiyao.xiaozmall.product.entity.SpuInfoDescEntity;
import com.zhangsisiyao.xiaozmall.product.entity.SpuInfoEntity;
import com.zhangsisiyao.xiaozmall.product.service.SpuInfoDescService;
import com.zhangsisiyao.xiaozmall.product.vo.QueryVo.ImageQueryVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * spu信息介绍
 *
 * @author zhangsisiyao
 * @email 2963456487@qq.com
 * @date 2023-02-17 23:30:14
 */
@RestController
@RequestMapping("product/spuinfodesc")
@Api(tags = "Spu商品描述操作")
@ApiSupport(order = 9,author = "zhangsisiyao")
public class SpuInfoDescController {
    @Autowired
    private SpuInfoDescService spuInfoDescService;

    /**
     * 列表
     */
    @PostMapping("/list")
    //@RequiresPermissions("product:spuinfodesc:list")
    public R<PageUtils> list(@RequestParam ImageQueryVo params){
        PageUtils page = spuInfoDescService.queryPage(params);

        return new  R<PageUtils>().ok().put( page);
    }


    /**
     * 信息
     */
    @PostMapping("/info/{spuId}")
    //@RequiresPermissions("product:spuinfodesc:info")
    public R<SpuInfoDescEntity> info(@PathVariable("spuId") Long spuId){
		SpuInfoDescEntity spuInfoDesc = spuInfoDescService.getById(spuId);

        return new  R<SpuInfoDescEntity>().ok().put(spuInfoDesc);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    //@RequiresPermissions("product:spuinfodesc:save")
    public R save(@RequestBody SpuInfoDescEntity spuInfoDesc){
		spuInfoDescService.save(spuInfoDesc);

        return new R<>().ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    //@RequiresPermissions("product:spuinfodesc:update")
    public R update(@RequestBody SpuInfoDescEntity spuInfoDesc){
		spuInfoDescService.updateById(spuInfoDesc);

        return new R<>().ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    //@RequiresPermissions("product:spuinfodesc:delete")
    public R delete(@RequestBody Long[] spuIds){
		spuInfoDescService.removeByIds(Arrays.asList(spuIds));

        return new R<>().ok();
    }

}
