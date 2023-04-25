package com.zhangsisiyao.xiaozmall.product.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.common.utils.R;
import com.zhangsisiyao.common.vo.product.SpuInfoVo;
import com.zhangsisiyao.xiaozmall.product.entity.SpuInfoEntity;
import com.zhangsisiyao.xiaozmall.product.service.SpuInfoService;
import com.zhangsisiyao.common.vo.product.ProductVo;
import com.zhangsisiyao.xiaozmall.product.vo.SpuInfoQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
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
@ApiSupport(order = 0)
public class SpuInfoController {
    @Autowired
    private SpuInfoService spuInfoService;


    @PostMapping("/list/{catalog}/{brand}")
    @ApiOperation(value = "通过分类id、品牌id获取Spu信息")
    public R<List<SpuInfoVo>> listWithCatalogAndBrand(@PathVariable @ApiParam(value = "分类id") String catalog, @PathVariable @ApiParam(value = "品牌id") String brand){
        List<SpuInfoVo> list = spuInfoService.getWithCatalogAndBrand(catalog, brand);
        return new R<List<SpuInfoVo>>().ok().put( list);
    }

    @PostMapping("/getProduct/{spuId}")
    @ApiOperation(value = "通过spuId获取整个商品信息")
    public R<ProductVo> infoProduct(@PathVariable @ApiParam(value = "商品spuId") Long spuId){
        ProductVo product = spuInfoService.getProduct(spuId);
        return new R<ProductVo>().ok().put(product);
    }


    /**
     * 列表
     */
    @PostMapping("/list")
    @ApiOperation(value = "分页查询商品spu信息")
    public R<PageUtils> list(@RequestParam @ApiParam(value = "分页搜索参数") SpuInfoQueryVo params){
        PageUtils page = spuInfoService.queryPageLimit(params);
        return new  R<PageUtils>().ok().put( page);
    }


    @PostMapping("/up/{spuId}")
    @ApiOperation(value = "上架商品")
    public R SpuUp(@PathVariable @ApiParam(value = "商品spuId") Long spuId){
        //TODO spu上架时，提交商品信息到ES
        this.spuInfoService.upSpu(spuId);
        return new R<>().ok();
    }

    @PostMapping("/down/{spuId}")
    @ApiOperation(value = "下架商品")
    public R SpuDown(@PathVariable @ApiParam(value = "商品spuId") Long spuId){
        this.spuInfoService.downSpu(spuId);
        return new R<>().ok();
    }


    /**
     * 信息
     */
    @PostMapping("/info/{id}")
    @ApiOperation(value = "查询商品spu信息")
    public R<SpuInfoVo> info(@PathVariable("id") @ApiParam(value = "商品spuId") Long id){
		SpuInfoEntity spuInfo = spuInfoService.getById(id);
        SpuInfoVo spuInfoVo=new SpuInfoVo();
        BeanUtils.copyProperties(spuInfo,spuInfoVo);
        return new  R<SpuInfoVo>().ok().put(spuInfoVo);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation("新增商品")
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
    @ApiOperation(value = "修改商品spu信息")
    public R update(@Valid @RequestBody @ApiParam(value = "商品spu信息") SpuInfoVo spuInfoVo){
        SpuInfoEntity spuInfo=new SpuInfoEntity();
        BeanUtils.copyProperties(spuInfoVo,spuInfo);
		spuInfoService.updateById(spuInfo);
        return new R<>().ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除商品")
    public R delete(@RequestBody @ApiParam(value = "商品的spuId数组") Long[] ids){
		spuInfoService.deleteSpu(ids);
        return new R<>().ok();
    }

}
