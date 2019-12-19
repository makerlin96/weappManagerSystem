package com.gzstarry.entity.cms;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 文章分类
 *
 * @author MakerLin makerlin96@gmail.com
 */
@Data
@TableName("tb_article_cate")
public class ArticleCateEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId
    private Long cateId;
    private String cateName;
    private Integer sort;
    private Integer status;
    private Date createTime;
    private Date updateTime;

}
