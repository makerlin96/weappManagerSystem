package com.gzstarry.entity.cms;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 广告管理
 *
 * @author MakerLin makerlin96@gmail.com
 */
@Data
@TableName("tb_banner")
public class BannerEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId
    private Long id;
    private String title;
    private Integer cateId;
    private String images;
    private String url;
    private Integer status;
    private Date createTime;
    private Date updateTime;
    private Integer sort;

}
