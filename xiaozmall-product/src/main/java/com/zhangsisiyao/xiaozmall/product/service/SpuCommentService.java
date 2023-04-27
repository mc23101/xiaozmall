package com.zhangsisiyao.xiaozmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.common.vo.product.PageParamVo;
import com.zhangsisiyao.xiaozmall.product.entity.SpuCommentEntity;
import com.zhangsisiyao.xiaozmall.product.vo.QueryVo.CommentQueryVo;

import java.util.Map;

/**
 * 商品评价
 *
 * @author zhangsisiyao
 * @email 2963456487@qq.com
 * @date 2023-02-17 23:30:14
 */
public interface SpuCommentService extends IService<SpuCommentEntity> {

    PageUtils queryPage(CommentQueryVo params);
}

