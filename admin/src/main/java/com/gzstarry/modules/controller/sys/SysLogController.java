package com.gzstarry.modules.controller.sys;

import com.gzstarry.common.constant.Constant;
import com.gzstarry.common.utils.PageUtils;
import com.gzstarry.common.utils.R;
import com.gzstarry.entity.sys.SysLogEntity;
import com.gzstarry.modules.base.AbstractController;
import com.gzstarry.service.sys.SysLogService;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Arrays;
import java.util.Map;

/**
 * 系统日志
 *
 * @author MakerLin makerlin96@gmail.com
 */
@Api(tags="系统日志")
@RestController
@RequestMapping("/sys/log")
public class SysLogController extends AbstractController {

    @Autowired
    private SysLogService sysLogService;


    @ApiOperation("日志列表")
    @GetMapping("/list")
    @RequiresPermissions("sys:log:list")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "第几页", paramType = "query", dataType="int", required = true),
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页几条", paramType = "query", dataType="int", required = true),
        @ApiImplicitParam(name = "username", value = "用户名", paramType = "query", dataType="string"),
        @ApiImplicitParam(name = "startDate", value = "开始时间", paramType = "query", dataType="string"),
        @ApiImplicitParam(name = "endDate", value = "结束时间", paramType = "query", dataType="string")
    })
    public R list(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageUtils list = sysLogService.selectByPage(params);
        return R.ok().put("data", list);
    }


    @GetMapping("/info/{id}")
    @ApiOperation("日志详情")
    @RequiresPermissions("sys:log:info")
    public R detail(@PathVariable("id") Long id) {
        SysLogEntity log = sysLogService.getById(id);
        return R.ok().put("data",log);
    }


    @PostMapping("/del")
    @ApiOperation("删除日志")
    @RequiresPermissions("sys:log:del")
    public R del(@RequestBody Long[] ids) {
        sysLogService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}