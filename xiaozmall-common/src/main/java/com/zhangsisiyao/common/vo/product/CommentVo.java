package com.zhangsisiyao.common.vo.product;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel(description = "商品评论")
public class CommentVo implements Serializable {

    @ApiModelProperty(value = "评论id")
    private Long id;

    @ApiModelProperty(value = "商品skuId",position = 1)
    private Long skuId;

    @ApiModelProperty(value = "商品spuId",position = 2)
    private Long spuId;

    @ApiModelProperty(value = "商品名字",position = 3)
    private String spuName;

    @ApiModelProperty(value = "会员昵称",position = 4)
    private String memberNickName;

    @ApiModelProperty(value = "会员星级",position = 5)
    private Integer star;

    @ApiModelProperty(value = "会员ip",position = 6)
    private String memberIp;

    @ApiModelProperty(value = "显示状态[0-不显示，1-显示]",position = 7)
    private Integer showStatus;

    @ApiModelProperty(value = "购买时属性组合",position = 8)
    private String spuAttributes;

    @ApiModelProperty(value = "点赞数",position = 9)
    private Integer likesCount;

    @ApiModelProperty(value = "回复数",position = 10)
    private Integer replyCount;

    @ApiModelProperty(value = "评论图片/视频[json数据；[{type:文件类型,url:资源路径}]]",position = 11)
    private String resources;

    @ApiModelProperty(value = "评论内容",position = 12)
    private String content;

    @ApiModelProperty(value = "用户头像",position = 13)
    private String memberIcon;

    @ApiModelProperty(value = "评论类型[0 - 对商品的直接评论，1 - 对评论的回复]",position = 14)
    private Integer commentType;
}
