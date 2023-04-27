package com.zhangsisiyao.xiaozmall.product.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.common.utils.R;
import com.zhangsisiyao.common.vo.product.ImageVo;
import com.zhangsisiyao.common.vo.product.PageParamVo;
import com.zhangsisiyao.xiaozmall.product.entity.SpuImagesEntity;
import com.zhangsisiyao.xiaozmall.product.service.SpuImagesService;
import com.zhangsisiyao.xiaozmall.product.vo.QueryVo.ImageQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
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
@ApiSupport(order = 8)
public class SpuImagesController {
    @Autowired
    private SpuImagesService spuImagesService;

    /**
     * 列表
     */
    @PostMapping("/list")
    @ApiOperation(value = "分页查询spu图片")
    public R<PageUtils> list(@RequestParam @ApiParam(value = "分页查询参数") ImageQueryVo params){
        PageUtils page = spuImagesService.queryPage(params);
        return new  R<PageUtils>().ok().put( page);
    }


    /**
     * 信息
     */
    @PostMapping("/info/{id}")
    @ApiOperation(value = "查询spu图片")
    public R<ImageVo> info(@PathVariable("id") @ApiParam(value = "spu图片id") Long id){
		SpuImagesEntity spuImages = spuImagesService.getById(id);
        ImageVo imageVo=new ImageVo();
        BeanUtils.copyProperties(spuImages,imageVo);
        return new  R<ImageVo>().ok().put(imageVo);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增spu图片")
    public R save(@RequestBody @ApiParam(value = "spu图片信息") ImageVo imageVo){
        SpuImagesEntity spuImages=new SpuImagesEntity();
        BeanUtils.copyProperties(imageVo,spuImages);
		spuImagesService.save(spuImages);
        return new R<>().ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改spu图片")
    public R update(@RequestBody @ApiParam(value = "spu图片信息") ImageVo imageVo){
        SpuImagesEntity spuImages=new SpuImagesEntity();
        BeanUtils.copyProperties(imageVo,spuImages);
		spuImagesService.updateById(spuImages);
        return new R<>().ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除spu图片")
    public R delete(@RequestBody @ApiParam(value = "spu图片id数组") Long[] ids){
		spuImagesService.removeByIds(Arrays.asList(ids));
        return new R<>().ok();
    }

}
