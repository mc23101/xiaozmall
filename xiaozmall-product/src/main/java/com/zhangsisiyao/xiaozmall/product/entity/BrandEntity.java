package com.zhangsisiyao.xiaozmall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 品牌
 * 
 * @author zhangsisiyao
 * @email 2963456487@qq.com
 * @date 2023-02-17 23:30:14
 */
@Data
@TableName("pms_brand")
public class BrandEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 品牌id
	 */
	@TableId
	@Min(value = 1)
	private Long brandId;

	@NotEmpty
	private String name;
	/**
	 * 品牌logo地址
	 */
	@NotEmpty
	private String logo;
	/**
	 * 介绍
	 */
	@NotEmpty
	private String descript;
	/**
	 * 显示状态[0-不显示；1-显示]
	 */
	@Max(value = 1)
	@Min(value = 0)
	private Integer showStatus;
	/**
	 * 检索首字母
	 */
	@NotEmpty
	private String firstLetter;
	/**
	 * 排序
	 */
	@Min(value = 0)
	private Integer sort;

}
