package com.zhangsisiyao.xiaozmall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * sku信息
 * 
 * @author zhangsisiyao
 * @email 2963456487@qq.com
 * @date 2023-02-17 23:30:14
 */
@Data
@TableName("pms_sku_info")
public class SkuInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * skuId
	 */
	@TableId
	@Min(value = 1)
	private Long skuId;
	/**
	 * spuId
	 */
	@Min(value = 1)
	private Long spuId;
	/**
	 * sku名称
	 */
	@NotEmpty
	private String skuName;
	/**
	 * sku介绍描述
	 */
	@NotEmpty
	private String skuDesc;
	/**
	 * 所属分类id
	 */
	@Min(value = 10)
	private Long catalogId;
	/**
	 * 品牌id
	 */
	@Min(value = 1)
	private Long brandId;
	/**
	 * 默认图片
	 */
	@NotEmpty
	private String skuDefaultImg;
	/**
	 * 标题
	 */
	@NotEmpty
	private String skuTitle;
	/**
	 * 副标题
	 */
	@NotEmpty
	private String skuSubtitle;
	/**
	 * 价格
	 */
	@NotNull
	private BigDecimal price;
	/**
	 * 销量
	 */
	@Min(value = 1)
	private Long saleCount;

}
