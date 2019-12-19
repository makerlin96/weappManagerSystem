package com.gzstarry.service.sys;

import com.gzstarry.entity.sys.SysRolePermissionEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 角色对应权限表
 *
 * @author MakerLin makerlin96@gmail.com
 */
public interface SysRolePermissionService extends IService<SysRolePermissionEntity> {

    /**
     * 获取角色对应的菜单
     * @param roleId
     * @return
     */
    List<Long> getPermissionListByRoleId(Long roleId);

    /**
     * 保存或修改
     * @param roleId      角色ID
     * @param permissionIdList  菜单ID列表
     */
    void saveOrUpdate(Long roleId, List<Long> permissionIdList);

    /**
     * 根据角色id，删除角色菜单关系
     * @param roleIds 角色id
     */
    void deleteByRoleIds(Long[] roleIds);
}
