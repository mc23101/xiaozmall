package com.zhangsisiyao.common.vo.member;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "会员收藏的专题活动")
public class MemberCollectSubject implements Serializable {

    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "会员id",position = 1)
    private Long memberId;

    @ApiModelProperty(value = "活动id",position = 2)
    private Long subjectId;

    @ApiModelProperty(value = "活动名称",position = 3)
    private String subjectName;

    @ApiModelProperty(value = "活动图片",position = 4)
    private String subjectImg;

    @ApiModelProperty(value = "活动url",position = 5)
    private String subjectUrl;
}
