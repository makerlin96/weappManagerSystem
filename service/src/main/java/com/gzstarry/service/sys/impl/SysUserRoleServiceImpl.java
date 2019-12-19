package com.gzstarry.service.sys.impl;

import com.gzstarry.dao.sys.SysUserRoleMapper;
import com.gzstarry.entity.sys.SysUserRoleEntity;
import com.gzstarry.service.sys.SysUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 角色用户关系
 *
 * @author MakerLin makerlin96@gmail.com
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRoleEntity> implements SysUserRoleService {

    @Override
    public void saveOrUpdate(Long userId, List<Long> roleIdList) {
        //先删除用户与角色关系
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", userId);
        this.removeByMap(map);

        //用户没有一个角色权限的情况
        if(roleIdList == null || roleIdList.size() == 0){
            return ;
        }

        //保存角色用户关系
        for(Long roleId : roleIdList){
            SysUserRoleEntity sysUserRoleEntity = new SysUserRoleEntity();
            sysUserRoleEntity.setUserId(userId);
            sysUserRoleEntity.setRoleId(roleId);
            baseMapper.insert(sysUserRoleEntity);
        }
    }

    @Override
    public void deleteByRoleIds(Long[] roleIds) {
        baseMapper.deleteByRoleIds(roleIds);
    }


    @Override
    public List<Long> getRoleIdList(Long userId) {
        return baseMapper.getRoleIdList(userId);
    }

}
