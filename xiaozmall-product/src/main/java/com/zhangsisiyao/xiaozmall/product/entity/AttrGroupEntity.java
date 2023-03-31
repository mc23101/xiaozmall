package com.zhangsisiyao.xiaozmall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 
 * 
 * @author zhangsisiyao
 * @email 2963456487@qq.com
 * @date 2023-02-25 15:45:31
 */
@Data
@TableName("pms_attr_group")
public class AttrGroupEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 分组id
	 */
	@TableId
	@Min(value = 1)
	private Long attrGroupId;
	/**
	 * 组名
	 */
	@NotEmpty
	private String attrGroupName;
	/**
	 * 排序
	 */
	@Min(value = 0)
	private Integer sort;
	/**
	 * 描述
	 */
	@NotEmpty
	private String descript;
	/**
	 * 组图标
	 */
	@NotEmpty
	private String icon;
	/**
	 * 所属分类id
	 */
	@Min(value = 1)
	private Long catalogId;

}
