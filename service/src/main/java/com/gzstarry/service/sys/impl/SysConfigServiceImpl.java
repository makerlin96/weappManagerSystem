package com.gzstarry.service.sys.impl;

import com.gzstarry.common.constant.Constant;
import com.gzstarry.common.exception.WebException;
import com.gzstarry.common.utils.PageUtils;
import com.gzstarry.common.utils.RedisUtils;
import com.gzstarry.dao.sys.SysConfigMapper;
import com.gzstarry.entity.sys.SysConfigEntity;
import com.gzstarry.service.sys.SysConfigService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 系统配置
 *
 * @author MakerLin makerlin96@gmail.com
 */
@Service("sysConfigService")
public class SysConfigServiceImpl extends ServiceImpl<SysConfigMapper, SysConfigEntity> implements SysConfigService {

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public PageUtils selectByPage(Map<String, Object> params) {
        long page = Long.parseLong((String) params.get(Constant.PAGE));
        long limit = Long.parseLong((String) params.get(Constant.LIMIT));
        String paramKey = (String) params.get("paramKey");
        Page<SysConfigEntity> pages = new Page<>(page, limit);
        QueryWrapper<SysConfigEntity> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id")
                .eq("status", 1)
                .like(StringUtils.isNotBlank(paramKey),"param_key",paramKey);
        IPage<SysConfigEntity> list = baseMapper.selectPage(pages,wrapper);
        return new PageUtils(list);
    }

    @Override
    public void saveConfig(SysConfigEntity config) {
        this.save(config);
        redisUtils.set("lunhui:config:" + config.getParamKey(), config);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateConfig(SysConfigEntity config) {
        this.updateById(config);
        redisUtils.set("lunhui:config:" + config.getParamKey(), config);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateValueByKey(String key, String value) {
        baseMapper.updateValueByKey(key, value);
        redisUtils.delete("lunhui:config:" + key);
    }

    @Override
    public String getValue(String key) {
        SysConfigEntity config = (SysConfigEntity) redisUtils.get("lunhui:config:" + key);
        if(config == null){
            config = baseMapper.getByParamKey(key);
            redisUtils.set("lunhui:config:" + key, config);
        }

        return config == null ? null : config.getParamValue();
    }

    @Override
    public <T> T getConfigObject(String key, Class<T> clazz) {
        String value = getValue(key);
        if(StringUtils.isNotBlank(value)){
            return new Gson().fromJson(value, clazz);
        }

        try {
            return clazz.newInstance();
        } catch (Exception e) {
            throw new WebException("获取参数失败");
        }
    }
}
