package com.gzstarry.dao.sys;

import com.gzstarry.entity.core.SysPermissionTree;
import com.gzstarry.entity.sys.SysPermissionEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * 菜单管理
 *
 * @author MakerLin makerlin96@gmail.com
 */
@Mapper
@Repository
public interface SysPermissionMapper extends BaseMapper<SysPermissionEntity> {

    /**
     * 获取用户可以操作的菜单列表
     * @param userId 用户ID
     * @return
     */
    @Select({
            "<script>",
            "SELECT DISTINCT(a.id) ,a.parent_id,a.name,a.path,a.sort,a.perms,a.icon",
            "FROM sys_permission a,sys_role_permission b,sys_user_role c",
            "WHERE a.id = b.permission_id",
            "and c.role_id = b.role_id",
            "and c.user_id = #{userId}",
            "and a.type = 1",
            "ORDER BY a.sort",
            "</script>"
    })
    List<SysPermissionEntity> getUserPermissionList(Long userId);

    /**
     * 获取用户可以操作的按钮列表
     * @param userId 用户ID
     * @return
     */
    @Select({
            "<script>",
            "SELECT c.perms from sys_user_role AS a",
            "LEFT JOIN sys_role_permission AS b on a.role_id = b.role_id",
            "LEFT JOIN sys_permission AS c on b.permission_id = c.id",
            "WHERE c.type = '2' AND a.user_id = #{userId}",
            "</script>"
    })
    Set<String> getUserButtonsList(Long userId);

    /**
     * 根据用户ID查询权限
     * @param userId
     * @return
     */
    @Select({
            "<script>",
            "SELECT m.perms from sys_user_role ur",
            "LEFT JOIN sys_role_permission rm on ur.role_id = rm.role_id ",
            "LEFT JOIN sys_permission m on rm.permission_id = m.id ",
            "where ur.user_id = #{userId}",
            "</script>"
    })
    List<String> selectPermissionByUserId(Long userId);

    /**
     * 获取所有可以操作的按钮列表
     * @return
     */
    @Select("SELECT perms from sys_permission WHERE perms !='' AND type = 2")
    Set<String> getAllButtonsList();

    /**
     * 查询所有并根据sort排序
     * @return
     */
    @Select("SELECT * FROM sys_permission order by sort ASC")
    List<SysPermissionEntity> selectAllPermissions();

    /**
     * 获取所有数据
     * @return
     */
    @Select("SELECT * FROM sys_permission ORDER BY sort ASC")
    List<SysPermissionTree> getList();

}
