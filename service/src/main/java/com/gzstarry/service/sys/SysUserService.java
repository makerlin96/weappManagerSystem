package com.gzstarry.service.sys;

import com.gzstarry.common.utils.PageUtils;
import com.gzstarry.entity.sys.SysUserEntity;
import com.gzstarry.entity.sys.SysUserExcelEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 系统用户
 *
 * @author MakerLin makerlin96@gmail.com
 */
public interface SysUserService extends IService<SysUserEntity> {

    /**
     * 后台分页
     * @param params
     * @return
     */
    PageUtils selectByPage(Map<String, Object> params);

    /**
     * 保存用户
     * @param sysUserEntity
     */
    void saveUser(SysUserEntity sysUserEntity);

    /**
     * 编辑用户
     * @param sysUserEntity
     */
    void updateUser(SysUserEntity sysUserEntity);

    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    SysUserEntity selectByUsername(String username);

    /**
     * 获取用户权限列表
     * @param userId
     * @return
     */
    Set<String> getUserPermissions(Long userId);

    /**
     * Excel导出
     * @param params
     * @return
     */
    List<SysUserExcelEntity> exportExcel(Map<String, Object> params);

    /**
     * 获取第二个数据库的数据
     * @return
     */
    List<SysUserEntity> dynamicDataSource();
}
