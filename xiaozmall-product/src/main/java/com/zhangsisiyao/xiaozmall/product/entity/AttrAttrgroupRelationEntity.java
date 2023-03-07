package com.zhangsisiyao.xiaozmall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 属性&属性分组关联
 * 
 * @author zhangsisiyao
 * @email 2963456487@qq.com
 * @date 2023-02-26 09:32:23
 */
@Data
@TableName("pms_attr_attrgroup_relation")
public class AttrAttrgroupRelationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	@Min(value = 1)
	private Long id;
	/**
	 * 属性id
	 */
	@Min(value = 1)
	private Long attrId;
	/**
	 * 属性分组id
	 */
	@Min(value = 1)
	private Long attrGroupId;
	/**
	 * 属性组内排序
	 */
	@Min(value = 0)
	private Integer attrSort;

}
