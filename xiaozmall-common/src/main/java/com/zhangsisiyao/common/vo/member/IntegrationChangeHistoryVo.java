package com.zhangsisiyao.common.vo.member;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel(description = "会员积分变化历史记录")
public class IntegrationChangeHistoryVo implements Serializable {

    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "会员id",position = 1)
    private Long memberId;

    @ApiModelProperty(value = "创建时间",position = 2)
    private Date createTime;

    @ApiModelProperty(value = "变化的值（正负计数）",position = 3)
    private Integer changeCount;

    @ApiModelProperty(value = "备注",position = 4)
    private String note;

    @ApiModelProperty(value = "来源[0->购物；1->管理员修改;2->活动]",position = 5)
    private Integer sourceType;
}
