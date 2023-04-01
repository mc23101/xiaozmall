package com.zhangsisiyao.xiaozmall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * sku图片
 * 
 * @author zhangsisiyao
 * @email 2963456487@qq.com
 * @date 2023-02-17 23:30:14
 */
@Data
@TableName("pms_sku_images")
public class SkuImagesEntity implements Serializable {
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
	 * 图片地址
	 */
	@NotEmpty
	private String imgUrl;
	/**
	 * 排序
	 */
	@Min(value = 0)
	private Integer imgSort;

}
