package com.gzstarry.modules.controller.sys;

import com.gzstarry.common.annotation.SysLog;
import com.gzstarry.common.utils.R;
import com.gzstarry.common.validator.ValidatorUtils;
import com.gzstarry.entity.core.SysPermissionTree;
import com.gzstarry.entity.sys.SysPermissionEntity;
import com.gzstarry.modules.base.AbstractController;
import com.gzstarry.service.sys.SysPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 菜单管理
 *
 * @author MakerLin makerlin96@gmail.com
 */
@Api(tags="菜单管理")
@RestController
@RequestMapping("/sys/permission")
public class SysPermissionController extends AbstractController {

    @Autowired
    private SysPermissionService sysPermissionService;


    @GetMapping("/list")
    @ApiOperation("列表")
    @RequiresPermissions("sys:permission:list")
    public R list() {
        List<SysPermissionTree> list = sysPermissionService.getListTree();
        return R.ok().put("data", list);
    }


    @ApiOperation("菜单信息")
    @GetMapping("/info/{id}")
    @RequiresPermissions("sys:permission:info")
    public R info(@PathVariable("id") Long id){
        SysPermissionEntity data = sysPermissionService.getById(id);
        if(data.getParentId() != 0){
            SysPermissionEntity parent = sysPermissionService.getById(data.getParentId());
            data.setParentName(parent.getName());
        }
        return R.ok().put("data", data);
    }


    @SysLog("添加菜单")
    @PostMapping("/add")
    @RequiresPermissions("sys:permission:add")
    public R doAdd(@RequestBody SysPermissionEntity entity) {
        ValidatorUtils.validateEntity(entity);
        sysPermissionService.save(entity);
        return R.ok();
    }


    @SysLog("编辑菜单")
    @PostMapping("/edit")
    @RequiresPermissions("sys:permission:edit")
    public R doEdit(@RequestBody SysPermissionEntity entity) {
        ValidatorUtils.validateEntity(entity);
        sysPermissionService.updateById(entity);
        return R.ok();
    }


    @SysLog("删除菜单")
    @PostMapping("/del")
    @RequiresPermissions("sys:permission:del")
    public R del(@RequestBody Long[] id) {
        sysPermissionService.removeByIds(Arrays.asList(id));
        return R.ok();
    }

}
