package com.zhangsisiyao.xiaozmall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

import javax.validation.constraints.*;

/**
 * spu属性值
 * 
 * @author zhangsisiyao
 * @email 2963456487@qq.com
 * @date 2023-02-17 23:30:14
 */
@Data
@TableName("pms_product_attr_value")
public class ProductAttrValueEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	@Min(value=1)
	private Long id;
	/**
	 * 商品id
	 */
	@Min(value = 1)
	private Long spuId;
	/**
	 * 属性id
	 */
	@Min(value = 1)
	private Long attrId;
	/**
	 * 属性名
	 */
	@NotEmpty
	private String attrName;
	/**
	 * 属性值
	 */
	@NotEmpty
	private String attrValue;
	/**
	 * 顺序
	 */
	@Min(value = 0)
	private Integer attrSort;
	/**
	 * 快速展示【是否展示在介绍上；0-否 1-是】
	 */
	@Min(value = 0)
	@Max(value = 1)
	private Integer quickShow;

}
