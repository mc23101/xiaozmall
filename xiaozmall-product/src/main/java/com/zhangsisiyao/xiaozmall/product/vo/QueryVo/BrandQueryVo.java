package com.zhangsisiyao.xiaozmall.product.vo.QueryVo;

import com.zhangsisiyao.common.vo.product.BrandVo;
import com.zhangsisiyao.common.vo.PageParamVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "品牌条件查询参数")
public class BrandQueryVo implements Serializable {
    @ApiModelProperty(value = "品牌信息")
    BrandVo brandVo;

    @ApiModelProperty(value = "分页查询参数",position = 1)
    PageParamVo pageParamVo=new PageParamVo();
}
