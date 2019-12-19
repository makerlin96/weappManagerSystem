package com.gzstarry.modules.controller.cms;

import com.gzstarry.common.annotation.SysLog;
import com.gzstarry.common.constant.Constant;
import com.gzstarry.common.utils.PageUtils;
import com.gzstarry.common.utils.R;
import com.gzstarry.common.validator.ValidatorUtils;
import com.gzstarry.entity.cms.BannerEntity;
import com.gzstarry.entity.sys.SysDictEntity;
import com.gzstarry.modules.base.AbstractController;
import com.gzstarry.service.cms.BannerService;
import com.gzstarry.service.sys.SysDictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import java.util.*;

/**
 * 广告管理
 *
 * @author MakerLin makerlin96@gmail.com
 */
@Api(tags="广告管理")
@RestController
@RequestMapping("/cms/banner")
public class BannerController extends AbstractController {

    @Autowired
    private BannerService bannerService;
    @Autowired
    private SysDictService sysDictService;


    @ApiOperation("文章列表")
    @GetMapping("/list")
    @RequiresPermissions("cms:banner:list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "第几页", paramType = "query", dataType="int", required = true),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页几条", paramType = "query", dataType="int", required = true),
            @ApiImplicitParam(name = "title", value = "标题", paramType = "query", dataType="string"),
            @ApiImplicitParam(name = "status", value = "状态", paramType = "query", dataType="string"),
            @ApiImplicitParam(name = "startDate", value = "开始时间", paramType = "query", dataType="string"),
            @ApiImplicitParam(name = "endDate", value = "结束时间", paramType = "query", dataType="string")
    })
    public R list(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageUtils list = bannerService.selectByPage(params);
        return R.ok().put("data", list);
    }


    @SysLog("添加广告")
    @PostMapping("/add")
    @ApiOperation("添加广告")
    @RequiresPermissions("cms:banner:add")
    public R add(@RequestBody BannerEntity entity) {
        ValidatorUtils.validateEntity(entity);
        entity.setCreateTime(new Date());
        entity.setStatus(Constant.OPEN);
        bannerService.save(entity);
        return R.ok();
    }


    @SysLog("编辑广告")
    @PostMapping("/edit")
    @ApiOperation("编辑广告")
    @RequiresPermissions("cms:banner:edit")
    public R edit(@RequestBody BannerEntity entity) {
        ValidatorUtils.validateEntity(entity);
        bannerService.updateById(entity);
        return R.ok();
    }


    @ApiOperation("广告信息")
    @GetMapping("/info/{id}")
    @RequiresPermissions("cms:banner:list")
    public R info(@PathVariable("id") Integer id) {
        BannerEntity data = bannerService.getById(id);
        return R.ok().put("data", data);
    }


    @ApiOperation("广告分类")
    @GetMapping("/bannerCate")
    public R bannerCate() {
        List<SysDictEntity> data = sysDictService.selectByKey("BANNER_CATE");
        return R.ok().put("data", data);
    }


    @SysLog("删除广告")
    @PostMapping("/del")
    @ApiOperation("删除广告")
    @RequiresPermissions("cms:banner:del")
    public R del(@RequestBody Long[] ids) {
        bannerService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}

