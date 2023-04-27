package com.zhangsisiyao.xiaozmall.product.vo.QueryVo;

import com.zhangsisiyao.common.vo.product.CommentVo;
import com.zhangsisiyao.common.vo.product.PageParamVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "商品评论条件查询参数")
public class CommentQueryVo implements Serializable {

    @ApiModelProperty(value = "商品评论信息")
    CommentVo commentVo;

    @ApiModelProperty(value = "分页查询参数",position = 1)
    PageParamVo pageParamVo=new PageParamVo();
}
