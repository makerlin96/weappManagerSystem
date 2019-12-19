package com.gzstarry.entity.sys;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 登录表单
 *
 * @author MakerLin makerlin96@gmail.com
 */
@Data
@ApiModel(value="登录对象", description="登录对象")
public class SysLoginEntity {

    @ApiModelProperty(value = "账号")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "验证码")
    private String captcha;

}
