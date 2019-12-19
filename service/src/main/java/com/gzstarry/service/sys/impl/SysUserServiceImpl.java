package com.gzstarry.service.sys.impl;

import cn.hutool.crypto.SecureUtil;
import com.gzstarry.common.constant.Constant;
import com.gzstarry.common.utils.PageUtils;
import com.gzstarry.dao.sys.SysPermissionMapper;
import com.gzstarry.dao.sys.SysUserMapper;
import com.gzstarry.dao.sys.SysUserRoleMapper;
import com.gzstarry.entity.sys.SysPermissionEntity;
import com.gzstarry.entity.sys.SysUserEntity;
import com.gzstarry.entity.sys.SysUserExcelEntity;
import com.gzstarry.service.sys.SysUserRoleService;
import com.gzstarry.service.sys.SysUserService;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 系统用户
 *
 * @author MakerLin makerlin96@gmail.com
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserEntity> implements SysUserService {

    @Autowired
    protected SysUserMapper sysUserMapper;
    @Autowired
    protected SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    protected SysPermissionMapper sysPermissionMapper;
    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Override
    public PageUtils selectByPage(Map<String, Object> params) {
        long page = Long.parseLong((String) params.get(Constant.PAGE));
        long limit = Long.parseLong((String) params.get(Constant.LIMIT));
        Page<SysUserEntity> pages = new Page<>(page, limit);
        List<SysUserEntity> list = baseMapper.getPageList(pages, params);
        pages.setRecords(list);
        return new PageUtils(pages);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveUser(SysUserEntity sysUserEntity) {
        //密码加密
        sysUserEntity.setPassword(SecureUtil.sha256(sysUserEntity.getPassword()));
        sysUserEntity.setCreateTime(new Date());
        //保存用户
        baseMapper.insert(sysUserEntity);
        //保存角色用户关系
        sysUserRoleService.saveOrUpdate(sysUserEntity.getUserId(), sysUserEntity.getRoleIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUser(SysUserEntity sysUserEntity) {
        //密码加密
        if(StringUtils.isBlank(sysUserEntity.getPassword())){
            sysUserEntity.setPassword(null);
        }else{
            String password = SecureUtil.sha256(sysUserEntity.getPassword());
            sysUserEntity.setPassword(password);
        }
        //更新用户
        baseMapper.updateById(sysUserEntity);
        //保存角色用户关系
        sysUserRoleService.saveOrUpdate(sysUserEntity.getUserId(), sysUserEntity.getRoleIdList());
    }

    @Override
    public Set<String> getUserPermissions(Long userId) {
        List<String> permsList;
        //系统管理员，ID为1，拥有最高权限
        if(userId == Constant.SUPER_ADMIN){
            List<SysPermissionEntity> menuList = sysPermissionMapper.selectAllPermissions();
            permsList = new ArrayList<>(menuList.size());
            for(SysPermissionEntity menu : menuList){
                permsList.add(menu.getPerms());
            }
        }else{
            permsList = sysPermissionMapper.selectPermissionByUserId(userId);
        }
        //用户权限列表
        Set<String> permsSet = new HashSet<>();
        for(String perms : permsList){
            if(StringUtils.isBlank(perms)){
                continue;
            }
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }
        return permsSet;
    }


    @Override
    public List<SysUserExcelEntity> exportExcel(Map<String, Object> params) {
        return baseMapper.exportExcelList(params);
    }

    @Override
    public SysUserEntity selectByUsername(String username) {
        return baseMapper.selectByUsername(username);
    }

    @Override
    @DS("slave")
    public List<SysUserEntity> dynamicDataSource(){
        return baseMapper.selectList(null);
    }
}
