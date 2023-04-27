package com.zhangsisiyao.xiaozmall.product.vo.QueryVo;

import com.zhangsisiyao.common.vo.product.AttrVo;
import com.zhangsisiyao.common.vo.product.PageParamVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(description = "属性条件查询参数")
@Data
public class AttrQueryVo implements Serializable {
    @ApiModelProperty(value = "属性信息",position = 1)
    AttrVo attrVo=new AttrVo();

    @ApiModelProperty(value = "分页查询参数",position = 2)
    PageParamVo pageParams=new PageParamVo();

}
