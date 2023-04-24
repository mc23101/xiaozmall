package com.zhangsisiyao.xiaozmall.product.controller;

import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.common.utils.R;
import com.zhangsisiyao.xiaozmall.product.entity.SpuImagesEntity;
import com.zhangsisiyao.xiaozmall.product.service.SpuImagesService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * spu图片
 *
 * @author zhangsisiyao
 * @email 2963456487@qq.com
 * @date 2023-02-17 23:30:14
 */
@RestController
@RequestMapping("product/spuimages")
@Api(tags = "Spu图片信息操作")
public class SpuImagesController {
    @Autowired
    private SpuImagesService spuImagesService;

    /**
     * 列表
     */
    @PostMapping("/list")
    //@RequiresPermissions("product:spuimages:list")
    public R<PageUtils> list(@RequestParam Map<String, Object> params){
        PageUtils page = spuImagesService.queryPage(params);

        return new  R<PageUtils>().ok().put( page);
    }


    /**
     * 信息
     */
    @PostMapping("/info/{id}")
    //@RequiresPermissions("product:spuimages:info")
    public R<SpuImagesEntity> info(@PathVariable("id") Long id){
		SpuImagesEntity spuImages = spuImagesService.getById(id);

        return new  R<SpuImagesEntity>().ok().put( spuImages);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    //@RequiresPermissions("product:spuimages:save")
    public R save(@RequestBody SpuImagesEntity spuImages){
		spuImagesService.save(spuImages);

        return new R<>().ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    //@RequiresPermissions("product:spuimages:update")
    public R update(@RequestBody SpuImagesEntity spuImages){
		spuImagesService.updateById(spuImages);

        return new R<>().ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    //@RequiresPermissions("product:spuimages:delete")
    public R delete(@RequestBody Long[] ids){
		spuImagesService.removeByIds(Arrays.asList(ids));

        return new R<>().ok();
    }

}
