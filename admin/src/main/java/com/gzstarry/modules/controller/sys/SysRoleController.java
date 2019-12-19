package com.gzstarry.modules.controller.sys;

import com.gzstarry.common.annotation.SysLog;
import com.gzstarry.common.constant.Constant;
import com.gzstarry.common.utils.PageUtils;
import com.gzstarry.common.utils.R;
import com.gzstarry.common.validator.ValidatorUtils;
import com.gzstarry.entity.sys.SysRoleEntity;
import com.gzstarry.modules.base.AbstractController;
import com.gzstarry.service.sys.SysRolePermissionService;
import com.gzstarry.service.sys.SysRoleService;
import io.swagger.annotations.*;
import org.apache.commons.lang.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.*;

/**
 * 角色管理
 *
 * @author MakerLin makerlin96@gmail.com
 */
@Api(tags="角色管理")
@RestController
@RequestMapping("/sys/role")
public class SysRoleController extends AbstractController {

    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysRolePermissionService sysRolePermissionService;


    @ApiOperation("角色列表")
    @GetMapping("/list")
    @RequiresPermissions("sys:role:list")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "第几页", paramType = "query", dataType="int", required = true),
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页几条", paramType = "query", dataType="int", required = true),
        @ApiImplicitParam(name = "roleName", value = "角色名", paramType = "query", dataType="string")
    })
    public R list(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageUtils list = sysRoleService.selectByPage(params);
        return R.ok().put("data", list);
    }


    @ApiOperation("获取全部角色")
    @GetMapping("/getRoleList")
    public R getRoleList() {
        List<SysRoleEntity> list = sysRoleService.list();
        return R.ok().put("data", list);
    }


    @SysLog("添加角色")
    @PostMapping("/add")
    @RequiresPermissions("sys:role:add")
    public R add(@RequestBody SysRoleEntity entity) {
        ValidatorUtils.validateEntity(entity);
        entity.setCreateTime(new Date());
        sysRoleService.saveRole(entity);
        return R.ok();
    }


    @ApiOperation("获取角色信息")
    @GetMapping("/info/{roleId}")
    @RequiresPermissions("sys:role:list")
    public R info(@PathVariable("roleId") Long roleId){
        SysRoleEntity role = sysRoleService.getById(roleId);
        List<Long> list = sysRolePermissionService.getPermissionListByRoleId(roleId);
        role.setPermissionIdList(list);
        return R.ok().put("data", role);
    }


    @SysLog("编辑角色")
    @PostMapping("/edit")
    @RequiresPermissions("sys:role:edit")
    public R edit(@RequestBody SysRoleEntity entity) {
        ValidatorUtils.validateEntity(entity);
        sysRoleService.updateRole(entity);
        return R.ok();
    }


    @SysLog("删除角色")
    @PostMapping("/del")
    @ApiOperation("删除角色")
    @RequiresPermissions("sys:role:del")
    public R delete(@RequestBody Long[] ids) {
        if(ArrayUtils.contains(ids, 1L)){
            return R.error("超级管理员角色不能删除");
        }
        sysRoleService.delete(ids);
        return R.ok();
    }

}
