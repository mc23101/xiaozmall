package com.zhangsisiyao.system.system.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zhangsisiyao.system.system.entity.HomeRecommendationEntity;
import com.zhangsisiyao.system.system.service.HomeRecommendationService;
import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.common.utils.R;



/**
 * 
 *
 * @author system
 * @email 2963456487@qq.com
 * @date 2023-03-10 16:22:36
 */
@RestController
@RequestMapping("system/homerecommendation")
public class HomeRecommendationController {
    @Autowired
    private HomeRecommendationService homeRecommendationService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("system:homerecommendation:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = homeRecommendationService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("system:homerecommendation:info")
    public R info(@PathVariable("id") Integer id){
		HomeRecommendationEntity homeRecommendation = homeRecommendationService.getById(id);

        return R.ok().put("homeRecommendation", homeRecommendation);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("system:homerecommendation:save")
    public R save(@RequestBody HomeRecommendationEntity homeRecommendation){
		homeRecommendationService.save(homeRecommendation);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("system:homerecommendation:update")
    public R update(@RequestBody HomeRecommendationEntity homeRecommendation){
		homeRecommendationService.updateById(homeRecommendation);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("system:homerecommendation:delete")
    public R delete(@RequestBody Integer[] ids){
		homeRecommendationService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
