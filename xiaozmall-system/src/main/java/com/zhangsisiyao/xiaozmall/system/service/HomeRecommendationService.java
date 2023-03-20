package com.zhangsisiyao.xiaozmall.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.xiaozmall.system.entity.HomeRecommendationEntity;

import java.util.Map;

/**
 * 
 *
 * @author system
 * @email 2963456487@qq.com
 * @date 2023-03-10 16:22:36
 */
public interface HomeRecommendationService extends IService<HomeRecommendationEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

