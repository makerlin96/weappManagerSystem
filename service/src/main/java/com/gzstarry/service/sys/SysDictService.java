package com.gzstarry.service.sys;

import com.gzstarry.entity.core.SysDictTree;
import com.gzstarry.entity.sys.SysDictEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 系统词典
 *
 * @author MakerLin makerlin96@gmail.com
 */
public interface SysDictService extends IService<SysDictEntity> {


    /**
     * 根据key查询数据
     * @param k
     * @return
     */
    List<SysDictEntity> selectByKey(String k);

    /**
     * 获取树形列表
     * @return
     */
    List<SysDictTree> getListTree();
}
