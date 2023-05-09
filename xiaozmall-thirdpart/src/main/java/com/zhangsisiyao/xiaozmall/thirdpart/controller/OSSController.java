package com.zhangsisiyao.xiaozmall.thirdpart.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.zhangsisiyao.common.utils.R;
import com.zhangsisiyao.xiaozmall.thirdpart.service.OSSService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("thirdpart/oss")
@Api("OSS储存操作")
public class OSSController {

    @Autowired
    OSSService ossService;

    @RequestMapping("/policy")
    protected R policy(){
        return ossService.policy();
    }


}
