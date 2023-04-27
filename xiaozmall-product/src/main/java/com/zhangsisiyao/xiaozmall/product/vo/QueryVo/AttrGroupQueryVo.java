package com.zhangsisiyao.xiaozmall.product.vo.QueryVo;

import com.zhangsisiyao.common.vo.product.AttrGroupVo;
import com.zhangsisiyao.common.vo.product.PageParamVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@ApiModel(description = "属性分组条件查询参数")
public class AttrGroupQueryVo implements Serializable {

    @ApiModelProperty(value = "属性分组信息")
    AttrGroupVo attrGroupVo;

    @ApiModelProperty(value = "分页查询参数",position = 1)
    PageParamVo pageParams=new PageParamVo();


}
