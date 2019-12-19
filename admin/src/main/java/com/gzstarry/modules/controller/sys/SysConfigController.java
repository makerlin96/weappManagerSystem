package com.gzstarry.modules.controller.sys;

import com.gzstarry.common.annotation.SysLog;
import com.gzstarry.common.constant.Constant;
import com.gzstarry.common.utils.PageUtils;
import com.gzstarry.common.utils.R;
import com.gzstarry.common.validator.ValidatorUtils;
import com.gzstarry.entity.sys.SysConfigEntity;
import com.gzstarry.modules.base.AbstractController;
import com.gzstarry.service.sys.SysConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Arrays;
import java.util.Map;

/**
 * 系统配置信息
 *
 * @author MakerLin makerlin96@gmail.com
 */
@Api(tags="系统配置信息")
@RestController
@RequestMapping("/sys/config")
public class SysConfigController extends AbstractController {

    @Autowired
    private SysConfigService sysConfigService;

    @ApiOperation("配置列表")
    @GetMapping("/list")
    @RequiresPermissions("sys:config:list")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "第几页", paramType = "query", dataType="int", required = true),
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页几条", paramType = "query", dataType="int", required = true),
        @ApiImplicitParam(name = "roleName", value = "角色名", paramType = "query", dataType="string")
    })
    public R list(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageUtils list = sysConfigService.selectByPage(params);
        return R.ok().put("data", list);
    }


    @ApiOperation("配置信息")
    @GetMapping("/info/{id}")
    @RequiresPermissions("sys:config:list")
    public R detail(@PathVariable("id") Integer id) {
        SysConfigEntity config = sysConfigService.getById(id);
        return R.ok().put("data", config);
    }


    @SysLog("添加配置")
    @PostMapping("/add")
    @RequiresPermissions("sys:config:add")
    public R add(@RequestBody SysConfigEntity entity) {
        ValidatorUtils.validateEntity(entity);
        entity.setStatus(1);
        sysConfigService.saveConfig(entity);
        return R.ok();
    }


    @SysLog("编辑配置")
    @PostMapping("/edit")
    @RequiresPermissions("sys:config:edit")
    public R edit(@RequestBody SysConfigEntity entity) {
        ValidatorUtils.validateEntity(entity);
        sysConfigService.updateConfig(entity);
        return R.ok();
    }


    @SysLog("删除配置")
    @PostMapping("/del")
    @RequiresPermissions("sys:config:del")
    public R delete(@RequestBody Long[] ids){
        sysConfigService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
