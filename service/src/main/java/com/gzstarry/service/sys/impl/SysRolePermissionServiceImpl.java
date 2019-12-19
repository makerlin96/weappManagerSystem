package com.gzstarry.service.sys.impl;

import com.gzstarry.dao.sys.SysRolePermissionMapper;
import com.gzstarry.entity.sys.SysRolePermissionEntity;
import com.gzstarry.service.sys.SysRolePermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 角色对应权限表
 *
 * @author MakerLin makerlin96@gmail.com
 */
@Service
public class SysRolePermissionServiceImpl extends ServiceImpl<SysRolePermissionMapper, SysRolePermissionEntity> implements SysRolePermissionService {

    @Autowired
    protected SysRolePermissionMapper sysRolePermissionMapper;

    @Override
    public List<Long> getPermissionListByRoleId(Long roleId) {
        return baseMapper.getPermissionListByRoleId(roleId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(Long roleId, List<Long> permissionIdList) {
        //先删除角色菜单关系
        deleteByRoleIds(new Long[]{roleId});

        //角色没有一个菜单权限的情况
        if(permissionIdList.size() == 0){
            return ;
        }

        //保存角色菜单关系
        for(Long permissionId : permissionIdList){
            SysRolePermissionEntity entity = new SysRolePermissionEntity();
            entity.setRoleId(roleId);
            entity.setPermissionId(permissionId);
            //保存
            baseMapper.insert(entity);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByRoleIds(Long[] roleIds) {
        baseMapper.deleteByRoleIds(roleIds);
    }
}
