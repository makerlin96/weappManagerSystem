package com.gzstarry.async;

import com.gzstarry.entity.sys.SysLogEntity;
import com.gzstarry.service.sys.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 异步执行类
 *
 * @author MakerLin makerlin96@gmail.com
 */
@Component
public class AsyncTask {

    @Autowired
    private SysLogService sysLogService;

    /**
     * 异步获取IP地址信息
     * @param id
     * @param ip
     */
    @Async
    public void getIpAddress(Long id,String ip){
        SysLogEntity log = new SysLogEntity();
        log.setId(id);
        sysLogService.updateById(log);
    }
}
