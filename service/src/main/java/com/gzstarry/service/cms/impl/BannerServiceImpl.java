package com.gzstarry.service.cms.impl;

import com.gzstarry.common.constant.Constant;
import com.gzstarry.common.utils.PageUtils;
import com.gzstarry.dao.cms.BannerMapper;
import com.gzstarry.entity.cms.BannerEntity;
import com.gzstarry.service.cms.BannerService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 广告管理
 *
 * @author MakerLin makerlin96@gmail.com
 */
@Service
public class BannerServiceImpl extends ServiceImpl<BannerMapper, BannerEntity> implements BannerService {

    @Autowired
    protected BannerMapper bannerMapper;

    @Override
    public PageUtils selectByPage(Map<String, Object> params) {
        long page = Long.parseLong((String) params.get(Constant.PAGE));
        long limit = Long.parseLong((String) params.get(Constant.LIMIT));
        String title = (String) params.get("title");
        Page<BannerEntity> pages = new Page<>(page, limit);
        QueryWrapper<BannerEntity> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_time");
        wrapper.like(StringUtils.isNotBlank(title),"title",title);
        IPage<BannerEntity> list = baseMapper.selectPage(pages,wrapper);
        return new PageUtils(list);
    }
}
