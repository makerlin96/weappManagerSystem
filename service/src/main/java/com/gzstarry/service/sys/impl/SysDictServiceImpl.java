package com.gzstarry.service.sys.impl;

import com.gzstarry.dao.sys.SysDictMapper;
import com.gzstarry.entity.core.SysDictTree;
import com.gzstarry.entity.core.TreeUtils;
import com.gzstarry.entity.sys.SysDictEntity;
import com.gzstarry.service.sys.SysDictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统词典
 *
 * @author MakerLin makerlin96@gmail.com
 */
@Service
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDictEntity> implements SysDictService {

    @Autowired
    protected SysDictMapper sysDictMapper;

    @Override
    public List<SysDictTree> getListTree() {
        List<SysDictTree> list = sysDictMapper.getList();
        return TreeUtils.build(list,0L);
    }

    @Override
    public List<SysDictEntity> selectByKey(String k) {
        return sysDictMapper.selectByKey(k);
    }
}



