package com.zhangsisiyao.xiaozmall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

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
	/**
	 * 属性值
	 */
	@NotEmpty
	private String attrValue;

}
