package com.zhangsisiyao.xiaozmall.product.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class SkuAttrVo implements Serializable {

    Long attrId;
    String attrName;
    String attrValue;
}
