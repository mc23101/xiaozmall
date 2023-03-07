package com.zhangsisiyao.xiaozmall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

/**
 * sku销售属性&值
 * 
 * @author zhangsisiyao
 * @email 2963456487@qq.com
 * @date 2023-02-17 23:30:14
 */
@Data
@TableName("pms_sku_sale_attr_value")
public class SkuSaleAttrValueEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	@Min(value = 1)
	private Long id;
	/**
	 * sku_id
	 */
	@Min(value = 1)
	private Long skuId;
	/**
	 * attr_id
	 */
	@Min(value = 1)
	private Long attrId;
	/**
	 * 销售属性名
	 */
	@NotEmpty
	private String attrName;
	/**
	 * 销售属性值
	 */
	@NotEmpty
	private String attrValue;
	/**
	 * 顺序
	 */
	@Min(value = 0)
	private Integer attrSort;

}
