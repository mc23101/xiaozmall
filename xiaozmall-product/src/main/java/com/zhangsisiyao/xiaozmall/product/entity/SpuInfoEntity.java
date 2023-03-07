package com.zhangsisiyao.xiaozmall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

import javax.validation.constraints.*;

/**
 * spu信息
 * 
 * @author zhangsisiyao
 * @email 2963456487@qq.com
 * @date 2023-02-17 23:30:14
 */
@Data
@TableName("pms_spu_info")
public class SpuInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 商品id
	 */
	@TableId
	@Min(value = 1)
	private Long id;
	/**
	 * 商品名称
	 */
	@NotEmpty
	private String spuName;
	/**
	 * 商品描述
	 */
	@NotEmpty
	private String spuDescription;
	/**
	 * 所属分类id
	 */
	@Min(value = 1)
	private Long catalogId;
	/**
	 * 品牌id
	 */
	@Min(value = 1)
	private Long brandId;
	/**
	 * 
	 */
	@NotNull
	private BigDecimal weight;
	/**
	 * 上架状态[0 - 下架，1 - 上架]
	 */
	@Min(value = 0)
	@Min(value = 1)
	private Integer publishStatus;
	/**
	 * 
	 */
	@NotNull
	private Date createTime;
	/**
	 * 
	 */
	@NotNull
	private Date updateTime;

}
