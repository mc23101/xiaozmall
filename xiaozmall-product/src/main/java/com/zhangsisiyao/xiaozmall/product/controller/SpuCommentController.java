package com.zhangsisiyao.xiaozmall.product.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.common.utils.R;
import com.zhangsisiyao.common.vo.product.CommentVo;
import com.zhangsisiyao.common.vo.product.PageParamVo;
import com.zhangsisiyao.xiaozmall.product.entity.SpuCommentEntity;
import com.zhangsisiyao.xiaozmall.product.service.SpuCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * 商品评价
 *
 * @author zhangsisiyao
 * @email 2963456487@qq.com
 * @date 2023-02-17 23:30:14
 */
@RestController
@RequestMapping("product/spucomment")
@Api(tags = "商品评论操作")
@ApiSupport(order = 4)
public class SpuCommentController {

    @Autowired
    private SpuCommentService spuCommentService;

    /**
     * 列表
     */
    @PostMapping("/list")
    @ApiOperation(value = "分页查询商品评论")
    public R<PageUtils> list(@RequestParam @ApiParam(value = "分页查询参数") PageParamVo params){
        PageUtils page = spuCommentService.queryPage(params);

        return new R<PageUtils>().ok().put( page);
    }


    /**
     * 信息
     */
    @PostMapping("/info/{id}")
    @ApiOperation(value = "查询商品评论信息")
    public R<CommentVo> info(@PathVariable("id") @ApiParam(value = "评论Id") Long id){
		SpuCommentEntity spuComment = spuCommentService.getById(id);
        CommentVo commentVo=new CommentVo();
        BeanUtils.copyProperties(spuCommentService,commentVo);
        return new R<CommentVo>().ok().put(commentVo);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增商品评论")
    public R save(@RequestBody @ApiParam(value = "商品评论信息") CommentVo commentVo){
        SpuCommentEntity spuComment=new SpuCommentEntity();
        BeanUtils.copyProperties(commentVo,spuComment);
		spuCommentService.save(spuComment);
        return new R<>().ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改商品评论")
    public R update(@RequestBody @ApiParam(value = "商品评论信息") CommentVo commentVo){
        SpuCommentEntity spuComment=new SpuCommentEntity();
        BeanUtils.copyProperties(commentVo,spuComment);
		spuCommentService.updateById(spuComment);
        return new R<>().ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation("删除商品评论")
    public R delete(@RequestBody @ApiParam("商品评论id数组") Long[] ids){
		spuCommentService.removeByIds(Arrays.asList(ids));

        return new R<>().ok();
    }

}
