package com.gzstarry.dao.sys;

import com.gzstarry.entity.sys.ScheduleJobEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 定时任务
 *
 * @author MakerLin makerlin96@gmail.com
 */
@Mapper
@Repository
public interface ScheduleJobMapper extends BaseMapper<ScheduleJobEntity> {

}
