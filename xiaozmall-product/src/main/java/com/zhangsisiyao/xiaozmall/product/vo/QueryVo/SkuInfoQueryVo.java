package com.zhangsisiyao.xiaozmall.product.vo.QueryVo;

import com.zhangsisiyao.common.vo.product.PageParamVo;
import com.zhangsisiyao.common.vo.product.SkuInfoVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ApiModel(description = "sku信息条件查询参数")
public class SkuInfoQueryVo implements Serializable {
    @ApiModelProperty(value = "sku信息")
    SkuInfoVo skuInfoVo;

    @ApiModelProperty(value = "分页查询参数",position = 4)
    PageParamVo pageParamVo=new PageParamVo();

}
