package com.zhangsisiyao.xiaozmall.ware.dao;

import com.zhangsisiyao.xiaozmall.ware.entity.WareSkuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhangsisiyao.xiaozmall.ware.vo.WareSkuVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 商品库存
 * 
 * @author zhangsisiyao
 * @email 2963456487@qq.com
 * @date 2023-02-17 19:33:49
 */
@Mapper
public interface WareSkuDao extends BaseMapper<WareSkuEntity> {
    List<WareSkuVo> queryWareSkuPage(Integer limit,Integer page,String wareId,String skuId);

    int queryWareSkuCount(String wareId,String skuId);
}
