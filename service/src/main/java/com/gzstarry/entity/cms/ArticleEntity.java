package com.gzstarry.entity.cms;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章管理
 *
 * @author MakerLin makerlin96@gmail.com
 */
@Data
@TableName("tb_article")
public class ArticleEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId
    private Long id;
    private String title;
    private Long cateId;
    private String flag;
    private String img;
    private String remark;
    private String keyword;
    private String content;
    private Long views;
    private Integer status;
    private String writer;
    private Date createTime;
    private Date updateTime;
    @TableField(exist=false)
    private String cateName;
    @TableField(exist=false)
    private Integer cateCount;

}
