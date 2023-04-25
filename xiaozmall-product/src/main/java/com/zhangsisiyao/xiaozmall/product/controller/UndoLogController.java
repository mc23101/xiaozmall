package com.zhangsisiyao.xiaozmall.product.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.zhangsisiyao.common.utils.PageUtils;
import com.zhangsisiyao.common.utils.R;
import com.zhangsisiyao.xiaozmall.product.entity.UndoLogEntity;
import com.zhangsisiyao.xiaozmall.product.service.UndoLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * 
 *
 * @author zhangsisiyao
 * @email 2963456487@qq.com
 * @date 2023-02-26 09:32:23
 */
@RestController
@RequestMapping("product/undolog")
@ApiSupport(order = 14)
public class UndoLogController {
    @Autowired
    private UndoLogService undoLogService;

    /**
     * 列表
     */
    @PostMapping("/list")
    //@RequiresPermissions("product:undolog:list")
    public R<PageUtils> list(@RequestParam Map<String, Object> params){
        PageUtils page = undoLogService.queryPage(params);

        return new  R<PageUtils>().ok().put( page);
    }


    /**
     * 信息
     */
    @PostMapping("/info/{id}")
    //@RequiresPermissions("product:undolog:info")
    public R<UndoLogEntity> info(@PathVariable("id") Long id){
		UndoLogEntity undoLog = undoLogService.getById(id);

        return new  R<UndoLogEntity>().ok().put(undoLog);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    //@RequiresPermissions("product:undolog:save")
    public R save(@RequestBody UndoLogEntity undoLog){
		undoLogService.save(undoLog);

        return new R<>().ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    //@RequiresPermissions("product:undolog:update")
    public R update(@RequestBody UndoLogEntity undoLog){
		undoLogService.updateById(undoLog);

        return new R<>().ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    //@RequiresPermissions("product:undolog:delete")
    public R delete(@RequestBody Long[] ids){
		undoLogService.removeByIds(Arrays.asList(ids));

        return new R<>().ok();
    }

}
