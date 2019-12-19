package com.gzstarry.dao.cms;

import com.gzstarry.entity.cms.BannerEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 广告管理
 *
 * @author MakerLin makerlin96@gmail.com
 */
@Mapper
@Repository
public interface BannerMapper extends BaseMapper<BannerEntity> {

}
