package com.zhangsisiyao.xiaozmall.product.vo.QueryVo;

import com.zhangsisiyao.common.vo.product.AttrVo;
import com.zhangsisiyao.common.vo.product.PageParamVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "属性值条件查询参数")
public class AttrValueQueryVo implements Serializable {

    @ApiModelProperty(value = "属性值信息")
    AttrVo.AttrValueVo attrValueVo;

    @ApiModelProperty(value = "分页查询参数",position = 1)
    PageParamVo pageParamVo=new PageParamVo();
}
