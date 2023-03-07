package com.zhangsisiyao.xiaozmall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

/**
 * 品牌分类关联
 * 
 * @author zhangsisiyao
 * @email 2963456487@qq.com
 * @date 2023-02-26 09:32:23
 */
@Data
@TableName("pms_category_brand_relation")
public class CategoryBrandRelationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	@Min(value = 1)
	private Long id;
	/**
	 * 品牌id
	 */
	@Min(value = 1)
	private Long brandId;
	/**
	 * 分类id
	 */
	@Min(value = 1)
	private Long catalogId;
	/**
	 * 
	 */
	@NotEmpty
	private String brandName;
	/**
	 * 
	 */
	@NotEmpty
	private String catalogName;

}
