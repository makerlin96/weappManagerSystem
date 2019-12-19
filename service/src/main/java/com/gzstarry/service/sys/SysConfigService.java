package com.gzstarry.service.sys;

import com.gzstarry.common.utils.PageUtils;
import com.gzstarry.entity.sys.SysConfigEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * 系统配置
 *
 * @author MakerLin makerlin96@gmail.com
 */
public interface SysConfigService extends IService<SysConfigEntity> {

    /**
     * 后台分页数据
     * @param params
     * @return
     */
    PageUtils selectByPage(Map<String, Object> params);

    /**
     * 保存配置信息
     * @param config
     */
    void saveConfig(SysConfigEntity config);

    /**
     * 更新配置信息
     * @param config
     */
    void updateConfig(SysConfigEntity config);

    /**
     * 根据key，更新value
     * @param key
     * @param value
     */
    void updateValueByKey(String key, String value);

    /**
     * 根据key，获取配置的value值
     * @param key
     * @return
     */
    String getValue(String key);

    /**
     * 根据key，获取value的Object对象
     * @param key    key
     * @param clazz  Object对象
     * @return
     */
    <T> T getConfigObject(String key, Class<T> clazz);
}
