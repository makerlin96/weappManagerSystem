package com.gzstarry.dao.sys;

import com.gzstarry.entity.sys.SysOssEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 文件上传
 *
 * @author MakerLin makerlin96@gmail.com
 */
@Mapper
@Repository
public interface SysOssMapper extends BaseMapper<SysOssEntity> {


}
