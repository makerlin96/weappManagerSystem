package com.gzstarry.modules.controller.cms;

import com.gzstarry.common.annotation.SysLog;
import com.gzstarry.common.constant.Constant;
import com.gzstarry.common.utils.PageUtils;
import com.gzstarry.common.utils.R;
import com.gzstarry.common.validator.ValidatorUtils;
import com.gzstarry.entity.cms.ArticleEntity;
import com.gzstarry.modules.base.AbstractController;
import com.gzstarry.service.cms.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

/**
 * 文章管理
 *
 * @author MakerLin makerlin96@gmail.com
 */
@Api(tags="文章管理")
@RestController
@RequestMapping("/cms/article")
public class ArticleController extends AbstractController {

    @Autowired
    private ArticleService articleService;


    @ApiOperation("文章列表")
    @GetMapping("/list")
    @RequiresPermissions("cms:article:list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "第几页", paramType = "query", dataType="int", required = true),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页几条", paramType = "query", dataType="int", required = true),
            @ApiImplicitParam(name = "title", value = "标题", paramType = "query", dataType="string"),
            @ApiImplicitParam(name = "status", value = "状态", paramType = "query", dataType="string"),
            @ApiImplicitParam(name = "startDate", value = "开始时间", paramType = "query", dataType="string"),
            @ApiImplicitParam(name = "endDate", value = "结束时间", paramType = "query", dataType="string")
    })
    public R list(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageUtils list = articleService.selectByPage(params);
        return R.ok().put("data", list);
    }


    @SysLog("添加文章")
    @PostMapping("/add")
    @ApiOperation("添加文章")
    @RequiresPermissions("cms:article:add")
    public R add(@RequestBody ArticleEntity entity) {
        ValidatorUtils.validateEntity(entity);
        entity.setCreateTime(new Date());
        entity.setWriter(getUser().getRealName());
        entity.setStatus(Constant.OPEN);
        articleService.save(entity);
        return R.ok();
    }


    @SysLog("编辑文章")
    @PostMapping("/edit")
    @ApiOperation("编辑文章")
    @RequiresPermissions("cms:article:edit")
    public R edit(@RequestBody ArticleEntity entity) {
        ValidatorUtils.validateEntity(entity);
        articleService.updateById(entity);
        return R.ok();
    }


    @ApiOperation("文章信息")
    @GetMapping("/info/{id}")
    @RequiresPermissions("cms:article:list")
    public R info(@PathVariable("id") Integer id) {
        ArticleEntity article = articleService.getById(id);
        return R.ok().put("data", article);
    }


    @SysLog("删除文章")
    @PostMapping("/del")
    @ApiOperation("删除文章")
    @RequiresPermissions("cms:article:del")
    public R del(@RequestBody Long[] ids) {
        articleService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
