package com.zhangsisiyao.xiaozmall.product.vo.QueryVo;

import com.zhangsisiyao.common.vo.product.CatalogVo;
import com.zhangsisiyao.common.vo.product.PageParamVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "商品分类品牌关联信息条件查询参数")
public class CatalogBrandRelationQueryVo implements Serializable {

    @ApiModelProperty(value = "商品分类品牌关联信息")
    CatalogVo.CatalogBrandRelationVo catalogBrandRelationVo;

    @ApiModelProperty(value = "分页查询参数",position = 2)
    PageParamVo pageParamVo=new PageParamVo();
}
