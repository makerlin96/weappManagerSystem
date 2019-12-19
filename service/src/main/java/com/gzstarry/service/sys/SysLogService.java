package com.gzstarry.service.sys;

import com.gzstarry.common.utils.PageUtils;
import com.gzstarry.entity.sys.SysLogEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * 系统日志
 *
 * @author MakerLin makerlin96@gmail.com
 */
public interface SysLogService extends IService<SysLogEntity> {


    /**
     * 后台分页
     * @param params
     * @return
     */
    PageUtils selectByPage(Map<String, Object> params);
}
