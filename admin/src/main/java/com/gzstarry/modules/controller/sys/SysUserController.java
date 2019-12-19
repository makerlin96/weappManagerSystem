package com.gzstarry.modules.controller.sys;

import com.gzstarry.common.annotation.SysLog;
import com.gzstarry.common.constant.Constant;
import com.gzstarry.common.utils.ExcelUtils;
import com.gzstarry.common.utils.PageUtils;
import com.gzstarry.common.utils.R;
import com.gzstarry.common.validator.ValidatorUtils;
import com.gzstarry.entity.sys.SysPermissionEntity;
import com.gzstarry.entity.sys.SysUserEntity;
import com.gzstarry.entity.sys.SysUserExcelEntity;
import com.gzstarry.modules.base.AbstractController;
import com.gzstarry.service.sys.SysPermissionService;
import com.gzstarry.service.sys.SysRoleService;
import com.gzstarry.service.sys.SysUserRoleService;
import com.gzstarry.service.sys.SysUserService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 用户管理
 *
 * @author MakerLin makerlin96@gmail.com
 */
@Slf4j
@Api(tags="系统用户")
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends AbstractController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysPermissionService sysPermissionService;


    @ApiOperation("用户列表")
    @GetMapping("/list")
    @RequiresPermissions("sys:user:list")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "第几页", paramType = "query", dataType="int", required = true),
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页几条", paramType = "query", dataType="int", required = true),
        @ApiImplicitParam(name = "username", value = "用户名", paramType = "query", dataType="string"),
        @ApiImplicitParam(name = "mobile", value = "手机号", paramType = "query", dataType="string"),
        @ApiImplicitParam(name = "startDate", value = "开始时间", paramType = "query", dataType="string"),
        @ApiImplicitParam(name = "endDate", value = "结束时间", paramType = "query", dataType="string")
    })
    public R list(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageUtils list = sysUserService.selectByPage(params);
        return R.ok().put("data", list);
    }


    @ApiOperation("获取用户信息")
    @GetMapping("/info/{userId}")
    @RequiresPermissions("sys:user:info")
    public R info(@PathVariable("userId") Long userId){
        SysUserEntity user = sysUserService.getById(userId);
        //用户角色列表
        List<Long> roleIdList = sysUserRoleService.getRoleIdList(userId);
        user.setRoleIdList(roleIdList);
        return R.ok().put("data", user);
    }


    @ApiOperation("获取用户权限")
    @GetMapping("/getRouters")
    public R getRouters(){
        Long userId = getUserId();
        Map<String, Object> map = new HashMap<>(16);
        SysUserEntity user = sysUserService.getById(userId);
        //获取用户角色
        Set<String> roleSet = sysRoleService.getUserRoles(userId);
        //获取用户可以操作的菜单列表
        List<SysPermissionEntity> menus = sysPermissionService.getLeftNav(user,userId);
        //获取用户可以操作的按钮列表
        Set<String> buttons = sysPermissionService.getUserButtonsList(user,userId);
        map.put("data", user);
        map.put("routers", menus);
        map.put("roles", roleSet);
        map.put("buttons", buttons);
        return R.ok(map);
    }


    @SysLog("添加用户")
    @PostMapping("/add")
    @ApiOperation("添加用户")
    @RequiresPermissions("sys:user:add")
    public R add(@RequestBody SysUserEntity entity) {
        ValidatorUtils.validateEntity(entity);
        sysUserService.saveUser(entity);
        return R.ok();
    }


    @SysLog("编辑用户")
    @PostMapping("/edit")
    @ApiOperation("编辑用户")
    @RequiresPermissions("sys:user:edit")
    public R edit(@RequestBody SysUserEntity entity) {
        ValidatorUtils.validateEntity(entity);
        sysUserService.updateUser(entity);
        return R.ok();
    }


    @ApiOperation("导出用户")
    @GetMapping("/export")
    @RequiresPermissions("sys:user:list")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "username", value = "用户名", paramType = "query", dataType="string"),
        @ApiImplicitParam(name = "mobile", value = "手机号", paramType = "query", dataType="string"),
        @ApiImplicitParam(name = "startDate", value = "开始时间", paramType = "query", dataType="string"),
        @ApiImplicitParam(name = "endDate", value = "结束时间", paramType = "query", dataType="string")
    })
    public void export(HttpServletResponse response,@ApiIgnore @RequestParam Map<String, Object> params) {
        try {
            List<SysUserExcelEntity> list = sysUserService.exportExcel(params);
            ExcelUtils.exportExcel(list, "用户信息", "用户", SysUserExcelEntity.class, "用户列表", response);
        } catch (Exception e){
            log.error(">>>>>>>>导出列表异常,信息:",e.getMessage());
        }
    }


    @SysLog("删除用户")
    @PostMapping("/del")
    @ApiOperation("删除用户")
    @RequiresPermissions("sys:user:del")
    public R del(@RequestBody Long[] ids) {
        if(ArrayUtils.contains(ids, 1L)){
            return R.error("系统管理员不能删除");
        }
        if(ArrayUtils.contains(ids, getUserId())){
            return R.error("当前用户不能删除");
        }
        sysUserService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
