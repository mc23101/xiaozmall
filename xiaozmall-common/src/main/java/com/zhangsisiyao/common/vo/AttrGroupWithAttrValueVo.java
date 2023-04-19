package com.zhangsisiyao.common.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AttrGroupWithAttrValueVo {


    public AttrGroupWithAttrValueVo() {
    }

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

    private List<AttrValueVo> attrs=new ArrayList<>();
}
