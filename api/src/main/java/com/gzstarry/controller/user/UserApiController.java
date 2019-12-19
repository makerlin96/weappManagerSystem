package com.gzstarry.controller.user;

import cn.hutool.crypto.SecureUtil;
import com.gzstarry.annotation.AppLogin;
import com.gzstarry.annotation.AppLoginUser;
import com.gzstarry.common.constant.Constant;
import com.gzstarry.common.utils.JwtUtils;
import com.gzstarry.common.utils.PageUtils;
import com.gzstarry.common.utils.R;
import com.gzstarry.controller.common.AbstractApiController;
import com.gzstarry.entity.sys.SysUserEntity;
import com.gzstarry.service.sys.SysUserService;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户接口
 *
 * @author MakerLin makerlin96@gmail.com
 */
@Api(tags="用户接口")
@RestController
@RequestMapping("/user")
public class UserApiController extends AbstractApiController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private JwtUtils jwtUtils;


    /**
     * 登录
     * @return
     */
    @ApiOperation("登录")
    @PostMapping("/login")
    public R login(
         @ApiParam(required=true,name="account",value="账号")@RequestParam(value="account",required=false)String account,
         @ApiParam(required=true,name="password",value="密码")@RequestParam(value="password",required=false)String password
    ){
        try {
            SysUserEntity sysUserEntity = sysUserService.selectByUsername(account);
            //判断账号是否已经存在
            if(sysUserEntity == null){
                return R.error("账户不存在");
            }
            //判断用户输入的密码是否正确
            String pwd = SecureUtil.sha256(password);
            if(!pwd.equals(sysUserEntity.getPassword())){
                return R.error("账号或密码错误");
            }
            if(sysUserEntity.getStatus() == Constant.OPEN){
                sysUserEntity.setLastLoginTime(new Date());
                if(sysUserService.updateById(sysUserEntity)){
                    Map<String, Object> map = new HashMap<>();
                    String token = jwtUtils.createToken(String.valueOf(sysUserEntity.getUserId()));
                    map.put("token", token);
                    map.put("expire", jwtUtils.getExpire());
                    return R.ok(map);
                }else{
                    return R.error("登录失败");
                }
            }else{
                return R.error("账号已禁用");
            }
        }catch (Exception e){
            logger.error(">>>>>>>>登录异常,信息:",e);
            return R.error("登录异常");
        }
    }


    /**
     * 获取用户信息
     * @return
     */
    @AppLogin
    @GetMapping("/getUserInfo")
    @ApiOperation("获取用户信息")
    public R userInfo(HttpServletRequest request){
        try {
            //获取头部请求信息中的token  根据token获取userID
            String headValue = request.getHeader("Token");
            Claims claims = jwtUtils.getClaimByToken(headValue);
            String userId = claims.getSubject();
            SysUserEntity sysUserEntity = sysUserService.getById(userId);
            return R.ok().put("data", sysUserEntity);
        }catch (Exception e){
            logger.error(">>>>>>>>获取用户信息详情异常,信息:",e);
            return R.error(e.getMessage());
        }
    }


    @AppLogin
    @GetMapping("/getUserInfo1")
    @ApiOperation("获取用户信息")
    public R getUserInfo1(@AppLoginUser SysUserEntity sysUserEntity){

        return R.ok().put("sysUserEntity", sysUserEntity);
    }


    /**
     * 所有用户列表
     */
    @GetMapping("/list")
    @ApiOperation("所有用户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页", paramType = "query", dataType="Integer", required = true),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页记录数", paramType = "query", dataType="Integer", required = true)
    })
    public R list(@ApiIgnore @RequestParam Map<String, Object> params){
        PageUtils page = sysUserService.selectByPage(params);
        return R.ok().put("page", page);
    }



    @PostMapping("/save")
    @ApiOperation("添加")
    public R save(){
        SysUserEntity user = new SysUserEntity();
        user.setUsername(new Date().toString());
        sysUserService.save(user);
        return R.ok();
    }

}
