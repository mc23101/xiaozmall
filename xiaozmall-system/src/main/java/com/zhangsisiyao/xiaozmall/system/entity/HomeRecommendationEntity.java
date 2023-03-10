package com.zhangsisiyao.system.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author system
 * @email 2963456487@qq.com
 * @date 2023-03-10 16:22:36
 */
@Data
@TableName("home_recommendation")
public class HomeRecommendationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 推荐id
	 */
	@TableId
	private Integer id;
	/**
	 * 展示图片url
	 */
	private String imageUrl;
	/**
	 * 点击转跳页面url
	 */
	private String openUrl;
	/**
	 * 是否启用
	 */
	private Integer enable;
	/**
	 * 页面转跳类型0-覆盖 1-新窗口
	 */
	private Integer openType;

}
