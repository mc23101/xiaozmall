package com.zhangsisiyao.common.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class SkuAttrValueVo implements Serializable {

    Long attrId;

    String attrName;

    String attrValue;
}
