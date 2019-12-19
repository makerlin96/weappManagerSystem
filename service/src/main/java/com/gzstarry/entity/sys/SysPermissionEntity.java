package com.gzstarry.entity.sys;

import com.gzstarry.entity.core.Meta;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.io.Serializable;
import java.util.List;

/**
 * 菜单管理
 *
 * @author MakerLin makerlin96@gmail.com
 */
@Data
@TableName("sys_permission")
public class SysPermissionEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 菜单ID
     */
    @TableId
    private Long id;
    /**
     * 父菜单ID，一级菜单为0
     */
    private Long parentId;
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 菜单URL
     */
    private String path;
    /**
     * 类型     0：目录   1：菜单   2：按钮
     */
    private Integer type;
    /**
     * 授权(多个用逗号分隔，如：user:list,user:create)
     */
    private String perms;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 父级名称
     */
    @TableField(exist=false)
    private String parentName;
    /**
     * vueTree属性
     */
    @TableField(exist=false)
    private Boolean alwaysShow;

    @Transient
    @TableField(exist=false)
    private Meta meta;

    @TableField(exist=false)
    private String redirect;

    @TableField(exist=false)
    private String component;

    @TableField(exist=false)
    private List<?> children;
}
