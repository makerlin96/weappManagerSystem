package com.gzstarry.service.cms;

import com.gzstarry.common.utils.PageUtils;
import com.gzstarry.entity.cms.BannerEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * 广告管理
 *
 * @author MakerLin makerlin96@gmail.com
 */
public interface BannerService extends IService<BannerEntity> {


    /**
     * 后台分页
     * @param params
     * @return
     */
    PageUtils selectByPage(Map<String, Object> params);
}
