package com.gzstarry.entity.sys;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 系统用户
 *
 * @author MakerLin makerlin96@gmail.com
 */
@Data
@TableName("sys_user")
public class SysUserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private Long userId;

    @NotBlank(message="用户名不能为空")
    @Length(min=2,max=20,message="用户名长度必须在2-20位之间")
    private String username;

    //@NotBlank(message="密码不能为空")
    //@Length(min=6,max=20,message="密码长度必须在6-20位之间")
    private String password;

    private String realName;

    private String avatar;

    @NotBlank(message="邮箱不能为空")
    @Email(message="邮箱格式不正确")
    private String email;

    @NotBlank(message="手机号不能为空")
    private String mobile;

    private Integer superAdmin;

    private Integer status;

    private Date createTime;

    private Date lastLoginTime;
    /**
     * 角色ID列表
     */
    @TableField(exist=false)
    private List<Long> roleIdList;

}
