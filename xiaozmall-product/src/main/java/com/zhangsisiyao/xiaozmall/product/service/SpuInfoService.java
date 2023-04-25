package com.zhangsisiyao.xiaozmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangsisiyao.common.utils.PageUtils;

import com.zhangsisiyao.xiaozmall.product.entity.SpuInfoEntity;
import com.zhangsisiyao.common.vo.product.ProductVo;

import java.util.List;
import java.util.Map;

/**
 * spu信息
 *
 * @author zhangsisiyao
 * @email 2963456487@qq.com
 * @date 2023-02-17 23:30:14
 */
public interface SpuInfoService extends IService<SpuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryPageLimit(Map<String, Object> params);

    boolean saveProduct(ProductVo product);

    ProductVo getProduct(Long spuId);
    
    void upSpu(Long spuId);

    void downSpu(Long spuId);

    void deleteSpu(Long[] ids);

    List<SpuInfoEntity> getWithCatalogAndBrand(String catalog,String brand);
}

