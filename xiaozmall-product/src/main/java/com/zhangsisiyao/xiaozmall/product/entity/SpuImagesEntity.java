package com.zhangsisiyao.xiaozmall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

/**
 * spu图片
 * 
 * @author zhangsisiyao
 * @email 2963456487@qq.com
 * @date 2023-02-17 23:30:14
 */
@Data
@TableName("pms_spu_images")
public class SpuImagesEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	@Min(value = 1)
	private Long id;
	/**
	 * spu_id
	 */
	@Min(value = 1)
	private Long spuId;
	/**
	 * 图片地址
	 */
	@NotEmpty
	private String imgUrl;
	/**
	 * 顺序
	 */
	@Min(value = 0)
	private Integer imgSort;
	/**
	 * 是否默认图
	 */
	@Min(value = 0)
	@Max(value = 1)
	private Integer defaultImg;

}
