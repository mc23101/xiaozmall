package com.zhangsisiyao.xiaozmall.product.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class AttrValueVo implements Serializable {
    Long id;

    Long attrId;

    String attrValue;

    Integer showDesc;
}