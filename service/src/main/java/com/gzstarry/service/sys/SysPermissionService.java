package com.gzstarry.service.sys;

import com.gzstarry.entity.core.SysPermissionTree;
import com.gzstarry.entity.sys.SysPermissionEntity;
import com.gzstarry.entity.sys.SysUserEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Set;

/**
 * 菜单管理
 *
 * @author MakerLin makerlin96@gmail.com
 */
public interface SysPermissionService extends IService<SysPermissionEntity> {

    /**
     * 获取系统左侧菜单列表
     * @param userId
     * @return
     */
    List<SysPermissionEntity> getLeftNav(SysUserEntity user, Long userId);

    /**
     * 获取用户可以操作的按钮列表
     * @param userId
     * @return
     */
    Set<String> getUserButtonsList(SysUserEntity user, Long userId);

    /**
     * 获取树形列表
     * @return
     */
    List<SysPermissionTree> getListTree();

}
