/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.zhangsisiyao.common.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.http.HttpStatus;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 *
 * @author Mark sunlightcs@gmail.com
 */
@ApiModel(description = "请求页面的返回结果")
@Data
public class R <T> implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "响应消息")
	private String msg="";

	@ApiModelProperty(value = "响应编码")
	private int code=0;

	@ApiModelProperty(value = "响应数据")
	private T data;

	public R() {
		this.code=0;
		this.msg="success";
	}
	
	public  R<T> error() {
		return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常，请联系管理员");
	}
	
	public  R<T> error(String msg) {
		return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
	}
	
	public  R<T> error(int code, String msg) {
		this.code=code;
		this.msg=msg;
		return this;
	}

	public R<T> ok(String msg) {
		this.msg=msg;
		return this;
	}

	public R<T> put(T data) {
		this.data=data;
		return this;
	}
	
	public R<T> ok() {
		return ok("success");
	}

}
