package com.gzstarry.service.cms;

import com.gzstarry.common.utils.PageUtils;
import com.gzstarry.entity.cms.ArticleEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * 文章管理
 *
 * @author MakerLin makerlin96@gmail.com
 */
public interface ArticleService extends IService<ArticleEntity> {


    /**
     * 后台分页
     * @param params
     * @return
     */
    PageUtils selectByPage(Map<String, Object> params);

    /**
     * 根据文章类型统计
     * @param map
     * @return
     */
    List<ArticleEntity> selectCateTypeByCount(Map<String, Object> map);
}
