package com.zhangsisiyao.xiaozmall.product.controller;

import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.common.utils.R;
import com.zhangsisiyao.xiaozmall.product.entity.SpuInfoEntity;
import com.zhangsisiyao.xiaozmall.product.service.SpuInfoService;
import com.zhangsisiyao.common.vo.product.ProductVo;
import io.swagger.annotations.Api;
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
@Api(tags = "Spu操作")
public class SpuInfoController {
    @Autowired
    private SpuInfoService spuInfoService;


    @PostMapping("/list/{catalog}/{brand}")
    public R<List<SpuInfoEntity>> listWithCatalogAndBrand( @PathVariable String catalog,@PathVariable String brand){
        List<SpuInfoEntity> list = spuInfoService.getWithCatalogAndBrand(catalog, brand);
        return new R<List<SpuInfoEntity>>().ok().put( list);
    }

    @PostMapping("/getProduct/{spuId}")
    public R<ProductVo> infoProduct(@PathVariable Long spuId){
        ProductVo product = spuInfoService.getProduct(spuId);
        return new R<ProductVo>().ok().put(product);
    }


    /**
     * 列表
     */
    @PostMapping("/list")
    public R<PageUtils> list(@RequestParam Map<String, Object> params){
        PageUtils page = spuInfoService.queryPageLimit(params);
        return new  R<PageUtils>().ok().put( page);
    }


    @PostMapping("/up/{spuId}")
    public R SpuUp(@PathVariable Long spuId){
        //TODO spu上架时，提交商品信息到ES
        this.spuInfoService.upSpu(spuId);
        return new R<>().ok();
    }

    @PostMapping("/down/{spuId}")
    public R SpuDown(@PathVariable Long spuId){
        this.spuInfoService.downSpu(spuId);
        return new R<>().ok();
    }


    /**
     * 信息
     */
    @PostMapping("/info/{id}")
    public R<SpuInfoEntity> info(@PathVariable("id") Long id){
		SpuInfoEntity spuInfo = spuInfoService.getById(id);

        return new  R<SpuInfoEntity>().ok().put( spuInfo);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public R save(@RequestBody ProductVo product){
        System.out.println(product);
        boolean b = spuInfoService.saveProduct(product);
        if(b){
            return new R<>().ok();
        }else {
            return new R<>().error();
        }
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public R update(@Valid @RequestBody SpuInfoEntity spuInfo){
		spuInfoService.updateById(spuInfo);

        return new R<>().ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		spuInfoService.deleteSpu(ids);
        return new R<>().ok();
    }

}
