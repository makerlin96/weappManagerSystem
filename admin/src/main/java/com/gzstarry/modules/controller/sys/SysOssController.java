package com.gzstarry.modules.controller.sys;

import com.gzstarry.common.annotation.SysLog;
import com.gzstarry.common.constant.ConfigConstant;
import com.gzstarry.common.constant.Constant;
import com.gzstarry.common.utils.PageUtils;
import com.gzstarry.common.utils.R;
import com.gzstarry.common.validator.ValidatorUtils;
// import com.gzstarry.common.validator.group.*;
import com.gzstarry.entity.sys.SysOssEntity;
import com.gzstarry.modules.base.AbstractController;
import com.gzstarry.oss.CloudStorageConfig;
import com.gzstarry.oss.OSSFactory;
import com.gzstarry.service.sys.SysConfigService;
import com.gzstarry.service.sys.SysOssService;
import com.google.gson.Gson;
import com.gzstarry.common.validator.group.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FilenameUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

/**
 * 文件上传配置
 *
 * @author MakerLin makerlin96@gmail.com
 */
@Api(tags="文件上传配置")
@RestController
@RequestMapping("/sys/oss")
public class SysOssController extends AbstractController {

    @Autowired
    private SysOssService sysOssService;
    @Autowired
    private SysConfigService sysConfigService;

    private final static String KEY = ConfigConstant.CLOUD_STORAGE_CONFIG_KEY;

    @ApiOperation("文件列表")
    @GetMapping("/list")
    @RequiresPermissions("sys:oss:list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "第几页", paramType = "query", dataType="int", required = true),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页几条", paramType = "query", dataType="int", required = true)
    })
    public R list(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageUtils list = sysOssService.selectByPage(params);
        return R.ok().put("data", list);
    }


    @ApiOperation("文件配置信息")
    @GetMapping("/config")
    @RequiresPermissions("sys:oss:list")
    public R config(){
        CloudStorageConfig config = sysConfigService.getConfigObject(KEY, CloudStorageConfig.class);
        return R.ok().put("data", config);
    }


    @ApiOperation("保存上传配置信息")
    @PostMapping("/save")
    @RequiresPermissions("sys:oss:save")
    public R saveConfig(@RequestBody CloudStorageConfig config){
        //校验类型
        ValidatorUtils.validateEntity(config);
        if(config.getType() == Constant.CloudService.LOCAL.getValue()){
            //校验本地存储数据
            ValidatorUtils.validateEntity(config, LocalGroup.class);
        }else if(config.getType() == Constant.CloudService.QINIU.getValue()){
            //校验七牛云数据
            ValidatorUtils.validateEntity(config, QiniuGroup.class);
        }else if(config.getType() == Constant.CloudService.ALIYUN.getValue()){
            //校验阿里云数据
            ValidatorUtils.validateEntity(config, AliyunGroup.class);
        }else if(config.getType() == Constant.CloudService.QCLOUD.getValue()){
            //校验腾讯云数据
            ValidatorUtils.validateEntity(config, QcloudGroup.class);
        }
        else if(config.getType() == Constant.CloudService.FASTDFS.getValue()){
            //校验腾讯云数据
            ValidatorUtils.validateEntity(config, FastDFSGroup.class);
        }
        sysConfigService.updateValueByKey(KEY, new Gson().toJson(config));
        return R.ok();
    }


    @ApiOperation("上传文件")
    @PostMapping("/upload")
    @RequiresPermissions("sys:oss:list")
    public R upload(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            return R.error("上传文件不能为空");
        }
        //获取文件后缀名
        String suffix = FilenameUtils.getExtension(file.getOriginalFilename());
        //上传文件
        String url = OSSFactory.build().uploadSuffix(file.getBytes(), suffix);
        //保存文件信息
        SysOssEntity ossEntity = new SysOssEntity();
        ossEntity.setUrl(url);
        ossEntity.setCreateTime(new Date());
        sysOssService.save(ossEntity);
        return R.ok("上传成功").put("url", url);
    }


    @SysLog("删除文件")
    @PostMapping("/del")
    @ApiOperation("删除文件")
    @RequiresPermissions("sys:oss:del")
    public R del(@RequestBody Long[] ids) {
        sysOssService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}

