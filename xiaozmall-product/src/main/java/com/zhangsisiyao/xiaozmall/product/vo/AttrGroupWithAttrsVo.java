package com.zhangsisiyao.xiaozmall.product.vo;

import com.zhangsisiyao.xiaozmall.product.entity.AttrEntity;
import com.zhangsisiyao.xiaozmall.product.entity.AttrGroupEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AttrGroupWithAttrsVo {


    public AttrGroupWithAttrsVo(AttrGroupEntity groupEntity) {
        this.attrGroupId=groupEntity.getAttrGroupId();
        this.attrGroupName=groupEntity.getAttrGroupName();
        this.sort=groupEntity.getSort();
        this.descript=groupEntity.getDescript();
        this.icon=groupEntity.getIcon();
        this.catelogId=groupEntity.getCatalogId();
    }

    public AttrGroupWithAttrsVo() {
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
    private Long catelogId;

    private List<AttrEntity> attrs=new ArrayList<>();
}
