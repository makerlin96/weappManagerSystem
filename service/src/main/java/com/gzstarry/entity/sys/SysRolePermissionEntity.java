package com.gzstarry.entity.sys;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;

/**
 * 角色与菜单对应关系
 *
 * @author MakerLin makerlin96@gmail.com
 */
@Data
@TableName("sys_role_permission")
public class SysRolePermissionEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private Long id;
    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 菜单ID
     */
    private Long permissionId;

}
