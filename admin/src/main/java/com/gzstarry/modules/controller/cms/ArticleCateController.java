package com.gzstarry.modules.controller.cms;

import com.gzstarry.common.annotation.SysLog;
import com.gzstarry.common.constant.Constant;
import com.gzstarry.common.utils.PageUtils;
import com.gzstarry.common.utils.R;
import com.gzstarry.common.validator.ValidatorUtils;
import com.gzstarry.entity.cms.ArticleCateEntity;
import com.gzstarry.modules.base.AbstractController;
import com.gzstarry.service.cms.ArticleCateService;
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
 * 文章分类
 *
 * @author MakerLin makerlin96@gmail.com
 */
@Api(tags="文章分类")
@RestController
@RequestMapping("/cms/cate")
public class ArticleCateController extends AbstractController {

    @Autowired
    private ArticleCateService articleCateService;


    @ApiOperation("分类列表")
    @GetMapping("/list")
    @RequiresPermissions("cms:cate:list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "第几页", paramType = "query", dataType="int", required = true),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页几条", paramType = "query", dataType="int", required = true),
            @ApiImplicitParam(name = "cateName", value = "分类名称", paramType = "query", dataType="string")
    })
    public R list(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageUtils list = articleCateService.selectByPage(params);
        return R.ok().put("data", list);
    }


    @SysLog("添加分类")
    @PostMapping("/add")
    @RequiresPermissions("cms:cate:add")
    public R add(@RequestBody ArticleCateEntity entity) {
        ValidatorUtils.validateEntity(entity);
        entity.setCreateTime(new Date());
        articleCateService.save(entity);
        return R.ok();
    }


    @ApiOperation("分类信息")
    @GetMapping("/info/{cateId}")
    @RequiresPermissions("cms:cate:list")
    public R info(@PathVariable("cateId") Long cateId){
        ArticleCateEntity role = articleCateService.getById(cateId);
        return R.ok().put("data", role);
    }


    @SysLog("编辑分类")
    @PostMapping("/edit")
    @ApiOperation("编辑分类")
    @RequiresPermissions("cms:cate:edit")
    public R edit(@RequestBody ArticleCateEntity entity) {
        ValidatorUtils.validateEntity(entity);
        articleCateService.updateById(entity);
        return R.ok();
    }


    @SysLog("删除分类")
    @PostMapping("/del")
    @ApiOperation("删除分类")
    @RequiresPermissions("cms:cate:del")
    public R delete(@RequestBody Long[] ids) {
        articleCateService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }
}

