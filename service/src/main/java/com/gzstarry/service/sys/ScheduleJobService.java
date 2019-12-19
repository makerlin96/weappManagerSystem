package com.gzstarry.service.sys;

import com.gzstarry.common.utils.PageUtils;
import com.gzstarry.entity.sys.ScheduleJobEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * 定时任务
 *
 * @author MakerLin makerlin96@gmail.com
 */
public interface ScheduleJobService extends IService<ScheduleJobEntity> {

    /**
     * 后台分页数据
     * @param params
     * @return
     */
    PageUtils selectByPage(Map<String, Object> params);

    /**
     * 保存
     * @param job
     */
    void saveJob(ScheduleJobEntity job);

    /**
     * 保存
     * @param job
     */
    void updateJob(ScheduleJobEntity job);

    /**
     * 批量删除定时任务
     */
    void deleteBatch(Long[] ids);

    /**
     * 立即执行
     * @param jobId
     */
    void run(Long jobId);

    /**
     * 暂停运行
     * @param jobId
     */
    void pause(Long jobId);

    /**
     * 恢复运行
     * @param jobId
     */
    void resume(Long jobId);

}
