package com.zhangsisiyao.xiaozmall.product.vo.QueryVo;

import com.zhangsisiyao.common.vo.product.PageParamVo;
import com.zhangsisiyao.common.vo.product.SpuInfoVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@ApiModel(description = "spu条件查询参数")
public class SpuInfoQueryVo implements Serializable {

    @ApiModelProperty(value = "spu信息")
    SpuInfoVo spuInfoVo;

    @ApiModelProperty(value = "分页查询参数",position = 1)
    PageParamVo pageParams=new PageParamVo();
}
