package com.gzstarry.dao.sys;

import com.gzstarry.entity.sys.SysConfigEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * 系统配置
 *
 * @author MakerLin makerlin96@gmail.com
 */
@Mapper
@Repository
public interface SysConfigMapper extends BaseMapper<SysConfigEntity> {

    /**
     * 根据key更新value
     * @param paramKey
     * @param paramValue
     * @return
     */
    @Update("UPDATE sys_config SET param_value = #{paramValue} WHERE param_key = #{paramKey}")
    boolean updateValueByKey(@Param("paramKey") String paramKey, @Param("paramValue") String paramValue);


    /**
     * 根据key查询数据
     * @param paramKey
     * @return
     */
    @Select("SELECT * FROM sys_config WHERE param_key = #{paramKey}")
    SysConfigEntity getByParamKey(String paramKey);
}
