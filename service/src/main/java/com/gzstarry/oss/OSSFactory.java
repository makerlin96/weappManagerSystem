package com.gzstarry.oss;

import com.gzstarry.common.constant.ConfigConstant;
import com.gzstarry.common.constant.Constant;
import com.gzstarry.common.utils.SpringContextUtils;
import com.gzstarry.service.sys.SysConfigService;

/**
 * 文件上传Factory
 *
 * @author MakerLin makerlin96@gmail.com
 */
public final class OSSFactory {

    private static SysConfigService sysConfigService;

    static {
        OSSFactory.sysConfigService = (SysConfigService) SpringContextUtils.getBean("sysConfigService");
    }

    public static AbstractStorageService build(){
        //获取云存储配置信息
        CloudStorageConfig config = sysConfigService.getConfigObject(ConfigConstant.CLOUD_STORAGE_CONFIG_KEY, CloudStorageConfig.class);
        if(config.getType() == Constant.CloudService.LOCAL.getValue()){
            return new LocalStorageService(config);
        }else if(config.getType() == Constant.CloudService.QINIU.getValue()){
            return new QiniuCloudStorageService(config);
        }else if(config.getType() == Constant.CloudService.ALIYUN.getValue()){
            return new AliyunStorageService(config);
        }else if(config.getType() == Constant.CloudService.QCLOUD.getValue()){
            return new QcloudStorageService(config);
        }else if(config.getType() == Constant.CloudService.FASTDFS.getValue()){
            return new FastDFSStorageService(config);
        }
        return null;
    }

}
