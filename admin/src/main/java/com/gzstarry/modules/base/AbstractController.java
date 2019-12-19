package com.gzstarry.modules.base;

import com.gzstarry.entity.sys.SysUserEntity;
import com.gzstarry.shiro.ShiroUtils;

/**
 * 基类控制器
 *
 * @author MakerLin makerlin96@gmail.com
 */
public abstract class AbstractController {

    protected SysUserEntity getUser() {
        return ShiroUtils.getUser();
    }

    protected Long getUserId() {
        return getUser().getUserId();
    }

}
