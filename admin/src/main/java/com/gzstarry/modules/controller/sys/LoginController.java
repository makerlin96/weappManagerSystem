package com.gzstarry.modules.controller.sys;

import cn.hutool.crypto.SecureUtil;
import com.gzstarry.common.constant.Constant;
import com.gzstarry.common.utils.JwtUtils;
import com.gzstarry.common.utils.R;
import com.gzstarry.common.utils.RedisUtils;
import com.gzstarry.entity.sys.SysLoginEntity;
import com.gzstarry.entity.sys.SysUserEntity;
import com.gzstarry.modules.base.AbstractController;
import com.gzstarry.service.sys.SysUserService;
import com.gzstarry.shiro.ShiroUtils;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录接口
 *
 * @author MakerLin makerlin96@gmail.com
 */
@Slf4j
@Api(tags="登录接口")
@RestController
public class LoginController extends AbstractController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private Producer producer;

    @ApiOperation(value = "验证码", produces="application/octet-stream")
    @GetMapping("/captcha")
    public void captcha(HttpServletResponse response) throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        //生成文字验证码
        String text = producer.createText();
        //生成图片验证码
        BufferedImage image = producer.createImage(text);
        //保存到shiro session
        ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        out.close();
    }

    @ApiOperation("登录")
    @PostMapping("/login")
    public R login(@RequestBody SysLoginEntity sysLoginEntity) {
        try {
            ///String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
            ///if (!sysLoginEntity.getCaptcha().equalsIgnoreCase(kaptcha)) {
            ///    return R.error("验证码不正确");
            ///}
            SysUserEntity user = sysUserService.selectByUsername(sysLoginEntity.getUsername());
            //判断账号是否已经存在
            if(user == null){
                return R.error("账户不存在");
            }
            //判断用户输入的密码是否正确
            String pwd = SecureUtil.sha256(sysLoginEntity.getPassword());
            if(!pwd.equals(user.getPassword())){
                return R.error("账号或密码错误");
            }
            if(user.getStatus() == Constant.OPEN){
                user.setLastLoginTime(new Date());
                if(sysUserService.updateById(user)){
                    Map<String, Object> map = new HashMap<>(16);
                    String token = jwtUtils.createToken(String.valueOf(user.getUserId()));
                    redisUtils.set("lunhui:sysUser:" + user.getUserId(), token, jwtUtils.getExpire());
                    map.put("token", token);
                    map.put("expire", jwtUtils.getExpire());
                    return R.ok("登录成功").put("data",map);
                }else{
                    return R.error("登录失败");
                }
            }else{
                return R.error("账号已被锁定,请联系管理员!");
            }
        }catch (Exception e){
            log.error(">>>>>>>>登录异常,信息:",e);
            return R.error(e.getMessage());
        }
    }


    @PostMapping("/logout")
    public R logout() {
        ShiroUtils.logout();
        return R.ok();
    }

}
