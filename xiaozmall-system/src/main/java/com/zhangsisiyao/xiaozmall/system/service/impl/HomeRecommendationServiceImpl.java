package com.zhangsisiyao.system.system.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.common.utils.Query;

import com.zhangsisiyao.system.system.dao.HomeRecommendationDao;
import com.zhangsisiyao.system.system.entity.HomeRecommendationEntity;
import com.zhangsisiyao.system.system.service.HomeRecommendationService;


@Service("homeRecommendationService")
public class HomeRecommendationServiceImpl extends ServiceImpl<HomeRecommendationDao, HomeRecommendationEntity> implements HomeRecommendationService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<HomeRecommendationEntity> page = this.page(
                new Query<HomeRecommendationEntity>().getPage(params),
                new QueryWrapper<HomeRecommendationEntity>()
        );

        return new PageUtils(page);
    }

}