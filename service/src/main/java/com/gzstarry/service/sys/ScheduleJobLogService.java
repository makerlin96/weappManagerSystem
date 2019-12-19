package com.gzstarry.service.sys;

import com.gzstarry.common.utils.PageUtils;
import com.gzstarry.entity.sys.ScheduleJobLogEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * 执行日志
 *
 * @author MakerLin makerlin96@gmail.com
 */
public interface ScheduleJobLogService extends IService<ScheduleJobLogEntity> {


    /**
     * 后台分页数据
     * @param params
     * @return
     */
    PageUtils selectByPage(Map<String, Object> params);
}
