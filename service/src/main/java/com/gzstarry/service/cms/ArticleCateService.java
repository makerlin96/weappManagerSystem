package com.gzstarry.service.cms;

import com.gzstarry.common.utils.PageUtils;
import com.gzstarry.entity.cms.ArticleCateEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * 文章分类
 *
 * @author MakerLin makerlin96@gmail.com
 */
public interface ArticleCateService extends IService<ArticleCateEntity> {

    /**
     * 后台分页数据
     * @param params
     * @return
     */
    PageUtils selectByPage(Map<String, Object> params);
}
