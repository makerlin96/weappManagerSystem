package com.gzstarry.service.sys.impl;

import com.gzstarry.common.constant.Constant;
import com.gzstarry.common.utils.PageUtils;
import com.gzstarry.dao.sys.ScheduleJobMapper;
import com.gzstarry.entity.sys.ScheduleJobEntity;
import com.gzstarry.job.ScheduleUtils;
import com.gzstarry.service.sys.ScheduleJobService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

/**
 * 定时任务
 *
 * @author MakerLin makerlin96@gmail.com
 */
@Service
public class ScheduleJobServiceImpl extends ServiceImpl<ScheduleJobMapper, ScheduleJobEntity> implements ScheduleJobService {

    @Autowired
    private Scheduler scheduler;
    @Autowired
    private ScheduleJobMapper scheduleJobMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveJob(ScheduleJobEntity job) {
        job.setCreateTime(new Date());
        job.setStatus(Constant.ScheduleStatus.NORMAL.getValue());
        baseMapper.insert(job);
        ScheduleUtils.createScheduleJob(scheduler, job);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateJob(ScheduleJobEntity job) {
        ScheduleUtils.updateScheduleJob(scheduler, job);
        baseMapper.updateById(job);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] ids) {
        for(Long jobId : ids){
            ScheduleUtils.deleteScheduleJob(scheduler, jobId);
        }
        baseMapper.deleteBatchIds(Arrays.asList(ids));
    }


    @Override
    public PageUtils selectByPage(Map<String, Object> params) {
        long page = Long.parseLong((String) params.get(Constant.PAGE));
        long limit = Long.parseLong((String) params.get(Constant.LIMIT));
        String beanName = (String) params.get("beanName");
        Page<ScheduleJobEntity> pages = new Page<>(page, limit);
        QueryWrapper<ScheduleJobEntity> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_time");
        wrapper.like(StringUtils.isNotBlank(beanName),"bean_name",beanName);
        IPage<ScheduleJobEntity> list = baseMapper.selectPage(pages, wrapper);
        return new PageUtils(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void run(Long jobId) {
        ScheduleUtils.run(scheduler,baseMapper.selectById(jobId));
    }

    @Override
    public void pause(Long jobId) {
        ScheduleUtils.pauseJob(scheduler, jobId);
        ScheduleJobEntity job=new ScheduleJobEntity();
        job.setJobId(jobId);
        job.setStatus(Constant.ScheduleStatus.PAUSE.getValue());
        scheduleJobMapper.updateById(job);
    }

    @Override
    public void resume(Long jobId) {
        ScheduleUtils.resumeJob(scheduler, jobId);
        ScheduleJobEntity job=new ScheduleJobEntity();
        job.setJobId(jobId);
        job.setStatus(Constant.ScheduleStatus.NORMAL.getValue());
        scheduleJobMapper.updateById(job);
    }


}
