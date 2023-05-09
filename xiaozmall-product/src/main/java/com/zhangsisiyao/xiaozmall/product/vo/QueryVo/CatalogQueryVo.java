package com.zhangsisiyao.xiaozmall.product.vo.QueryVo;

import com.zhangsisiyao.common.vo.product.CatalogVo;
import com.zhangsisiyao.common.vo.PageParamVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "商品分类条件查询参数")
public class CatalogQueryVo implements Serializable {

    @ApiModelProperty(value = "商品分类信息")
    CatalogVo catalogVo;

    @ApiModelProperty(value = "分页查询参数")
    PageParamVo pageParamVo=new PageParamVo();

}
