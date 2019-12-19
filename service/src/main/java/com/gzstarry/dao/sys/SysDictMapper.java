package com.gzstarry.dao.sys;

import com.gzstarry.entity.core.SysDictTree;
import com.gzstarry.entity.sys.SysDictEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 系统词典
 *
 * @author MakerLin makerlin96@gmail.com
 */
@Mapper
@Repository
public interface SysDictMapper extends BaseMapper<SysDictEntity> {

    /**
     * 根据key查询数据
     * @param k
     * @return
     */
    @Select("SELECT * FROM sys_dict WHERE k = #{k} AND `value` !=0 ORDER BY sort ASC")
    List<SysDictEntity> selectByKey(String k);

    /**
     * 获取所有数据
     * @return
     */
    @Select("SELECT * FROM sys_dict ORDER BY sort ASC")
    List<SysDictTree> getList();
}
