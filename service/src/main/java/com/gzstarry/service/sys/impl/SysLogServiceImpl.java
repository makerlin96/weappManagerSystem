package com.gzstarry.service.sys.impl;

import com.gzstarry.common.constant.Constant;
import com.gzstarry.common.utils.PageUtils;
import com.gzstarry.dao.sys.SysLogMapper;
import com.gzstarry.entity.sys.SysLogEntity;
import com.gzstarry.service.sys.SysLogService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Map;

/**
 * 系统日志
 *
 * @author MakerLin makerlin96@gmail.com
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLogEntity>  implements SysLogService {

    @Autowired
    protected SysLogMapper sysLogMapper;

    @Override
    public PageUtils selectByPage(Map<String, Object> params) {
        long page = Long.parseLong((String) params.get(Constant.PAGE));
        long limit = Long.parseLong((String) params.get(Constant.LIMIT));
        String username = (String) params.get("username");
        String startDate = (String) params.get("startDate");
        String endDate = (String) params.get("endDate");
        Page<SysLogEntity> pages = new Page<>(page, limit);
        QueryWrapper<SysLogEntity> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_time");
        wrapper.like(StringUtils.isNotBlank(username),"username",username);
        if(StringUtils.isNotBlank(startDate) && StringUtils.isNotBlank(endDate)){
            wrapper.between("create_time", startDate, endDate);
        }
        IPage<SysLogEntity> list = baseMapper.selectPage(pages, wrapper);
        return new PageUtils(list);
    }

}



