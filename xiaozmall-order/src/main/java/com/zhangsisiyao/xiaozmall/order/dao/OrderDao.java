package com.zhangsisiyao.xiaozmall.order.dao;

import com.zhangsisiyao.xiaozmall.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 * 
 * @author zhangsisiyao
 * @email 2963456487@qq.com
 * @date 2023-02-17 20:09:17
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}
