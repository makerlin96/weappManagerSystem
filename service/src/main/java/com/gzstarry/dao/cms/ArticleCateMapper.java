package com.gzstarry.dao.cms;

import com.gzstarry.entity.cms.ArticleCateEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 文章分类
 *
 * @author MakerLin makerlin96@gmail.com
 */
@Mapper
@Repository
public interface ArticleCateMapper extends BaseMapper<ArticleCateEntity> {

}
