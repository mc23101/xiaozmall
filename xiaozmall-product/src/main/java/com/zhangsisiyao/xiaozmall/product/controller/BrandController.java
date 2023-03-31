package com.zhangsisiyao.xiaozmall.product.controller;

import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.common.utils.R;
import com.zhangsisiyao.xiaozmall.product.entity.BrandEntity;
import com.zhangsisiyao.xiaozmall.product.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * 品牌
 *
 * @author zhangsisiyao
 * @email 2963456487@qq.com
 * @date 2023-02-17 23:30:14
 */
@RestController
@RequestMapping("product/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;


    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("product:brand:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = brandService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{brandId}")
    //@RequiresPermissions("product:brand:info")
    public R info(@PathVariable("brandId") Long brandId){
		BrandEntity brand = brandService.getById(brandId);

        return R.ok().put("brand", brand);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("product:brand:save")
    public R save(@RequestBody BrandEntity brand){
        brandService.save(brand);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:brand:update")
    public R update(@RequestBody BrandEntity brand){
		brandService.updateById(brand);
        return R.ok();
    }

    @RequestMapping("/update/status")
    public R updateStatus(@RequestBody BrandEntity brand){
        brandService.updateById(brand);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:brand:delete")
    public R delete(@RequestBody Long[] brandIds){
		brandService.removeByIds(Arrays.asList(brandIds));

        return R.ok();
    }

}
