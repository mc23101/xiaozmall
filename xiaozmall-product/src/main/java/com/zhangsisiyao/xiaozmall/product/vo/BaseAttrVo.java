package com.zhangsisiyao.xiaozmall.product.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseAttrVo implements Serializable {
    Long attrId;

    String attrValues;

    Integer showDesc;
}