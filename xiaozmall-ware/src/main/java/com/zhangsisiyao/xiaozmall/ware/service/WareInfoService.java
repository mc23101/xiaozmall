package com.zhangsisiyao.xiaozmall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.xiaozmall.ware.entity.WareInfoEntity;

import java.util.Map;

/**
 * 仓库信息
 *
 * @author zhangsisiyao
 * @email 2963456487@qq.com
 * @date 2023-02-17 19:33:49
 */
public interface WareInfoService extends IService<WareInfoEntity> {

    PageUtils queryWareInfoPage(Map<String, Object> params);
}

