package com.zhangsisiyao.xiaozmall.search.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class SkuAttrValueVo implements Serializable {

    Long attrId;

    String attrName;

    String attrValue;
}
