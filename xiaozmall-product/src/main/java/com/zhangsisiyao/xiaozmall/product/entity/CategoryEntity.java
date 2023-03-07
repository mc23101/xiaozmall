package com.zhangsisiyao.xiaozmall.product.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.checkerframework.framework.qual.NoDefaultQualifierForUse;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

/**
 * 商品三级分类
 * 
 * @author zhangsisiyao
 * @email 2963456487@qq.com
 * @date 2023-02-17 23:30:14
 */
@Data
@TableName("pms_category")
public class CategoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 分类id
	 */
	@TableId
	@Min(value = 1)
	private Long catId;
	/**
	 * 分类名称
	 */
	@NotEmpty
	private String name;
	/**
	 * 父分类id
	 */
	@Min(value = 0)
	private Long parentCid;
	/**
	 * 层级
	 */
	@Min(value = 0)
	private Integer catLevel;
	/**
	 * 是否显示[0-不显示，1显示]
	 */

	@TableLogic(value = "1",delval = "0")
	@Min(value = 0)
	@Min(value = 1)
	private Integer showStatus;
	/**
	 * 排序
	 */
	@Min(value = 0)
	private Integer sort;
	/**
	 * 图标地址
	 */
	@NotEmpty
	private String icon;
	/**
	 * 计量单位
	 */
	@NotEmpty
	private String productUnit;
	/**
	 * 商品数量
	 */
	@Min(value = 0)
	private Integer productCount;


	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@TableField(exist = false)
	List<CategoryEntity> children;
}
