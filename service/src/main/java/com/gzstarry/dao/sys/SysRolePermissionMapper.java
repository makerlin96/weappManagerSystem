package com.gzstarry.dao.sys;

import com.gzstarry.entity.sys.SysRolePermissionEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色对应权限表
 *
 * @author MakerLin makerlin96@gmail.com
 */
@Mapper
@Repository
public interface SysRolePermissionMapper extends BaseMapper<SysRolePermissionEntity> {


    /**
     * 获取角色对应的菜单
     * @param roleId
     * @return
     */
    @Select("SELECT permission_id FROM sys_role_permission WHERE role_id = #{roleId}")
    List<Long> getPermissionListByRoleId(Long roleId);

    /**
     * 根据角色id，删除角色菜单关系
     * @param roleId
     * @return
     */
    @Delete({
            "<script>",
            "DELETE FROM sys_role_permission WHERE role_id in",
            "<foreach item='roleId' collection='array' open='(' separator=',' close=')'>",
            "#{roleId}",
            "</foreach>",
            "</script>"
    })
    void deleteByRoleIds(Long[] roleId);

}
