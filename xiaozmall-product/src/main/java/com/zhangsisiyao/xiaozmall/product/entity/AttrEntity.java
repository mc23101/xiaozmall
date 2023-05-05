package com.zhangsisiyao.xiaozmall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 商品属性
 * 
 * @author zhangsisiyao
 * @email 2963456487@qq.com
 * @date 2023-02-17 23:30:14
 */
@Data
@TableName("pms_attr")
public class AttrEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 属性id
	 */
	@TableId
	@Min(value = 1)
	private Long attrId;
	/**
	 * 属性名
	 */
	@NotEmpty
	private String attrName;

	private Integer valueType;

	/**
	 * 是否需要检索[0-不需要，1-需要]
	 */
	@Max(value = 1)
	@Min(value = 0)
	private Integer searchType;
	/**
	 * 属性图标
	 */
	@NotEmpty
	private String icon;
	/**
	 * 可选值列表[用逗号分隔]
	 */
	@NotEmpty
	private String valueSelect;
	/**
	 * 属性类型[0-销售属性，1-基本属性，2-既是销售属性又是基本属性]
	 */
	@Max(value = 2)
	@Min(value = 0)
	private Integer attrType;
	/**
	 * 启用状态[0 - 禁用，1 - 启用]
	 */
	@Max(value = 1)
	@Min(value = 0)
	private Long enable;
	/**
	 * 所属分类
	 */
	@Min(value = 1)
	private Long catalogId;
	/**
	 * 快速展示【是否展示在介绍上；0-否 1-是】，在sku中仍然可以调整
	 */
	@Max(value = 1)
	@Min(value = 0)
	private Integer showDesc;

}
