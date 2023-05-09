package com.zhangsisiyao.xiaozmall.product.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.common.utils.R;
import com.zhangsisiyao.common.vo.product.AttrVo;
import com.zhangsisiyao.xiaozmall.product.entity.SkuSaleAttrValueEntity;
import com.zhangsisiyao.xiaozmall.product.service.SkuSaleAttrValueService;
import com.zhangsisiyao.xiaozmall.product.vo.QueryVo.AttrValueQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


/**
 * sku销售属性&值
 *
 * @author zhangsisiyao
 * @email 2963456487@qq.com
 * @date 2023-02-17 23:30:14
 */
@RestController
@RequestMapping("product/skusaleattrvalue")
@Api(tags = "Sku属性值操作")
@ApiSupport(order = 11)
public class SkuSaleAttrValueController {
    @Autowired
    private SkuSaleAttrValueService skuSaleAttrValueService;

    /**
     * 列表
     */
    @PostMapping("/list")
    @ApiOperation(value = "分页查询sku属性值")
    public R<PageUtils> list(@RequestParam @ApiParam(value = "分页查询参数") AttrValueQueryVo params){
        PageUtils page = skuSaleAttrValueService.queryPage(params);
        return new R<PageUtils>().ok().put( page);
    }


    /**
     * 信息
     */
    @PostMapping("/info/{id}")
    @ApiOperation(value = "查询sku属性值")
    public R<AttrVo.AttrValueVo> info(@PathVariable("id") @ApiParam("sku属性值Id") Long id){
		SkuSaleAttrValueEntity skuSaleAttrValue = skuSaleAttrValueService.getById(id);
        AttrVo.AttrValueVo attrValueVo=new AttrVo.AttrValueVo();
        BeanUtils.copyProperties(skuSaleAttrValue,attrValueVo);
        return new  R<AttrVo.AttrValueVo>().ok().put(attrValueVo);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增sku属性值")
    public R save(@RequestBody @ApiParam(value = "sku属性值信息") AttrVo.AttrValueVo attrValueVo){
        SkuSaleAttrValueEntity skuSaleAttrValue=new SkuSaleAttrValueEntity();
        BeanUtils.copyProperties(attrValueVo,skuSaleAttrValue);
		skuSaleAttrValueService.save(skuSaleAttrValue);

        return new R<>().ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation("修改sku属性值")
    public R update(@RequestBody @ApiParam(value = "sku属性值信息") AttrVo.AttrValueVo attrValueVo){
        SkuSaleAttrValueEntity skuSaleAttrValue=new SkuSaleAttrValueEntity();
        BeanUtils.copyProperties(attrValueVo,skuSaleAttrValue);
		skuSaleAttrValueService.updateById(skuSaleAttrValue);
        return new R<>().ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除sku属性值")
    public R delete(@RequestBody @ApiParam("sku属性值id数组") Long[] ids){
		skuSaleAttrValueService.removeByIds(Arrays.asList(ids));
        return new R<>().ok();
    }

}
