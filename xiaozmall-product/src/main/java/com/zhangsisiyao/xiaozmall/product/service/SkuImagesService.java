package com.zhangsisiyao.xiaozmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.xiaozmall.product.entity.SkuImagesEntity;
import com.zhangsisiyao.xiaozmall.product.vo.QueryVo.ImageQueryVo;

/**
 * sku图片
 *
 * @author zhangsisiyao
 * @email 2963456487@qq.com
 * @date 2023-02-17 23:30:14
 */
public interface SkuImagesService extends IService<SkuImagesEntity> {

    PageUtils queryPage(ImageQueryVo params);
}

