package com.gzstarry.entity.core;

import lombok.Data;
import java.io.Serializable;

/**
 * 系统词典
 *
 * @author MakerLin makerlin96@gmail.com
 */
@Data
public class SysDictTree extends BaseTree<SysDictTree> implements Serializable {

    private Long id;
    private String k;
    private String name;
    private String value;
    private Long parentId;
    private Long sort;

}
