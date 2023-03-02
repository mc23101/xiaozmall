package com.zhangsisiyao.xiaozmall.product.dao;

import com.zhangsisiyao.xiaozmall.product.entity.AttrEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 商品属性
 * 
 * @author zhangsisiyao
 * @email 2963456487@qq.com
 * @date 2023-02-17 23:30:14
 */
@Mapper
public interface AttrDao extends BaseMapper<AttrEntity> {
    public List<AttrEntity> queryWithAttrGroup(String groupID);
}
