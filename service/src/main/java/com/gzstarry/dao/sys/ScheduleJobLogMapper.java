package com.gzstarry.dao.sys;

import com.gzstarry.entity.sys.ScheduleJobLogEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 定时任务日志
 *
 * @author MakerLin makerlin96@gmail.com
 */
@Mapper
@Repository
public interface ScheduleJobLogMapper extends BaseMapper<ScheduleJobLogEntity> {
   
}
