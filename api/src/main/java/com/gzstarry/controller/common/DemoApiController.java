package com.gzstarry.controller.common;

import cn.hutool.extra.qrcode.QrCodeUtil;
import com.gzstarry.common.utils.R;
import com.gzstarry.common.utils.RedisUtils;
import com.gzstarry.common.validator.Assert;
import com.gzstarry.entity.sys.SysOssEntity;
import com.gzstarry.entity.sys.SysUserEntity;
import com.gzstarry.oss.OSSFactory;
import com.gzstarry.service.sys.SysOssService;
import com.gzstarry.service.sys.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * 测试接口
 *
 * @author MakerLin makerlin96@gmail.com
 */
@Api(tags="测试接口")
@RestController
@RequestMapping("/demo")
public class DemoApiController extends AbstractApiController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysOssService sysOssService;
    @Autowired
    private RedisUtils redisUtils;


    @ApiOperation("上传文件")
    @PostMapping("/upload")
    public R upload(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            return R.error("上传文件不能为空");
        }

        //上传文件
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String url = OSSFactory.build().uploadSuffix(file.getBytes(), suffix);

        //保存文件信息
        SysOssEntity entity = new SysOssEntity();
        entity.setUrl(url);
        entity.setCreateTime(new Date());
        sysOssService.save(entity);
        logger.info("文件上传成功，链接：{}", url);
        return R.ok().put("url", url);
    }


    @ApiOperation("设置Redis")
    @GetMapping("/setRedis")
    public R setRedis(){
        List<SysUserEntity> sysUserEntity = sysUserService.list();
        redisUtils.set("test", sysUserEntity);
        return R.ok();
    }

    @ApiOperation("设置Redis")
    @GetMapping("/getRedis")
    public R getRedis(){
        Object value = redisUtils.get("test");
        return R.ok().put("data",value);
    }



    @ApiOperation("生成二维码")
    @GetMapping("/qrCode")
    public void qrCode(HttpServletResponse response,
         @ApiParam(name="content",value="内容",required=true)@RequestParam(value="content")String content) throws IOException {
        BufferedImage image = QrCodeUtil.generate(content, 300, 300);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        out.close();
    }


    @ApiOperation("测试数据校验")
    @GetMapping("/testAssert")
    public R testAssert(
            @ApiParam(name="account",value="账号")@RequestParam(value="account",required=false)String account,
            @ApiParam(name="password",value="密码")@RequestParam(value="password",required=false)String password
    ){
        Assert.isBlank(account,"账号不能为空");
        Assert.isBlank(password,"密码不能为空");
        return R.ok("校验通过");
    }


    @ApiOperation("测试动态数据源")
    @GetMapping("/dynamicDataSource")
    public R dynamicDataSource(){
        List<SysUserEntity> list = sysUserService.dynamicDataSource();
        return R.ok().put("data",list);
    }

}
