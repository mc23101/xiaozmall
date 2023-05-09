package com.zhangsisiyao.common.vo.member;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel(description = "会员信息")
public class MemberVo implements Serializable {

    @ApiModelProperty(value = "会员id")
    private Long id;

    @ApiModelProperty(value = "会员等级id",position = 1)
    private Long levelId;

    @ApiModelProperty(value = "用户名",position = 2)
    private String username;

    @ApiModelProperty(value = "密码",position = 3)
    private String password;

    @ApiModelProperty(value = "昵称",position = 4)
    private String nickname;

    @ApiModelProperty(value = "手机号码",position = 5)
    private String mobile;

    @ApiModelProperty(value = "邮箱",position = 6)
    private String email;

    @ApiModelProperty(value = "头像",position = 7)
    private String header;

    @ApiModelProperty(value = "性别",position = 8)
    private Integer gender;

    @ApiModelProperty(value = "生日",position = 9)
    private Date birth;

    @ApiModelProperty(value = "所在城市",position = 10)
    private String city;

    @ApiModelProperty(value = "职业",position = 11)
    private String job;

    @ApiModelProperty(value = "个性签名",position = 12)
    private String sign;

    @ApiModelProperty(value = "用户来源",position = 13)
    private Integer sourceType;

    @ApiModelProperty(value = "积分",position = 14)
    private Integer integration;

    @ApiModelProperty(value = "成长值",position = 15)
    private Integer growth;

    @ApiModelProperty(value = "启用状态",position = 16)
    private Integer status;

    @ApiModelProperty(value = "注册时间",position = 17)
    private Date createTime;
}
