package com.zhangsisiyao.xiaozmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.xiaozmall.product.entity.SpuImagesEntity;
import com.zhangsisiyao.xiaozmall.product.vo.QueryVo.ImageQueryVo;

/**
 * spu图片
 *
 * @author zhangsisiyao
 * @email 2963456487@qq.com
 * @date 2023-02-17 23:30:14
 */
public interface SpuImagesService extends IService<SpuImagesEntity> {

    PageUtils queryPage(ImageQueryVo params);
}

