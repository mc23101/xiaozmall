package com.zhangsisiyao.xiaozmall.search.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 商品属性
 * 
 * @author zhangsisiyao
 * @email 2963456487@qq.com
 * @date 2023-02-17 23:30:14
 */
@Data
public class AttrVo implements Serializable {
	private static final long serialVersionUID = 1L;


	private Long attrId;
	/**
	 * 属性名
	 */

	private String attrName;
	/**
	 * 是否需要检索[0-不需要，1-需要]
	 */

	private Integer searchType;
	/**
	 * 属性图标
	 */

	private String icon;
	/**
	 * 可选值列表[用逗号分隔]
	 */

	private String valueSelect;
	/**
	 * 属性类型[0-销售属性，1-基本属性，2-既是销售属性又是基本属性]
	 */

	private Integer attrType;
	/**
	 * 启用状态[0 - 禁用，1 - 启用]
	 */

	private Long enable;

	private Long catalogId;

	private Integer showDesc;

}
