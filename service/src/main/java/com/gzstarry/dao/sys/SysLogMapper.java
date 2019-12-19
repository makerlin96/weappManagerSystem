package com.gzstarry.dao.sys;

import com.gzstarry.entity.sys.SysLogEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 系统日志
 *
 * @author MakerLin makerlin96@gmail.com
 */
@Mapper
@Repository
public interface SysLogMapper extends BaseMapper<SysLogEntity> {


}
