package com.zhangsisiyao.xiaozmall.product.controller;

import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.common.utils.R;
import com.zhangsisiyao.xiaozmall.product.entity.SpuInfoEntity;
import com.zhangsisiyao.xiaozmall.product.service.SpuInfoService;
import com.zhangsisiyao.xiaozmall.product.vo.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;


/**
 * spu信息
 *
 * @author zhangsisiyao
 * @email 2963456487@qq.com
 * @date 2023-02-17 23:30:14
 */
@RestController
@RequestMapping("product/spuinfo")
public class SpuInfoController {
    @Autowired
    private SpuInfoService spuInfoService;


    @RequestMapping("/list/{catalog}/{brand}")
    public R listWithCatalogAndBrand( @PathVariable String catalog,@PathVariable String brand){
        List<SpuInfoEntity> list = spuInfoService.getWithCatalogAndBrand(catalog, brand);
        return R.ok().put("data", list);
    }


    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = spuInfoService.queryPageLimit(params);
        return R.ok().put("page", page);
    }


    @RequestMapping("/up/{spuId}")
    public R SpuUp(@PathVariable Long spuId){
        //TODO spu上架时，提交商品信息到ES
        this.spuInfoService.upSpu(spuId);
        return R.ok("success");
    }

    @RequestMapping("/down/{spuId}")
    public R SpuDown(@PathVariable Long spuId){
        this.spuInfoService.downSpu(spuId);
        return R.ok("success");
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		SpuInfoEntity spuInfo = spuInfoService.getById(id);

        return R.ok().put("spuInfo", spuInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ProductVo product){
        System.out.println(product);
        boolean b = spuInfoService.saveProduct(product);
        if(b){
            return R.ok();
        }else {
            return R.error();
        }
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:spuinfo:update")
    public R update(@Valid @RequestBody SpuInfoEntity spuInfo){
		spuInfoService.updateById(spuInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:spuinfo:delete")
    public R delete(@RequestBody Long[] ids){
		spuInfoService.deleteSpu(ids);
        return R.ok();
    }

}
