package com.zhangsisiyao.xiaozmall.product.dao;

import com.zhangsisiyao.xiaozmall.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author zhangsisiyao
 * @email 2963456487@qq.com
 * @date 2023-02-17 23:30:14
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
