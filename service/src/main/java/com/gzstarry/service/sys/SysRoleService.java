package com.gzstarry.service.sys;

import com.gzstarry.common.utils.PageUtils;
import com.gzstarry.entity.sys.SysRoleEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;
import java.util.Set;

/**
 * 角色管理
 *
 * @author MakerLin makerlin96@gmail.com
 */
public interface SysRoleService extends IService<SysRoleEntity> {

    /**
     * 后台分页数据
     * @param params
     * @return
     */
    PageUtils selectByPage(Map<String, Object> params);

    /**
     * 根据用户ID查询角色
     * @param userId
     * @return
     */
    Set<String> getUserRoles(Long userId);

    /**
     * 保存
     * @param entity
     */
    void saveRole(SysRoleEntity entity);

    /**
     * 更新
     * @param entity
     */
    void updateRole(SysRoleEntity entity);

    /**
     * 删除
     * @param ids
     */
    void delete(Long[] ids);
}
