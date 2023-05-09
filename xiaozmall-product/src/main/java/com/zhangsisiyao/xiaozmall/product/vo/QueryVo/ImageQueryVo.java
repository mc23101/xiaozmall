package com.zhangsisiyao.xiaozmall.product.vo.QueryVo;

import com.zhangsisiyao.common.vo.product.ImageVo;
import com.zhangsisiyao.common.vo.PageParamVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "图片条件查询参数")
public class ImageQueryVo implements Serializable {

    @ApiModelProperty(value = "图片信息")
    ImageVo imageVo;

    @ApiModelProperty(value = "分页查询参数")
    PageParamVo pageParamVo=new PageParamVo();
}
