package com.gzstarry.service.cms.impl;

import com.gzstarry.common.constant.Constant;
import com.gzstarry.common.utils.PageUtils;
import com.gzstarry.dao.cms.ArticleMapper;
import com.gzstarry.entity.cms.ArticleEntity;
import com.gzstarry.service.cms.ArticleService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 文章管理
 *
 * @author MakerLin makerlin96@gmail.com
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, ArticleEntity> implements ArticleService {

    @Autowired
    protected ArticleMapper articleMapper;
    @Autowired
    protected RedisTemplate redisTemplate;


    @Override
    public PageUtils selectByPage(Map<String, Object> params) {
        long page = Long.parseLong((String) params.get(Constant.PAGE));
        long limit = Long.parseLong((String) params.get(Constant.LIMIT));
        Page<ArticleEntity> pages = new Page<>(page, limit);
        List<ArticleEntity> list = baseMapper.getPageList(pages, params);
        pages.setRecords(list);
        return new PageUtils(pages);
    }


    /**
     * SpringBoot2.0版本的redis缓存设置
     * @param map
     * @return
     */
    @Override
    //@Cacheable("lunhui:cateTypeCount")
    public List<ArticleEntity> selectCateTypeByCount(@Param("map") Map<String,Object> map) {
        return baseMapper.selectCateTypeByCount(map);
    }
}
