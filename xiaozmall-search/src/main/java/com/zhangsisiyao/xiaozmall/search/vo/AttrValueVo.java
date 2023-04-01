package com.zhangsisiyao.xiaozmall.search.vo;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

@Data
public class AttrValueVo implements Serializable {
    Long id;

    Long attrId;

    @Field(type = FieldType.Text)
    String attrValue;

    Integer showDesc;
}