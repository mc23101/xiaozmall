package com.zhangsisiyao.xiaozmall.search.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseAttrValueVo implements Serializable {
    Long attrId;

    String attrValues;

    Integer showDesc;
}