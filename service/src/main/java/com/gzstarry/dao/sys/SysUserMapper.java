package com.gzstarry.dao.sys;

import com.gzstarry.entity.sys.SysUserEntity;
import com.gzstarry.entity.sys.SysUserExcelEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 系统用户
 *
 * @author MakerLin makerlin96@gmail.com
 */
@Mapper
@Repository
public interface SysUserMapper extends BaseMapper<SysUserEntity> {

    /**
     * 后台分页
     * @param pages
     * @param params
     * @return
     */
    @Select({
            "<script>",
            "SELECT * FROM sys_user WHERE 1 = 1",
            "<if test=\"params.username != null and params.username.trim() != ''\">",
            "AND username like concat('%',#{params.username},'%')",
            "</if>",
            "<if test=\"params.mobile != null and params.mobile.trim() != ''\">",
            "AND mobile like concat('%',#{params.mobile},'%')",
            "</if>",
            "<if test=\"null != params.startDate and params.startDate != '' and null != params.endDate and params.endDate != ''\">",
            "AND create_time between DATE_FORMAT(#{params.startDate},'%Y-%m-%d 00:00:00') and DATE_FORMAT(#{params.endDate},'%Y-%m-%d 23:59:59')",
            "</if>",
            "ORDER BY user_id desc",
            "</script>"
    })
    List<SysUserEntity> getPageList(Page pages, @Param("params") Map<String,Object> params);

    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    @Select("SELECT * FROM sys_user where username = #{username}")
    SysUserEntity selectByUsername(String username);

    /**
     * 查询用户的所有菜单ID
     */
    @Select({
            "<script>",
            "SELECT distinct rm.menu_id from sys_user_role ur",
            "LEFT JOIN sys_role_menu rm on ur.role_id = rm.role_id",
            "where ur.user_id = #{userId}",
            "</script>"
    })
    List<Long> queryAllMenuId(Long userId);

    /**
     * Excel导出
     * @param params
     * @return
     */
    @Select({
            "<script>",
            "SELECT * FROM sys_user WHERE 1 = 1",
            "<if test=\"params.username != null and params.username.trim() != ''\">",
            "AND username like concat('%',#{params.username},'%')",
            "</if>",
            "<if test=\"params.mobile != null and params.mobile.trim() != ''\">",
            "AND mobile like concat('%',#{params.mobile},'%')",
            "</if>",
            "<if test=\"null != params.startDate and params.startDate != '' and null != params.endDate and params.endDate != ''\">",
            "AND create_time between DATE_FORMAT(#{params.startDate},'%Y-%m-%d 00:00:00') and DATE_FORMAT(#{params.endDate},'%Y-%m-%d 23:59:59')",
            "</if>",
            "</script>"
    })
    List<SysUserExcelEntity> exportExcelList(@Param("params") Map<String,Object> params);

}
