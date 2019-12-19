package com.gzstarry.modules.controller.sys;

import com.gzstarry.common.annotation.SysLog;
import com.gzstarry.common.utils.R;
import com.gzstarry.common.validator.ValidatorUtils;
import com.gzstarry.entity.core.SysDictTree;
import com.gzstarry.entity.sys.SysDictEntity;
import com.gzstarry.modules.base.AbstractController;
import com.gzstarry.service.sys.SysDictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 词典管理
 *
 * @author MakerLin makerlin96@gmail.com
 */
@Api(tags="词典管理")
@RestController
@RequestMapping("/sys/dict")
public class SysDictController extends AbstractController {

    @Autowired
    private SysDictService sysDictService;

    @GetMapping("/list")
    @ApiOperation("词典列表")
    @RequiresPermissions("sys:dict:list")
    public R list() {
        List<SysDictTree> list = sysDictService.getListTree();
        return R.ok().put("data",list);
    }


    @SysLog("添加词典")
    @PostMapping("/add")
    @ApiOperation("添加词典")
    @RequiresPermissions("sys:dict:add")
    public R add(@RequestBody SysDictEntity entity) {
        //效验数据
        ValidatorUtils.validateEntity(entity);
        sysDictService.save(entity);
        return R.ok();
    }


    @SysLog("编辑词典")
    @PutMapping("/edit")
    @ApiOperation("编辑词典")
    @RequiresPermissions("sys:dict:edit")
    public R edit(@RequestBody SysDictEntity entity) {
        //效验数据
        ValidatorUtils.validateEntity(entity);
        sysDictService.updateById(entity);
        return R.ok();
    }


    @ApiOperation("获取词典信息")
    @GetMapping("/info/{id}")
    @RequiresPermissions("sys:role:list")
    public R info(@PathVariable("id") Long id){
        SysDictEntity dict = sysDictService.getById(id);
        if(dict.getParentId() != 0){
            SysDictEntity parent = sysDictService.getById(dict.getParentId());
            dict.setParentName(parent.getName());
        }
        return R.ok().put("data", dict);
    }


    @SysLog("删除词典")
    @PostMapping("/del")
    @ApiOperation("删除词典")
    @RequiresPermissions("sys:dict:del")
    public R del(@RequestBody Long[] id) {
        sysDictService.removeByIds(Arrays.asList(id));
        return R.ok();
    }

}

