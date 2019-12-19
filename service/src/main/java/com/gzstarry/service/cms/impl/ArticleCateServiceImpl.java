package com.gzstarry.service.cms.impl;

import com.gzstarry.common.constant.Constant;
import com.gzstarry.common.utils.PageUtils;
import com.gzstarry.dao.cms.ArticleCateMapper;
import com.gzstarry.entity.cms.ArticleCateEntity;
import com.gzstarry.service.cms.ArticleCateService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 文章分类
 *
 * @author MakerLin makerlin96@gmail.com
 */
@Service
public class ArticleCateServiceImpl extends ServiceImpl<ArticleCateMapper, ArticleCateEntity> implements ArticleCateService {

    @Autowired
    protected ArticleCateMapper articleCateMapper;

    @Override
    public PageUtils selectByPage(Map<String, Object> params) {
        long page = Long.parseLong((String) params.get(Constant.PAGE));
        long limit = Long.parseLong((String) params.get(Constant.LIMIT));
        String cateName = (String) params.get("cateName");
        Page<ArticleCateEntity> pages = new Page<>(page, limit);
        QueryWrapper<ArticleCateEntity> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_time");
        wrapper.like(StringUtils.isNotBlank(cateName),"cate_name",cateName);
        IPage<ArticleCateEntity> list = baseMapper.selectPage(pages,wrapper);
        return new PageUtils(list);
    }


}
