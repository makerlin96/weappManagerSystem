package com.gzstarry.service.sys.impl;

import com.gzstarry.common.constant.Constant;
import com.gzstarry.common.utils.PageUtils;
import com.gzstarry.dao.sys.SysOssMapper;
import com.gzstarry.entity.sys.SysOssEntity;
import com.gzstarry.service.sys.SysOssService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 文件上传
 *
 * @author MakerLin makerlin96@gmail.com
 */
@Service
public class SysOssServiceImpl extends ServiceImpl<SysOssMapper, SysOssEntity> implements SysOssService {

    @Autowired
    protected SysOssMapper sysOssMapper;

    @Override
    public PageUtils selectByPage(Map<String, Object> params) {
        long page = Long.parseLong((String) params.get(Constant.PAGE));
        long limit = Long.parseLong((String) params.get(Constant.LIMIT));
        Page<SysOssEntity> pages = new Page<>(page, limit);
        QueryWrapper<SysOssEntity> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_time");
        IPage<SysOssEntity> list = baseMapper.selectPage(pages, wrapper);
        return new PageUtils(list);
    }

}
