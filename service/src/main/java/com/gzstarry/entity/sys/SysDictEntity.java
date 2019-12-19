package com.gzstarry.entity.sys;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 系统词典
 *
 * @author MakerLin makerlin96@gmail.com
 */
@Data
@TableName("sys_dict")
public class SysDictEntity implements Serializable {

    @TableId
    private Long id;

    private String k;

    private String name;

    private String value;

    private Long parentId;

    private Long sort;

    @TableField(exist=false)
    private String parentName;
}
