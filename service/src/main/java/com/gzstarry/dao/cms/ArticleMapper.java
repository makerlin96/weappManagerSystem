package com.gzstarry.dao.cms;

import com.gzstarry.entity.cms.ArticleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 文章管理
 *
 * @author MakerLin makerlin96@gmail.com
 */
@Mapper
@Repository
public interface ArticleMapper extends BaseMapper<ArticleEntity> {

    /**
     * 后台分页
     * @param pages
     * @param params
     * @return
     */
    @Select({
            "<script>",
            "SELECT a.id,a.title,a.keyword,a.views,a.status,a.writer,a.create_time,a.update_time,b.cate_name FROM tb_article a",
            "LEFT JOIN tb_article_cate b ON a.cate_id = b.cate_id",
            "WHERE 1 = 1",
            "<if test=\"params.title != null and params.title.trim() != ''\">",
            "AND a.title like concat('%',#{params.title},'%')",
            "</if>",
            "<if test=\"params.status != null and params.status.trim() != ''\">",
            "AND a.status = #{params.status}",
            "</if>",
            "<if test=\"null != params.startDate and params.startDate != '' and null != params.endDate and params.endDate != ''\">",
            "AND a.create_time between DATE_FORMAT(#{params.startDate},'%Y-%m-%d 00:00:00') and DATE_FORMAT(#{params.endDate},'%Y-%m-%d 23:59:59')",
            "</if>",
            "ORDER BY a.create_time desc",
            "</script>"
    })
    List<ArticleEntity> getPageList(Page pages, @Param("params") Map<String,Object> params);


    /**
     * 根据文章类型统计
     * @param map
     * @return
     */
    @Select({
            "<script>",
            "SELECT c.cate_name,IFNULL(e.count, 0) AS cateCount from tb_article_cate c",
            "LEFT JOIN (SELECT b.cate_id,count(b.cate_id) AS count FROM tb_article AS a",
            "LEFT JOIN tb_article_cate AS b ON a.cate_id = b.cate_id",
            "WHERE 1=1",
            "<if test=\"null != map.startTime and map.startTime != '' and null != map.endTime and map.endTime != ''\">",
            "and a.create_time between DATE_FORMAT(#{map.startTime},'%Y-%m-%d 00:00:00') and DATE_FORMAT(#{map.endTime},'%Y-%m-%d 23:59:59')",
            "</if>",
            "GROUP BY b.cate_id",
            ")",
            "e on c.cate_id=e.cate_id WHERE c.`status`=0",
            "</script>"
    })
    List<ArticleEntity> selectCateTypeByCount(@Param("map") Map<String,Object> map);
}
