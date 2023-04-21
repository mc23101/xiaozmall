package com.zhangsisiyao.xiaozmall.search.entity;

import com.zhangsisiyao.common.vo.AttrValueVo;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class AttrGroupAttrValueEntity implements Serializable {
    /**
     * 分组id
     */
    private Long attrGroupId;
    /**
     * 组名
     */
    private String attrGroupName;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 描述
     */
    private String descript;
    /**
     * 组图标
     */
    private String icon;
    /**
     * 所属分类id
     */
    private Long catalogId;

    @Field(type = FieldType.Nested)
    private List<AttrValueVo> attrs=new ArrayList<>();
}
