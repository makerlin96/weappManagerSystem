package com.gzstarry.service.sys;

import com.gzstarry.common.utils.PageUtils;
import com.gzstarry.entity.sys.SysOssEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * 文件上传
 *
 * @author MakerLin makerlin96@gmail.com
 */
public interface SysOssService extends IService<SysOssEntity> {

    /**
     * 后台分页
     * @param params
     * @return
     */
    PageUtils selectByPage(Map<String, Object> params);

}
