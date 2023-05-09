package com.zhangsisiyao.common.vo.member;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel(description = "会员商品收藏夹")
public class MemberCollectSpuVo implements Serializable {

    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "会员id",position = 1)
    private Long memberId;

    @ApiModelProperty(value = "商品spuId",position = 2)
    private Long spuId;

    @ApiModelProperty(value = "商品spu名称",position = 3)
    private String spuName;

    @ApiModelProperty(value = "商品spu默认图片",position = 4)
    private String spuImg;

    @ApiModelProperty(value = "创建时间",position = 5)
    private Date createTime;
}
