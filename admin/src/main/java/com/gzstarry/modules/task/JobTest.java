package com.gzstarry.modules.task;

import com.gzstarry.modules.base.AbstractController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 定时任务测试类
 *
 * @author MakerLin makerlin96@gmail.com
 */
@Slf4j
@Component("jobTest")
public class JobTest extends AbstractController {

    public void test(){
        log.info("我是不带参数的test方法，正在被执行");
    }

    public void test2(String params){
        log.info("我是带参数的test2方法，正在被执行，参数为：" + params);
    }
}
