package com.gzstarry.dao.sys;

import com.gzstarry.entity.sys.SysRoleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色管理
 *
 * @author MakerLin makerlin96@gmail.com
 */
@Mapper
@Repository
public interface SysRoleMapper extends BaseMapper<SysRoleEntity> {


    /**
     * 根据用户ID查询角色
     * @param userId
     * @return
     */
    @Select("SELECT a.role_name FROM sys_role a,sys_user_role b WHERE a.role_id=b.role_id and b.user_id= #{userId}")
    List<String> getUserRoles(Long userId);

}
