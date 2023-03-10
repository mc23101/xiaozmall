package com.zhangsisiyao.xiaozmall.ware.service.impl;

import com.zhangsisiyao.xiaozmall.ware.vo.WareSkuVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.common.utils.Query;

import com.zhangsisiyao.xiaozmall.ware.dao.WareSkuDao;
import com.zhangsisiyao.xiaozmall.ware.entity.WareSkuEntity;
import com.zhangsisiyao.xiaozmall.ware.service.WareSkuService;


@Service("wareSkuService")
public class WareSkuServiceImpl extends ServiceImpl<WareSkuDao, WareSkuEntity> implements WareSkuService {

    @Override
    public long getSkuCountById(String skuid) {
        AtomicLong result= new AtomicLong();
        List<WareSkuEntity> skus = this.query().eq("sku_id", skuid).list();
        skus.forEach((sku)->{
            if(sku.getStockLocked()==0){
                result.addAndGet(sku.getStock());
            }
        });
        return result.get();
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WareSkuEntity> page = this.page(
                new Query<WareSkuEntity>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryWareSkuPage(Map<String, String> params) {
        int limit=Integer.parseInt(params.get("limit"));
        int page=Integer.parseInt(params.get("page"));
        String wareId=params.get("wareId");
        String skuId=params.get("skuId");
        int totalCount=this.baseMapper.queryWareSkuCount(wareId,skuId);
        List<WareSkuVo> list = this.baseMapper.queryWareSkuPage(limit,page ,wareId,skuId);
        return new PageUtils(list,totalCount,limit,page);
    }

}