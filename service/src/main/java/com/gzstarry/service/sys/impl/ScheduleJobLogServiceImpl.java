package com.gzstarry.service.sys.impl;

import com.gzstarry.common.constant.Constant;
import com.gzstarry.common.utils.PageUtils;
import com.gzstarry.dao.sys.ScheduleJobLogMapper;
import com.gzstarry.entity.sys.ScheduleJobLogEntity;
import com.gzstarry.service.sys.ScheduleJobLogService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 执行日志
 *
 * @author MakerLin makerlin96@gmail.com
 */
@Service(value = "scheduleJobLogService")
public class ScheduleJobLogServiceImpl extends ServiceImpl<ScheduleJobLogMapper, ScheduleJobLogEntity> implements ScheduleJobLogService {

    @Override
    public PageUtils selectByPage(Map<String, Object> params) {
        long page = Long.parseLong((String) params.get(Constant.PAGE));
        long limit = Long.parseLong((String) params.get(Constant.LIMIT));
        Page<ScheduleJobLogEntity> pages = new Page<>(page, limit);
        QueryWrapper<ScheduleJobLogEntity> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_time");
        IPage<ScheduleJobLogEntity> list = baseMapper.selectPage(pages,wrapper);
        return new PageUtils(list);
    }
}
