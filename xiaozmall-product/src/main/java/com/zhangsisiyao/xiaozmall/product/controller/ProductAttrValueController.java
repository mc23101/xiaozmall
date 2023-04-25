package com.zhangsisiyao.xiaozmall.product.controller;

import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.common.utils.R;
import com.zhangsisiyao.common.vo.product.AttrVo;
import com.zhangsisiyao.xiaozmall.product.entity.ProductAttrValueEntity;
import com.zhangsisiyao.xiaozmall.product.service.ProductAttrValueService;
import com.zhangsisiyao.common.vo.product.PageParamVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


/**
 * spu属性值
 *
 * @author zhangsisiyao
 * @email 2963456487@qq.com
 * @date 2023-02-17 23:30:14
 */
@RestController
@RequestMapping("product/productattrvalue")
@Api(tags = "Spu属性值操作")
public class ProductAttrValueController {
    @Autowired
    private ProductAttrValueService productAttrValueService;

    /**
     * 列表
     */
    @PostMapping("/list")
    @ApiOperation(value = "分页查询spu属性值信息")
    public R<PageUtils> list(@RequestParam @ApiParam("分页查询参数") PageParamVo params){
        PageUtils page = productAttrValueService.queryPage(params);

        return new R<PageUtils>().ok().put( page);
    }

    @PostMapping("/list/{spuId}")
    @ApiOperation("通过SpuId查询关联的属性值")
    public R<List<AttrVo.AttrValueVo>> productattrvalueWithSpu(@PathVariable @ApiParam(value = "商品spuId") String spuId){
        List<AttrVo.AttrValueVo> list = productAttrValueService.queryBySpuId(spuId);
        return new R<List<AttrVo.AttrValueVo>>().ok().put(list);
    }


    /**
     * 信息
     */
    @PostMapping("/info/{id}")
    @ApiOperation(value = "查询spu属性值信息")
    public R<AttrVo.AttrValueVo> info(@PathVariable("id") @ApiParam(value = "spu属性值Id") Long id){
		ProductAttrValueEntity productAttrValue = productAttrValueService.getById(id);
        AttrVo.AttrValueVo attrValueVo=new AttrVo.AttrValueVo();
        BeanUtils.copyProperties(productAttrValue,attrValueVo);
        return new  R<AttrVo.AttrValueVo>().ok().put(attrValueVo);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增Spu属性值")
    public R save(@RequestBody @ApiParam(value = "Spu属性值信息") AttrVo.AttrValueVo attrValueVo){
        ProductAttrValueEntity productAttrValue=new ProductAttrValueEntity();
        BeanUtils.copyProperties(attrValueVo,productAttrValue);
		productAttrValueService.save(productAttrValue);
        return new R<>().ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "更新Spu属性值")
    public R update(@RequestBody @ApiParam(value = "Spu属性值信息") AttrVo.AttrValueVo attrValueVo){
        ProductAttrValueEntity productAttrValue=new ProductAttrValueEntity();
        BeanUtils.copyProperties(attrValueVo,productAttrValue);
		productAttrValueService.updateById(productAttrValue);
        return new R<>().ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除Spu属性值")
    public R delete(@RequestBody @ApiParam(value = "spu属性值Id数组") Long[] ids){
		productAttrValueService.removeByIds(Arrays.asList(ids));
        return new R<>().ok();
    }

}
