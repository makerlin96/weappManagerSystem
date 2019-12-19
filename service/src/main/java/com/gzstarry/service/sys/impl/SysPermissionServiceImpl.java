package com.gzstarry.service.sys.impl;

import com.gzstarry.common.constant.Constant;
import com.gzstarry.dao.sys.SysPermissionMapper;
import com.gzstarry.dao.sys.SysUserMapper;
import com.gzstarry.entity.core.Meta;
import com.gzstarry.entity.core.SysPermissionTree;
import com.gzstarry.entity.core.TreeUtils;
import com.gzstarry.entity.sys.SysPermissionEntity;
import com.gzstarry.entity.sys.SysUserEntity;
import com.gzstarry.service.sys.SysPermissionService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 菜单管理
 *
 * @author MakerLin makerlin96@gmail.com
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermissionEntity> implements SysPermissionService {

    @Autowired
    protected SysPermissionMapper sysPermissionMapper;
    @Autowired
    protected SysUserMapper sysUserMapper;


    /**
     * 左边菜单绑定
     * 获取用户可以操作的菜单列表
     * @param userId
     * @return
     */
    @Override
    public List<SysPermissionEntity> getLeftNav(SysUserEntity user, Long userId) {
        List<SysPermissionEntity> permissionList;
        if(user.getSuperAdmin() == Constant.SUPER_ADMIN){
            QueryWrapper<SysPermissionEntity> wrapper = new QueryWrapper<>();
            wrapper.eq("type",1);
            permissionList = sysPermissionMapper.selectList(wrapper);
        }else{
            permissionList = sysPermissionMapper.getUserPermissionList(userId);
        }
        //根节点
        List<SysPermissionEntity> rootMenu = new ArrayList<SysPermissionEntity>();

        for (SysPermissionEntity nav : permissionList) {
            //父节点是0的，为根节点
            if(nav.getParentId()==0){
                nav.setMeta(new Meta(nav.getName(),nav.getIcon()));
                nav.setAlwaysShow(true);
                nav.setComponent("Layout");
                rootMenu.add(nav);
            }
        }
        /* 根据Menu类的order排序 */
        Collections.sort(rootMenu, order());
        //为根菜单设置子菜单，getClild是递归调用的
        for (SysPermissionEntity nav : rootMenu) {
            /* 获取根节点下的所有子节点 使用getChild方法*/
            List<SysPermissionEntity> childList = getChild(nav.getId(), permissionList);
            //给根节点设置子节点
            nav.setChildren(childList);
        }
        return rootMenu;
    }

    /**
     * 排序,根据order排序
     */
    public Comparator<SysPermissionEntity> order(){
        Comparator<SysPermissionEntity> comparator = new Comparator<SysPermissionEntity>() {
            @Override
            public int compare(SysPermissionEntity o1, SysPermissionEntity o2) {
                if(!o1.getSort().equals(o2.getSort())){
                    return o1.getSort() - o2.getSort();
                }
                return 0;
            }
        };
        return comparator;
    }

    /**
     * 获取子节点
     * @param id 父节点id
     * @param allMenu 所有菜单列表
     * @return 每个根节点下，所有子菜单列表
     */
    public List<SysPermissionEntity> getChild(Long id, List<SysPermissionEntity> allMenu){
        //子菜单
        List<SysPermissionEntity> childList = new ArrayList<SysPermissionEntity>();
        for (SysPermissionEntity nav : allMenu) {
            // 遍历所有节点，将所有菜单的父id与传过来的根节点的id比较
            //相等说明：为该根节点的子节点。
            if(nav.getParentId().equals(id)){
                nav.setMeta(new Meta(nav.getName(),nav.getIcon()));
                nav.setComponent(nav.getPath());
                childList.add(nav);
            }
        }
        //递归
        for (SysPermissionEntity nav : childList) {
            nav.setChildren(getChild(nav.getId(), allMenu));
        }
        //排序
        Collections.sort(childList,order());
        //如果节点下没有子节点，返回一个空List（递归退出）
        if(childList.size() == 0){
            return new ArrayList<SysPermissionEntity>();
        }
        return childList;
    }


    @Override
    public Set<String> getUserButtonsList(SysUserEntity user, Long userId) {
        Set<String> buttonsList;
        if(user.getSuperAdmin() == Constant.SUPER_ADMIN){
            buttonsList = sysPermissionMapper.getAllButtonsList();
        }else{
            buttonsList = sysPermissionMapper.getUserButtonsList(userId);
        }
        return buttonsList;
    }

    @Override
    public List<SysPermissionTree> getListTree() {
        List<SysPermissionTree> list = sysPermissionMapper.getList();
        return TreeUtils.build(list,0L);
    }

}
