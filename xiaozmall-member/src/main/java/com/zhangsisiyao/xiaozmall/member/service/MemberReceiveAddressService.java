package com.zhangsisiyao.xiaozmall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.xiaozmall.member.entity.MemberReceiveAddressEntity;

import java.util.Map;

/**
 * 会员收货地址
 *
 * @author zhangsisiyao
 * @email 2963456487@qq.com
 * @date 2023-02-17 20:02:54
 */
public interface MemberReceiveAddressService extends IService<MemberReceiveAddressEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

