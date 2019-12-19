package com.gzstarry.modules.controller.sys;

import com.gzstarry.common.annotation.SysLog;
import com.gzstarry.common.constant.Constant;
import com.gzstarry.common.utils.PageUtils;
import com.gzstarry.common.utils.R;
import com.gzstarry.entity.sys.ScheduleJobEntity;
import com.gzstarry.modules.base.AbstractController;
import com.gzstarry.service.sys.ScheduleJobLogService;
import com.gzstarry.service.sys.ScheduleJobService;
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
 * 定时任务
 *
 * @author MakerLin makerlin96@gmail.com
 */
@Api(tags="定时任务")
@RestController
@RequestMapping("/sys/job")
public class ScheduleJobController extends AbstractController {
    @Autowired
    private ScheduleJobService scheduleJobService;
    @Autowired
    private ScheduleJobLogService scheduleJobLogService;

    /**
     * 定时任务列表
     */
    @ApiOperation("定时任务列表")
    @GetMapping("/list")
    @RequiresPermissions("sys:job:list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页", paramType = "query", dataType="int", required = true),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页记录数", paramType = "query", dataType="int", required = true),
            @ApiImplicitParam(name = "beanName", value = "bean名称", paramType = "query", dataType="string")
    })
    public R list(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageUtils list = scheduleJobService.selectByPage(params);
        return R.ok().put("data", list);
    }


    @PostMapping("/add")
    @SysLog("创建定时任务")
    @ApiOperation("创建定时任务")
    @RequiresPermissions("sys:job:add")
    public R add(@RequestBody ScheduleJobEntity scheduleJobEntity) {
        scheduleJobService.saveJob(scheduleJobEntity);
        return R.ok();
    }


    @ApiOperation("获取信息")
    @GetMapping("/info/{jobId}")
    @RequiresPermissions("sys:job:list")
    public R info(@PathVariable("jobId") Long jobId) {
        ScheduleJobEntity job = scheduleJobService.getById(jobId);
        return R.ok().put("data", job);
    }


    @SysLog("修改定时任务")
    @PostMapping("/edit")
    @RequiresPermissions("sys:job:edit")
    public R edit(@RequestBody ScheduleJobEntity model) {
        scheduleJobService.updateById(model);
        return R.ok();
    }


    @PostMapping("/del")
    @SysLog("删除定时任务")
    @ApiOperation("删除用户")
    @RequiresPermissions("sys:job:del")
    public R del(@RequestBody Long[] ids) {
        scheduleJobService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }


    @SysLog("立即运行")
    @ApiOperation("立即运行")
    @PutMapping("/run/{jobId}")
    @RequiresPermissions("sys:job:run")
    public R run(@PathVariable("jobId") Long jobId) {
        scheduleJobService.run(jobId);
        return R.ok();
    }

    @SysLog("暂停任务")
    @ApiOperation("暂停任务")
    @PutMapping("/pause/{jobId}")
    @RequiresPermissions("sys:job:run")
    public R pause(@PathVariable("jobId") Long jobId) {
        scheduleJobService.pause(jobId);
        return R.ok();
    }


    @SysLog("恢复任务")
    @ApiOperation("恢复任务")
    @PutMapping("/resume/{jobId}")
    @RequiresPermissions("sys:job:resume")
    public R resume(@PathVariable("jobId") Long jobId) {
        scheduleJobService.resume(jobId);
        return R.ok();
    }


    @ApiOperation("任务日志列表")
    @GetMapping("/log")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "第几页", paramType = "query", dataType="Integer", required = true),
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页几条", paramType = "query", dataType="Integer", required = true),
    })
    public R logList(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageUtils list = scheduleJobLogService.selectByPage(params);
        return R.ok().put("data", list);
    }


    @SysLog("删除任务日志")
    @ApiOperation("删除任务日志")
    @PostMapping("/logDel")
    public R logDel(@RequestBody Long[] ids) {
        scheduleJobLogService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}