package com.gzstarry.entity.core;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 系统菜单
 *
 * @author MakerLin makerlin96@gmail.com
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class SysPermissionTree extends BaseTree<SysPermissionTree> implements Serializable {

    private Long id;
    private Long parentId;
    private String name;
    private String path;
    private Integer type;
    private String perms;
    private String icon;
    private Integer sort;
}
