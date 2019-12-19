package com.gzstarry.common.constant;

/**
 * 常量
 *
 * @author MakerLin makerlin96@gmail.com
 */
public class Constant {
    /** 超级管理员 */
    public static final int SUPER_ADMIN = 1;
    /** 成功 */
    public static final String SUCCESS = "success";
    /** 失败 */
    public static final String FAIL = "fail";
    /** 开启 */
    public static final int OPEN = 0;
    /** 关闭*/
    public static final int CLOSE = 1;
    /** 当前页码 */
    public static final String PAGE = "page";
    /** 每页显示记录数 */
    public static final String LIMIT = "limit";
    /** 现金支付 */
    public static final String CASH = "cash";
    /** 微信支付 */
    public static final String WXPAY = "wxpay";
    /** 支付宝支付 */
    public static final String ALIPAY = "alipay";

    /** 菜单类型 */
    public enum MenuType {

        /** 菜单 */
        MENU(1),
        /** 按钮 */
        BUTTON(2);

        private int value;

        MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /** 定时任务状态 */
    public enum ScheduleStatus {
        /** 运行 */
    	NORMAL(0),
        /** 暂停 */
    	PAUSE(1);

        private int value;

        ScheduleStatus(int value) {
            this.value = value;
        }
        
        public int getValue() {
            return value;
        }
    }

    /** 云存储 */
    public enum CloudService {
        /** 本地 */
        LOCAL(1),
        /** 七牛云 */
        QINIU(2),
        /** 阿里云 */
        ALIYUN(3),
        /** 腾讯云 */
        QCLOUD(4),
        /** FASTDFS */
        FASTDFS(5);

        private int value;

        CloudService(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

}
