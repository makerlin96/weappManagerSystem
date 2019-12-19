package com.gzstarry.entity.sys;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import java.util.Date;

/**
 * Excel导出类
 *
 * @author MakerLin makerlin96@gmail.com
 */
@Data
public class SysUserExcelEntity {

    @Excel(name = "用户ID")
    private Long userId;

    @Excel(name = "账号")
    private String username;

    @Excel(name = "手机号")
    private String mobile;

    @Excel(name = "创建时间", format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
