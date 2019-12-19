package com.gzstarry.modules.controller.sys;

import com.gzstarry.common.utils.R;
import com.gzstarry.common.utils.RedisUtils;
import com.gzstarry.modules.base.AbstractController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 系统首页
 *
 * @author MakerLin makerlin96@gmail.com
 */
@Api(tags="系统首页")
@RestController
public class IndexController extends AbstractController {

    @Autowired
    private RedisUtils redisUtils;

    @ApiOperation("系统信息")
    @GetMapping("/index/main")
    public R main() {
        Properties props = System.getProperties();
        //java版本
        String javaVersion = props.getProperty("java.version");
        //操作系统名称
        String osName = props.getProperty("os.name");
        //用户的主目录
        String userHome = props.getProperty("user.home");
        //用户的当前工作目录
        String userDir = props.getProperty("user.dir");
        //CPU个数
        String cpu = Runtime.getRuntime().availableProcessors() + "核";
        //内存总量
        String totalMemory = (Runtime.getRuntime().totalMemory() / 1024 / 1024) + "M";
        //空闲内存量
        String freeMemory = (Runtime.getRuntime().freeMemory() / 1024 / 1024) + "M";
        //使用的最大内存量
        String maxMemory = (Runtime.getRuntime().maxMemory() / 1024 / 1024) + "M";

        Map<String,Object> map = new HashMap<>();
        map.put("javaVersion", javaVersion);
        map.put("osName", osName);
        map.put("userHome", userHome);
        map.put("userDir", userDir);
        map.put("cpu", cpu);
        map.put("totalMemory", totalMemory);
        map.put("freeMemory", freeMemory);
        map.put("maxMemory", maxMemory);
        return R.ok(map);
    }


    @ApiOperation("清除Redis")
    @PostMapping("/index/clearRedis")
    public R clearRedis() {
        try {
            redisUtils.deleteAll("lunhui:*");
            return R.ok();
        } catch (Exception e) {
            return R.error();
        }
    }

}