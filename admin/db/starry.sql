/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50553
 Source Host           : localhost:3306
 Source Schema         : lunhui-vue

 Target Server Type    : MySQL
 Target Server Version : 50553
 File Encoding         : 65001

 Date: 15/11/2019 16:08:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `BLOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `SCHED_NAME`(`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CALENDAR_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `CALENDAR_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CRON_EXPRESSION` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TIME_ZONE_ID` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------
INSERT INTO `qrtz_cron_triggers` VALUES ('WebScheduler', 'TASK_33', 'DEFAULT', '0 0 0/1 * * ? ', 'Asia/Shanghai');
INSERT INTO `qrtz_cron_triggers` VALUES ('WebScheduler', 'TASK_34', 'DEFAULT', '0 0 0/1 * * ? ', 'Asia/Shanghai');
INSERT INTO `qrtz_cron_triggers` VALUES ('WebScheduler', 'TASK_37', 'DEFAULT', '0 0 0/1 * * ?', 'Asia/Shanghai');

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ENTRY_ID` varchar(95) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `INSTANCE_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `ENTRY_ID`) USING BTREE,
  INDEX `IDX_QRTZ_FT_TRIG_INST_NAME`(`SCHED_NAME`, `INSTANCE_NAME`) USING BTREE,
  INDEX `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY`(`SCHED_NAME`, `INSTANCE_NAME`, `REQUESTS_RECOVERY`) USING BTREE,
  INDEX `IDX_QRTZ_FT_J_G`(`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_FT_JG`(`SCHED_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_FT_T_G`(`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_FT_TG`(`SCHED_NAME`, `TRIGGER_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `IS_DURABLE` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `IS_UPDATE_DATA` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_J_REQ_RECOVERY`(`SCHED_NAME`, `REQUESTS_RECOVERY`) USING BTREE,
  INDEX `IDX_QRTZ_J_GRP`(`SCHED_NAME`, `JOB_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------
INSERT INTO `qrtz_job_details` VALUES ('WebScheduler', 'TASK_33', 'DEFAULT', NULL, 'ScheduleJob', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B45597400B57B226A6F624964223A33332C226265616E4E616D65223A226A6F6254657374222C226D6574686F644E616D65223A2274657374222C22706172616D73223A22222C2263726F6E45787072657373696F6E223A2230203020302F31202A202A203F20222C22737461747573223A302C2272656D61726B223A22E68891E4B88DE5B8A6E58F82E695B0E689A7E8A18C222C2263726561746554696D65223A224A616E20342C203230313920383A34323A333220414D227D7800);
INSERT INTO `qrtz_job_details` VALUES ('WebScheduler', 'TASK_34', 'DEFAULT', NULL, 'ScheduleJob', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B45597400C37B226A6F624964223A33342C226265616E4E616D65223A226A6F6254657374222C226D6574686F644E616D65223A227465737432222C22706172616D73223A2261646D696E222C2263726F6E45787072657373696F6E223A2230203020302F31202A202A203F20222C22737461747573223A302C2272656D61726B223A22E68891E698AFE5B8A6E58F82E695B0E79A847465737432E696B9E6B395222C2263726561746554696D65223A224A616E20342C203230313920383A34333A303120414D227D7800);
INSERT INTO `qrtz_job_details` VALUES ('WebScheduler', 'TASK_37', 'DEFAULT', NULL, 'ScheduleJob', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B455974009D7B226A6F624964223A33372C226265616E4E616D65223A226A6F6254657374222C226D6574686F644E616D65223A2231222C22706172616D73223A22222C2263726F6E45787072657373696F6E223A2230203020302F31202A202A203F222C22737461747573223A302C2272656D61726B223A2231222C2263726561746554696D65223A2253657020392C203230313920333A34363A333920504D227D7800);

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `LOCK_NAME` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `LOCK_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
INSERT INTO `qrtz_locks` VALUES ('ApiScheduler', 'STATE_ACCESS');
INSERT INTO `qrtz_locks` VALUES ('ApiScheduler', 'TRIGGER_ACCESS');
INSERT INTO `qrtz_locks` VALUES ('WebScheduler', 'STATE_ACCESS');
INSERT INTO `qrtz_locks` VALUES ('WebScheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `INSTANCE_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `INSTANCE_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------
INSERT INTO `qrtz_scheduler_state` VALUES ('ApiScheduler', 'DESKTOP-PU1531K1572580526473', 1572587025620, 15000);
INSERT INTO `qrtz_scheduler_state` VALUES ('WebScheduler', 'DESKTOP-PU1531K1573786014143', 1573797228349, 15000);

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `STR_PROP_1` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `STR_PROP_2` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `STR_PROP_3` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `INT_PROP_1` int(11) NULL DEFAULT NULL,
  `INT_PROP_2` int(11) NULL DEFAULT NULL,
  `LONG_PROP_1` bigint(20) NULL DEFAULT NULL,
  `LONG_PROP_2` bigint(20) NULL DEFAULT NULL,
  `DEC_PROP_1` decimal(13, 4) NULL DEFAULT NULL,
  `DEC_PROP_2` decimal(13, 4) NULL DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) NULL DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) NULL DEFAULT NULL,
  `PRIORITY` int(11) NULL DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_TYPE` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) NULL DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) NULL DEFAULT NULL,
  `JOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_T_J`(`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_T_JG`(`SCHED_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_T_C`(`SCHED_NAME`, `CALENDAR_NAME`) USING BTREE,
  INDEX `IDX_QRTZ_T_G`(`SCHED_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_T_STATE`(`SCHED_NAME`, `TRIGGER_STATE`) USING BTREE,
  INDEX `IDX_QRTZ_T_N_STATE`(`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`, `TRIGGER_STATE`) USING BTREE,
  INDEX `IDX_QRTZ_T_N_G_STATE`(`SCHED_NAME`, `TRIGGER_GROUP`, `TRIGGER_STATE`) USING BTREE,
  INDEX `IDX_QRTZ_T_NEXT_FIRE_TIME`(`SCHED_NAME`, `NEXT_FIRE_TIME`) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_ST`(`SCHED_NAME`, `TRIGGER_STATE`, `NEXT_FIRE_TIME`) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_MISFIRE`(`SCHED_NAME`, `MISFIRE_INSTR`, `NEXT_FIRE_TIME`) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_ST_MISFIRE`(`SCHED_NAME`, `MISFIRE_INSTR`, `NEXT_FIRE_TIME`, `TRIGGER_STATE`) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP`(`SCHED_NAME`, `MISFIRE_INSTR`, `NEXT_FIRE_TIME`, `TRIGGER_GROUP`, `TRIGGER_STATE`) USING BTREE,
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------
INSERT INTO `qrtz_triggers` VALUES ('WebScheduler', 'TASK_33', 'DEFAULT', 'TASK_33', 'DEFAULT', NULL, 1567764000000, 1558796400000, 5, 'PAUSED', 'CRON', 1546562552000, 0, NULL, 2, 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B455974008F7B226A6F624964223A33332C226265616E4E616D65223A226A6F6254657374222C226D6574686F644E616D65223A2274657374222C22706172616D73223A22222C2263726F6E45787072657373696F6E223A2230203020302F31202A202A203F20222C22737461747573223A302C2272656D61726B223A22E68891E4B88DE5B8A6E58F82E695B0E689A7E8A18C227D7800);
INSERT INTO `qrtz_triggers` VALUES ('WebScheduler', 'TASK_34', 'DEFAULT', 'TASK_34', 'DEFAULT', NULL, 1567764000000, 1558796400000, 5, 'PAUSED', 'CRON', 1546562581000, 0, NULL, 2, 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B455974009A7B226A6F624964223A33342C226265616E4E616D65223A226A6F6254657374222C226D6574686F644E616D65223A227465737432222C22706172616D73223A2261646D696E222C2263726F6E45787072657373696F6E223A2230203020302F31202A202A203F20222C22737461747573223A302C2272656D61726B223A22E68891E698AFE5B8A6E58F82E695B0E79A847465737432E696B9227D7800);
INSERT INTO `qrtz_triggers` VALUES ('WebScheduler', 'TASK_37', 'DEFAULT', 'TASK_37', 'DEFAULT', NULL, 1568016000000, -1, 5, 'PAUSED', 'CRON', 1568015199000, 0, NULL, 2, '');

-- ----------------------------
-- Table structure for schedule_job
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job`;
CREATE TABLE `schedule_job`  (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务id',
  `bean_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'spring bean名称',
  `method_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '方法名',
  `params` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数',
  `cron_expression` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'cron表达式',
  `status` tinyint(4) NULL DEFAULT NULL COMMENT '任务状态  0：正常  1：暂停',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '定时任务' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of schedule_job
-- ----------------------------
INSERT INTO `schedule_job` VALUES (33, 'jobTest', 'test', '', '0 0 0/1 * * ? ', 1, '我不带参数执行', '2019-01-04 08:42:32');
INSERT INTO `schedule_job` VALUES (34, 'jobTest', 'test2', 'admin', '0 0 0/1 * * ? ', 1, '我是带参数的test2方', '2019-01-04 08:43:01');
INSERT INTO `schedule_job` VALUES (37, 'jobTest', 'test', '', '0 0 0/1 * * ?', 1, '1', '2019-09-09 15:46:39');

-- ----------------------------
-- Table structure for schedule_job_log
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job_log`;
CREATE TABLE `schedule_job_log`  (
  `log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志id',
  `job_id` bigint(20) NOT NULL COMMENT '任务id',
  `bean_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'spring bean名称',
  `method_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '方法名',
  `params` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数',
  `status` tinyint(4) NOT NULL COMMENT '任务状态    0：成功    1：失败',
  `error` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '失败信息',
  `times` int(11) NOT NULL COMMENT '耗时(单位：毫秒)',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`log_id`) USING BTREE,
  INDEX `job_id`(`job_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '定时任务日志' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of schedule_job_log
-- ----------------------------
INSERT INTO `schedule_job_log` VALUES (1, 33, 'jobTest', 'test', '', 0, NULL, 15, '2019-03-16 12:00:00');
INSERT INTO `schedule_job_log` VALUES (2, 33, 'jobTest', 'test', '', 0, NULL, 0, '2019-03-16 13:00:00');
INSERT INTO `schedule_job_log` VALUES (3, 33, 'jobTest', 'test', '', 0, NULL, 2, '2019-03-16 14:00:00');
INSERT INTO `schedule_job_log` VALUES (4, 33, 'jobTest', 'test', '', 0, NULL, 1, '2019-03-16 15:00:00');
INSERT INTO `schedule_job_log` VALUES (5, 33, 'jobTest', 'test', '', 0, NULL, 3, '2019-09-06 17:17:43');
INSERT INTO `schedule_job_log` VALUES (6, 34, 'jobTest', 'test2', 'admin', 0, NULL, 1, '2019-09-06 17:17:54');
INSERT INTO `schedule_job_log` VALUES (7, 34, 'jobTest', 'test2', 'admin', 0, NULL, 2, '2019-09-06 17:27:00');
INSERT INTO `schedule_job_log` VALUES (8, 33, 'jobTest', 'test', '', 0, NULL, 1, '2019-09-06 17:27:02');
INSERT INTO `schedule_job_log` VALUES (14, 34, 'jobTest', 'test2', 'admin', 0, NULL, 1, '2019-10-19 15:53:42');

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `param_key` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '键',
  `param_value` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '值',
  `status` tinyint(4) NULL DEFAULT NULL COMMENT '状态   0：隐藏   1：显示',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `key`(`param_key`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统配置表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES (1, 'CLOUD_STORAGE_CONFIG_KEY', '{\"type\":2,\"localDomain\":\"http://localhost:8888/api\",\"localPrefix\":\"resource\",\"localPath\":\"c:\\\\uploads\",\"qiniuDomain\":\"http://xxxxx.cn\",\"qiniuPrefix\":\"\",\"qiniuAccessKey\":\"xxxxx\",\"qiniuSecretKey\":\"xxxxx\",\"qiniuBucketName\":\"lunhui\",\"aliyunDomain\":\"\",\"aliyunPrefix\":\"\",\"aliyunEndPoint\":\"\",\"aliyunAccessKeyId\":\"\",\"aliyunAccessKeySecret\":\"\",\"aliyunBucketName\":\"\",\"qcloudDomain\":\"\",\"qcloudPrefix\":\"\",\"qcloudSecretId\":\"\",\"qcloudSecretKey\":\"\",\"qcloudBucketName\":\"\",\"qcloudRegion\":\"\",\"fastdfsDomain\":\"http://192.168.2.2/group1\"}', 0, '上传配置');
INSERT INTO `sys_config` VALUES (2, 'SMS_STORAGE_CONFIG_KEY', '{\"key\":\"xxxx\",\"secret\":\"xxxx\",\"signName\":\"xxxx\"}', 0, '短信配置');
INSERT INTO `sys_config` VALUES (3, 'JPUSH_STORAGE_CONFIG_KEY', '{\"key\":\"xxx\",\"secret\":\"xxx\"}', 0, '极光推送配置');
INSERT INTO `sys_config` VALUES (4, 'EMAIL_STORAGE_CONFIG_KEY', '1', 0, '邮件配置');
INSERT INTO `sys_config` VALUES (6, '1', '1', 1, '1');
INSERT INTO `sys_config` VALUES (9, '2', '2', 1, '2');

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `k` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `value` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '值 ',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '父ID',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '词典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES (1, 'SEX', '性别', '0', 0, 2);
INSERT INTO `sys_dict` VALUES (2, 'SEX', '男', '1', 1, 0);
INSERT INTO `sys_dict` VALUES (3, 'SEX', '女', '2', 1, 0);
INSERT INTO `sys_dict` VALUES (4, 'BANNER_CATE', '广告位置', '0', 0, 4);
INSERT INTO `sys_dict` VALUES (5, 'BANNER_CATE', 'PC首页轮播图大图', '1', 4, 5);
INSERT INTO `sys_dict` VALUES (6, 'BANNER_CATE', '移动端首页轮播图', '2', 4, 6);
INSERT INTO `sys_dict` VALUES (7, 'USERS_GROUP', '会员组', '0', 0, 7);
INSERT INTO `sys_dict` VALUES (8, 'USERS_GROUP', '系统组', '1', 7, 8);
INSERT INTO `sys_dict` VALUES (9, 'USERS_GROUP', '游客组', '2', 7, 9);
INSERT INTO `sys_dict` VALUES (26, 'USERS_GROUP', '测试组', '3', 7, 10);

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户操作',
  `method` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求方法',
  `params` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '请求参数',
  `time` int(11) NOT NULL COMMENT '执行时长(毫秒)',
  `ip` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'IP地址',
  `address` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `id`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '操作日志表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES (1, 'admin', '编辑菜单', 'SysPermissionController.doEdit()', '{\"id\":33,\"parentId\":10,\"name\":\"定时任务\",\"path\":\"/sys/job\",\"type\":1,\"perms\":\"sys:job:list\",\"icon\":\"bell\",\"sort\":6,\"parentName\":\"系统工具\"}', 13, '127.0.0.1', NULL, '2019-10-19 16:56:02');
INSERT INTO `sys_log` VALUES (2, 'admin', '编辑菜单', 'SysPermissionController.doEdit()', '{\"id\":87,\"parentId\":10,\"name\":\"文件上传\",\"path\":\"/sys/oss\",\"type\":1,\"perms\":\"sys:oss:list\",\"icon\":\"cloud\",\"sort\":10,\"parentName\":\"系统工具\"}', 3, '127.0.0.1', NULL, '2019-10-19 16:56:20');
INSERT INTO `sys_log` VALUES (3, 'admin', '编辑菜单', 'SysPermissionController.doEdit()', '{\"id\":68,\"parentId\":10,\"name\":\"SQL监控\",\"path\":\"/sys/druid\",\"type\":1,\"perms\":\"sys:druid:index\",\"icon\":\"database\",\"sort\":10,\"parentName\":\"系统工具\"}', 7, '127.0.0.1', NULL, '2019-10-19 16:56:57');
INSERT INTO `sys_log` VALUES (4, 'admin', '编辑菜单', 'SysPermissionController.doEdit()', '{\"id\":68,\"parentId\":10,\"name\":\"SQL监控\",\"path\":\"/sys/druid\",\"type\":1,\"perms\":\"\",\"icon\":\"database\",\"sort\":10,\"parentName\":\"系统工具\"}', 4, '127.0.0.1', NULL, '2019-10-19 17:00:03');
INSERT INTO `sys_log` VALUES (5, 'admin', '编辑菜单', 'SysPermissionController.doEdit()', '{\"id\":84,\"parentId\":0,\"name\":\"测试菜单\",\"path\":\"1\",\"type\":1,\"perms\":\"1\",\"icon\":\"user\",\"sort\":10,\"parentName\":\"\"}', 4, '127.0.0.1', NULL, '2019-10-19 17:01:53');
INSERT INTO `sys_log` VALUES (6, 'admin', '编辑菜单', 'SysPermissionController.doEdit()', '{\"id\":86,\"parentId\":84,\"name\":\"二级菜单\",\"path\":\"2\",\"type\":1,\"perms\":\"2\",\"icon\":\"2\",\"sort\":10,\"parentName\":\"测试菜单\"}', 5, '127.0.0.1', NULL, '2019-10-19 17:02:05');
INSERT INTO `sys_log` VALUES (7, 'admin', '添加菜单', 'SysPermissionController.doAdd()', '{\"id\":88,\"parentId\":86,\"name\":\"三级菜单\",\"path\":\"/sys/oss/upload\",\"type\":1,\"perms\":\"sys:oss:upload\",\"icon\":\"\",\"sort\":10,\"parentName\":\"二级菜单\"}', 50, '127.0.0.1', NULL, '2019-10-20 22:29:45');
INSERT INTO `sys_log` VALUES (8, 'admin', '编辑菜单', 'SysPermissionController.doEdit()', '{\"id\":88,\"parentId\":86,\"name\":\"三级菜单\",\"path\":\"/sys/oss\",\"type\":1,\"perms\":\"sys:oss:list\",\"icon\":\"\",\"sort\":10,\"parentName\":\"二级菜单\"}', 9, '127.0.0.1', NULL, '2019-10-20 22:30:32');
INSERT INTO `sys_log` VALUES (9, 'admin', '编辑菜单', 'SysPermissionController.doEdit()', '{\"id\":86,\"parentId\":84,\"name\":\"二级菜单\",\"path\":\"2\",\"type\":1,\"perms\":\"2\",\"icon\":\"cog\",\"sort\":10,\"parentName\":\"测试菜单\"}', 2, '127.0.0.1', NULL, '2019-10-20 22:31:18');
INSERT INTO `sys_log` VALUES (10, 'admin', '编辑菜单', 'SysPermissionController.doEdit()', '{\"id\":88,\"parentId\":86,\"name\":\"三级菜单\",\"path\":\"/sys/oss\",\"type\":1,\"perms\":\"sys:oss:list\",\"icon\":\"cog\",\"sort\":10,\"parentName\":\"二级菜单\"}', 5, '127.0.0.1', NULL, '2019-10-20 22:31:25');
INSERT INTO `sys_log` VALUES (11, 'admin', '添加菜单', 'SysPermissionController.doAdd()', '{\"id\":89,\"parentId\":87,\"name\":\"保存配置\",\"path\":\"\",\"type\":2,\"perms\":\"sys:oss:save\",\"icon\":\"\",\"sort\":10,\"parentName\":\"文件上传\"}', 53, '127.0.0.1', NULL, '2019-10-21 12:18:15');
INSERT INTO `sys_log` VALUES (12, 'admin', '添加菜单', 'SysPermissionController.doAdd()', '{\"id\":90,\"parentId\":87,\"name\":\"删除文件\",\"path\":\"\",\"type\":2,\"perms\":\"sys:oss:del\",\"icon\":\"\",\"sort\":0,\"parentName\":\"文件上传\"}', 10, '127.0.0.1', NULL, '2019-10-21 12:18:35');
INSERT INTO `sys_log` VALUES (13, 'admin', '删除文件', 'SysOssController.del()', '[33,32]', 8, '127.0.0.1', NULL, '2019-10-21 12:19:23');
INSERT INTO `sys_log` VALUES (14, 'admin', '删除文件', 'SysOssController.del()', '[75,66,67,68,69,70,71,72,73,74]', 8, '127.0.0.1', NULL, '2019-11-01 14:15:55');
INSERT INTO `sys_log` VALUES (15, 'admin', '删除文件', 'SysOssController.del()', '[65,64,63,62,59,60,61,58,57,56]', 4, '127.0.0.1', NULL, '2019-11-01 14:16:01');
INSERT INTO `sys_log` VALUES (16, 'admin', '删除文件', 'SysOssController.del()', '[55]', 14, '127.0.0.1', NULL, '2019-11-01 15:15:53');
INSERT INTO `sys_log` VALUES (17, 'admin', '删除任务日志', 'ScheduleJobController.logDel()', '[13,12]', 17, '127.0.0.1', NULL, '2019-11-12 14:52:05');
INSERT INTO `sys_log` VALUES (18, 'admin', '删除任务日志', 'ScheduleJobController.logDel()', '[11,10]', 9, '127.0.0.1', NULL, '2019-11-12 14:52:39');
INSERT INTO `sys_log` VALUES (19, 'admin', '删除任务日志', 'ScheduleJobController.logDel()', '[9]', 9, '127.0.0.1', NULL, '2019-11-12 14:52:49');
INSERT INTO `sys_log` VALUES (20, 'admin', '编辑文章', 'ArticleController.edit()', '{\"id\":104,\"title\":\"啊啊啊啊啊\",\"keyword\":\"222\",\"status\":0}', 72, '127.0.0.1', NULL, '2019-11-12 16:32:05');
INSERT INTO `sys_log` VALUES (21, 'admin', '删除文件', 'SysOssController.del()', '[56]', 7, '127.0.0.1', NULL, '2019-11-13 09:05:16');
INSERT INTO `sys_log` VALUES (22, 'admin', '编辑文章', 'ArticleController.edit()', '{\"id\":104,\"title\":\"啊啊啊啊啊\",\"keyword\":\"222\",\"status\":0}', 63, '127.0.0.1', NULL, '2019-11-15 10:48:40');

-- ----------------------------
-- Table structure for sys_oss
-- ----------------------------
DROP TABLE IF EXISTS `sys_oss`;
CREATE TABLE `sys_oss`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'URL地址',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 58 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文件上传' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_oss
-- ----------------------------
INSERT INTO `sys_oss` VALUES (1, 'http://img.ilunhui.cn/20191016/ca4dd5bd1f7c474cb2d7e1e7bc20887d.jpg', '2019-10-16 00:01:35');
INSERT INTO `sys_oss` VALUES (2, 'http://img.ilunhui.cn/20191019/d0317204b9e04fb8ae884098cc6e4132.jpg', '2019-10-19 00:14:43');
INSERT INTO `sys_oss` VALUES (3, 'http://img.ilunhui.cn/20191019/6dcab8fccb554c9c8b387909bc6ae355.jpg', '2019-10-19 00:16:33');
INSERT INTO `sys_oss` VALUES (4, 'http://img.ilunhui.cn/20191019/12973f901d984eaf9163927cc837a315.png', '2019-10-19 00:16:42');
INSERT INTO `sys_oss` VALUES (5, 'http://img.ilunhui.cn/20191019/49fee2246fc74a889d10644e7124ddc7.png', '2019-10-19 00:19:29');
INSERT INTO `sys_oss` VALUES (6, 'http://img.ilunhui.cn/20191019/a05c717ca2e043e6a00765da4409a26d.png', '2019-10-19 00:21:01');
INSERT INTO `sys_oss` VALUES (27, 'http://img.ilunhui.cn/20191019/cc222914857f48b8aa468fd8f38011c0.png', '2019-10-19 15:49:24');
INSERT INTO `sys_oss` VALUES (29, 'http://img.ilunhui.cn/20191020/e655cf2fe7e640d2844198a16a3d48d2.dwg', '2019-10-20 12:27:21');
INSERT INTO `sys_oss` VALUES (30, 'http://img.ilunhui.cn/20191020/64b75140e28e482ab2309c1076d030e6.pdf', '2019-10-20 22:24:09');
INSERT INTO `sys_oss` VALUES (34, 'http://localhost:8888/api/resource/20191021/48937ae3620e42db96ab8c8ab183c272.png', '2019-10-21 12:14:17');
INSERT INTO `sys_oss` VALUES (36, 'http://img.ilunhui.cn/d50d3632483a48e1979ef0c3342ef7b5.jpg', '2019-10-22 12:52:30');
INSERT INTO `sys_oss` VALUES (37, 'http://localhost:8888/api/resource/0c3e1b45579941a389fc8dc2693a5780.jpg', '2019-10-22 12:53:08');
INSERT INTO `sys_oss` VALUES (38, 'http://192.168.2.2/group1/M00/00/00/wKgCAl23sPGAQ-nXAABE-8w15Xk11..jpg', '2019-10-29 11:24:34');
INSERT INTO `sys_oss` VALUES (39, 'http://192.168.2.2/group1/M00/00/00/wKgCAl23sP6AD79JAAi4ihKSHRQ53..png', '2019-10-29 11:24:47');
INSERT INTO `sys_oss` VALUES (40, 'http://192.168.2.2/group1/M00/00/00/wKgCAl23sYaAfxpuAAE0fINGTQE77..jpg', '2019-10-29 11:27:02');
INSERT INTO `sys_oss` VALUES (41, 'http://192.168.2.2/group1/M00/00/00/wKgCAl23sYqAXeeMAApd5-IJr5k38..png', '2019-10-29 11:27:06');
INSERT INTO `sys_oss` VALUES (42, 'http://192.168.2.2/group1/M00/00/00/wKgCAl23samAeuieAABE-8w15Xk82..jpg', '2019-10-29 11:30:59');
INSERT INTO `sys_oss` VALUES (43, 'http://192.168.2.2/group1/M00/00/00/wKgCAl23srCAarAVAAB0hmyBOBc14..jpg', '2019-10-29 11:32:00');
INSERT INTO `sys_oss` VALUES (44, 'http://192.168.2.2/group1/M00/00/00/wKgCAl23ss-AcyKhAABE-8w15Xk685.jpg', '2019-10-29 11:32:32');
INSERT INTO `sys_oss` VALUES (45, 'http://localhost:8888/api/resource/6d61aa922b1c463595d558376410192epng', '2019-10-29 11:32:56');
INSERT INTO `sys_oss` VALUES (46, 'http://localhost:8888/api/resource/b179b47d10094fd2811b5bc01564f533jpg', '2019-10-29 11:40:17');
INSERT INTO `sys_oss` VALUES (47, 'http://localhost:8888/api/resource/214a533316b1423e920294377f9955ccjpg', '2019-10-29 11:40:36');
INSERT INTO `sys_oss` VALUES (48, 'http://192.168.2.2/group1/M00/00/00/wKgCAl23tNaADXs_AAi4ihKSHRQ086.png', '2019-10-29 11:41:11');
INSERT INTO `sys_oss` VALUES (49, 'http://localhost:8888/api/resource/23e8ff3c14714cba9471f05f31ec9373jpg', '2019-10-29 11:42:51');
INSERT INTO `sys_oss` VALUES (50, 'http://localhost:8888/api/resource/c38e405b383044f791c6511afb7d8d2fjpg', '2019-10-29 11:44:33');
INSERT INTO `sys_oss` VALUES (51, 'http://localhost:8888/api/resource/8b9a9a7a4ba4490cb3c9e65dab298dfa.jpg', '2019-10-29 11:45:19');
INSERT INTO `sys_oss` VALUES (52, 'http://img.ilunhui.cn/48d3ecb89a754d3fbfed51f5d7ac1c75.jpg', '2019-10-29 13:50:54');
INSERT INTO `sys_oss` VALUES (53, 'http://localhost:8888/api/resource/c64f2fe559d049eeb4b9bc61f0a2c19d.jpg', '2019-10-29 13:55:08');
INSERT INTO `sys_oss` VALUES (54, 'http://192.168.2.2/group1/M00/00/00/wKgCAl231EmARIv1AAB0hmyBOBc436.jpg', '2019-10-29 13:55:21');
INSERT INTO `sys_oss` VALUES (55, 'http://localhost:8888/api/resource/ea88806913844416893a050aebac4609.jpg', '2019-11-13 09:01:04');
INSERT INTO `sys_oss` VALUES (57, 'http://img.ilunhui.cn/c5052963f17943228e64e1887ae2a1fc.docx', '2019-11-13 09:05:27');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `path` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单URL',
  `type` smallint(1) NULL DEFAULT NULL,
  `perms` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '授权 多个用逗号分隔，如：user:list,user:create)',
  `icon` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 91 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单管理' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES (1, 0, '系统管理', '/sys', 1, NULL, 'cog', 1);
INSERT INTO `sys_permission` VALUES (2, 1, '用户管理', '/sys/user', 1, 'sys:user:list', 'user', 3);
INSERT INTO `sys_permission` VALUES (3, 1, '系统参数', '/sys/config', 1, 'sys:config:list', 'gears', 9);
INSERT INTO `sys_permission` VALUES (4, 1, '数据字典', '/sys/dict', 1, 'sys:dict:list', 'clipboard-list', 4);
INSERT INTO `sys_permission` VALUES (5, 1, '角色管理', '/sys/role', 1, 'sys:role:list', 'users', 4);
INSERT INTO `sys_permission` VALUES (7, 1, '菜单管理', '/sys/permission', 1, 'sys:permission:list,sys:permission:info', 'icon-menu', 6);
INSERT INTO `sys_permission` VALUES (8, 0, '内容管理', '/cms', 1, '', 'paper-plane', 2);
INSERT INTO `sys_permission` VALUES (9, 3, '添加配置', '', 2, 'sys:config:add', '', 10);
INSERT INTO `sys_permission` VALUES (10, 0, '系统工具', '/tools', 1, '', 'icon-menu', 3);
INSERT INTO `sys_permission` VALUES (11, 7, '添加菜单', '', 2, 'sys:permission:add', '', 10);
INSERT INTO `sys_permission` VALUES (12, 7, '编辑菜单', '', 2, 'sys:permission:edit', '', 11);
INSERT INTO `sys_permission` VALUES (13, 7, '删除菜单', '', 2, 'sys:permission:del', '', 12);
INSERT INTO `sys_permission` VALUES (14, 10, '图标库', '/icons/index', 1, 'tools:icon:list', 'award', 2);
INSERT INTO `sys_permission` VALUES (16, 2, '添加用户', '', 2, 'sys:user:add', NULL, 15);
INSERT INTO `sys_permission` VALUES (17, 2, '编辑用户', '', 2, 'sys:user:edit', NULL, 16);
INSERT INTO `sys_permission` VALUES (18, 2, '删除用户', '', 2, 'sys:user:del', NULL, 17);
INSERT INTO `sys_permission` VALUES (19, 2, '用户信息', '', 2, 'sys:user:info', '', 8);
INSERT INTO `sys_permission` VALUES (22, 4, '添加词典', '', 2, 'sys:dict:add', NULL, 19);
INSERT INTO `sys_permission` VALUES (23, 4, '编辑词典', '', 2, 'sys:dict:edit', NULL, 20);
INSERT INTO `sys_permission` VALUES (33, 10, '定时任务', '/sys/job', 1, 'sys:job:list', 'bell', 6);
INSERT INTO `sys_permission` VALUES (45, 4, '删除词典', '', 2, 'sys:dict:del', NULL, 22);
INSERT INTO `sys_permission` VALUES (50, 33, '创建任务', '', 2, 'sys:job:add', NULL, 1);
INSERT INTO `sys_permission` VALUES (51, 33, '编辑任务', '', 2, 'sys:job:edit', NULL, 2);
INSERT INTO `sys_permission` VALUES (52, 33, '暂停任务', '', 2, 'sys:job:pause', NULL, 3);
INSERT INTO `sys_permission` VALUES (53, 33, '恢复任务', '', 2, 'sys:job:resume', NULL, 4);
INSERT INTO `sys_permission` VALUES (54, 33, '运行任务', '', 2, 'sys:job:run', NULL, 5);
INSERT INTO `sys_permission` VALUES (55, 33, '删除任务', '', 2, 'sys:job:del', NULL, 6);
INSERT INTO `sys_permission` VALUES (57, 1, '日志管理', '/sys/log', 1, 'sys:log:list', 'bug', 12);
INSERT INTO `sys_permission` VALUES (58, 57, '删除日志', '', 2, 'sys:log:del', NULL, 10);
INSERT INTO `sys_permission` VALUES (59, 5, '添加角色', '', 2, 'sys:role:add', NULL, 25);
INSERT INTO `sys_permission` VALUES (60, 5, '编辑角色', '', 2, 'sys:role:edit', NULL, 26);
INSERT INTO `sys_permission` VALUES (62, 5, '删除角色', '', 2, 'sys:role:del', NULL, 27);
INSERT INTO `sys_permission` VALUES (66, 3, '编辑配置', '', 2, 'sys:config:edit', '', 10);
INSERT INTO `sys_permission` VALUES (67, 3, '删除配置', '', 2, 'sys:config:del', '', 10);
INSERT INTO `sys_permission` VALUES (68, 10, 'SQL监控', '/sys/druid', 1, '', 'database', 10);
INSERT INTO `sys_permission` VALUES (75, 8, '文章列表', '/cms/article', 1, 'cms:article:list', 'align-left', 2);
INSERT INTO `sys_permission` VALUES (76, 75, '添加文章', '', 2, 'cms:article:add', '', 1);
INSERT INTO `sys_permission` VALUES (77, 75, '编辑文章', '', 2, 'cms:article:edit', '', 2);
INSERT INTO `sys_permission` VALUES (78, 75, '删除文章', '', 2, 'cms:article:del', '', 3);
INSERT INTO `sys_permission` VALUES (80, 8, '文章分类', '/cms/cate', 1, 'cms:cate:list', 'file-text', 1);
INSERT INTO `sys_permission` VALUES (81, 80, '添加分类', '', 2, 'cms:cate:add', '', 10);
INSERT INTO `sys_permission` VALUES (82, 80, '编辑分类', '', 2, 'cms:cate:edit', '', 10);
INSERT INTO `sys_permission` VALUES (83, 80, '删除分类', '', 2, 'cms:cate:del', '', 10);
INSERT INTO `sys_permission` VALUES (84, 0, '测试菜单', '1', 1, '1', 'user', 10);
INSERT INTO `sys_permission` VALUES (86, 84, '二级菜单', '2', 1, '2', 'cog', 10);
INSERT INTO `sys_permission` VALUES (87, 10, '文件上传', '/sys/oss', 1, 'sys:oss:list', 'cloud', 10);
INSERT INTO `sys_permission` VALUES (88, 86, '三级菜单', '/sys/oss', 1, 'sys:oss:list', 'cog', 10);
INSERT INTO `sys_permission` VALUES (89, 87, '保存配置', '', 2, 'sys:oss:save', '', 10);
INSERT INTO `sys_permission` VALUES (90, 87, '删除文件', '', 2, 'sys:oss:del', '', 0);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`) USING BTREE,
  UNIQUE INDEX `role_name`(`role_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', '888888', '具有所有权限的操作', '2017-11-21 11:42:11');
INSERT INTO `sys_role` VALUES (2, '一般用户', '123456', '只能操作部分功能', '2017-11-21 17:38:29');
INSERT INTO `sys_role` VALUES (3, '测试权限', NULL, '1234', '2019-07-13 13:49:09');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色ID',
  `permission_id` bigint(20) NULL DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 124 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色与菜单对应关系' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES (75, 1, 1);
INSERT INTO `sys_role_permission` VALUES (76, 1, 2);
INSERT INTO `sys_role_permission` VALUES (77, 1, 19);
INSERT INTO `sys_role_permission` VALUES (78, 1, 58);
INSERT INTO `sys_role_permission` VALUES (79, 1, 16);
INSERT INTO `sys_role_permission` VALUES (80, 1, 17);
INSERT INTO `sys_role_permission` VALUES (81, 1, 18);
INSERT INTO `sys_role_permission` VALUES (82, 1, 4);
INSERT INTO `sys_role_permission` VALUES (83, 1, 22);
INSERT INTO `sys_role_permission` VALUES (84, 1, 23);
INSERT INTO `sys_role_permission` VALUES (85, 1, 45);
INSERT INTO `sys_role_permission` VALUES (86, 1, 5);
INSERT INTO `sys_role_permission` VALUES (87, 1, 59);
INSERT INTO `sys_role_permission` VALUES (88, 1, 60);
INSERT INTO `sys_role_permission` VALUES (89, 1, 62);
INSERT INTO `sys_role_permission` VALUES (90, 1, 65);
INSERT INTO `sys_role_permission` VALUES (91, 1, 7);
INSERT INTO `sys_role_permission` VALUES (92, 1, 11);
INSERT INTO `sys_role_permission` VALUES (93, 1, 12);
INSERT INTO `sys_role_permission` VALUES (94, 1, 13);
INSERT INTO `sys_role_permission` VALUES (95, 1, 33);
INSERT INTO `sys_role_permission` VALUES (96, 1, 50);
INSERT INTO `sys_role_permission` VALUES (97, 1, 51);
INSERT INTO `sys_role_permission` VALUES (98, 1, 52);
INSERT INTO `sys_role_permission` VALUES (99, 1, 53);
INSERT INTO `sys_role_permission` VALUES (100, 1, 54);
INSERT INTO `sys_role_permission` VALUES (101, 1, 55);
INSERT INTO `sys_role_permission` VALUES (102, 1, 56);
INSERT INTO `sys_role_permission` VALUES (103, 1, 3);
INSERT INTO `sys_role_permission` VALUES (104, 1, 9);
INSERT INTO `sys_role_permission` VALUES (105, 1, 66);
INSERT INTO `sys_role_permission` VALUES (106, 1, 67);
INSERT INTO `sys_role_permission` VALUES (107, 1, 64);
INSERT INTO `sys_role_permission` VALUES (108, 1, 68);
INSERT INTO `sys_role_permission` VALUES (109, 1, 57);
INSERT INTO `sys_role_permission` VALUES (110, 1, 74);
INSERT INTO `sys_role_permission` VALUES (111, 1, 72);
INSERT INTO `sys_role_permission` VALUES (112, 1, 8);
INSERT INTO `sys_role_permission` VALUES (113, 1, 80);
INSERT INTO `sys_role_permission` VALUES (114, 1, 81);
INSERT INTO `sys_role_permission` VALUES (115, 1, 82);
INSERT INTO `sys_role_permission` VALUES (116, 1, 83);
INSERT INTO `sys_role_permission` VALUES (117, 1, 75);
INSERT INTO `sys_role_permission` VALUES (118, 1, 76);
INSERT INTO `sys_role_permission` VALUES (119, 1, 77);
INSERT INTO `sys_role_permission` VALUES (120, 1, 78);
INSERT INTO `sys_role_permission` VALUES (121, 2, 19);
INSERT INTO `sys_role_permission` VALUES (122, 2, 1);
INSERT INTO `sys_role_permission` VALUES (123, 2, 2);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `real_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `avatar` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `super_admin` tinyint(1) NULL DEFAULT NULL COMMENT '超级管理员  0不是    1是',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '状态  0正常   1禁用',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `last_login_time` datetime NULL DEFAULT NULL COMMENT '最后登录时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统用户' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', '管理员', 'https://wpimg.wallstcn.com/69a1c46c-eb1c-4b46-8bd4-e9e686ef5251.png', 'makerlin96@gmail.com', '18693281982', 1, 0, '2019-06-09 11:11:11', '2019-11-15 10:57:14');
INSERT INTO `sys_user` VALUES (2, 'test', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'test', 'https://wpimg.wallstcn.com/69a1c46c-eb1c-4b46-8bd4-e9e686ef5251.png', '123456@qq.com', '18693281909', 0, 0, '2018-01-08 22:49:26', '2019-09-06 13:41:34');
INSERT INTO `sys_user` VALUES (3, 'lisi', '09fba7943e22ac2eda2633813def105a4bd4d4020f35c9a23ec145dfc75211d9', '李四', 'https://wpimg.wallstcn.com/69a1c46c-eb1c-4b46-8bd4-e9e686ef5251.png', '54545@qq.com', '18693281909', 0, 0, '2018-01-08 22:49:26', '2019-05-29 15:19:09');
INSERT INTO `sys_user` VALUES (4, 'luoyonghao', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', '罗永浩', 'http://img.ilunhui.cn/8ed38df8-45f2-4cd9-af78-701989c77020.jpg', 'makerlin96@gmail.com', '18693281909', 0, 1, '2018-01-08 22:49:26', '2019-05-29 15:19:09');
INSERT INTO `sys_user` VALUES (5, 'test3', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'test3', 'https://wpimg.wallstcn.com/69a1c46c-eb1c-4b46-8bd4-e9e686ef5251.png', 'makerlin96@gmail.com', '18693281909', 0, 1, '2018-01-08 22:49:26', '2019-05-29 15:19:09');
INSERT INTO `sys_user` VALUES (6, 'test2', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'test2', 'http://img.ilunhui.cn/8ed38df8-45f2-4cd9-af78-701989c77020.jpg', 'makerlin96@gmail.com', '18693281909', 0, 1, '2018-01-08 22:49:26', '2019-05-29 15:19:09');
INSERT INTO `sys_user` VALUES (7, 'haha', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', '哈哈', 'https://wpimg.wallstcn.com/69a1c46c-eb1c-4b46-8bd4-e9e686ef5251.png', 'makerlin96@gmail.com', '18693281909', 0, 0, '2018-01-08 22:49:26', '2019-05-29 15:19:09');
INSERT INTO `sys_user` VALUES (8, 'test5', '96cae35ce8a9b0244178bf28e4966c2ce1b8385723a96a6b838858cdd6ca0a1e', 'test5', 'https://wpimg.wallstcn.com/69a1c46c-eb1c-4b46-8bd4-e9e686ef5251.png', 'makerlin96@gmail.com', '18693281909', 0, 0, '2018-01-08 22:49:26', '2019-05-29 15:19:09');
INSERT INTO `sys_user` VALUES (14, 'testuser', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', '测试用户', 'https://wpimg.wallstcn.com/69a1c46c-eb1c-4b46-8bd4-e9e686ef5251.png', 'vdfvdfvd@qq.com', '11111111111', 0, 0, '2019-07-12 13:45:55', NULL);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 56 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户与角色对应关系' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (14, 21, 2);
INSERT INTO `sys_user_role` VALUES (15, 21, 1);
INSERT INTO `sys_user_role` VALUES (16, 21, 3);
INSERT INTO `sys_user_role` VALUES (17, 21, 4);
INSERT INTO `sys_user_role` VALUES (20, 24, 2);
INSERT INTO `sys_user_role` VALUES (21, 24, 3);
INSERT INTO `sys_user_role` VALUES (22, 25, 2);
INSERT INTO `sys_user_role` VALUES (23, 25, 3);
INSERT INTO `sys_user_role` VALUES (26, 16, 2);
INSERT INTO `sys_user_role` VALUES (27, 16, 3);
INSERT INTO `sys_user_role` VALUES (33, 23, 1);
INSERT INTO `sys_user_role` VALUES (34, 23, 2);
INSERT INTO `sys_user_role` VALUES (39, 26, 2);
INSERT INTO `sys_user_role` VALUES (40, 26, 3);
INSERT INTO `sys_user_role` VALUES (41, 14, 3);
INSERT INTO `sys_user_role` VALUES (42, 14, 4);
INSERT INTO `sys_user_role` VALUES (45, 27, 2);
INSERT INTO `sys_user_role` VALUES (50, 1, 1);
INSERT INTO `sys_user_role` VALUES (52, 2, 2);
INSERT INTO `sys_user_role` VALUES (53, 8, 2);
INSERT INTO `sys_user_role` VALUES (54, 3, 2);
INSERT INTO `sys_user_role` VALUES (55, 7, 2);

-- ----------------------------
-- Table structure for tb_article
-- ----------------------------
DROP TABLE IF EXISTS `tb_article`;
CREATE TABLE `tb_article`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '文章ID',
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章标题',
  `cate_id` int(11) NULL DEFAULT 1 COMMENT '文章类别',
  `flag` set('H','T','G') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '头条[H]  推荐[T]  滚动[G]',
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '文章图片',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '文章描述',
  `keyword` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '文章关键字',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '文章内容',
  `views` int(11) NULL DEFAULT 1 COMMENT '浏览量',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '状态  0正常  1禁用',
  `writer` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `a_title`(`title`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 105 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文章表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_article
-- ----------------------------
INSERT INTO `tb_article` VALUES (27, 'React Native 的 2017 年终总结', 63, 'H,T', 'http://pbfngo7k2.bkt.clouddn.com/04456e39c59043dabe88583085c3c674.jpg', '现在 RN 更新的速度已经比刚出来那会儿慢了，但是一个月一个正式版，还是让许多使用 RN 的开发者感到苦恼。因为经常在更新后项目就跑不起来了，所以很多团队都选择了不更新，但也就没法用最新的 API 了。我觉得最好进行跨版本升级，因为有些版本之间的差别不是很大，我们可以忽略掉。', 'React Native', '1', 271, 0, 'admin', '2018-01-25 11:55:10', '2019-04-09 09:16:21');
INSERT INTO `tb_article` VALUES (32, 'Win10无法使用内置管理员账户打开程序', 57, 'H', 'http://pbfngo7k2.bkt.clouddn.com/04456e39c59043dabe88583085c3c674.jpg', '在win10/win8.1切换使用Administrator时，会出现无法使用内置管理员账户打开程序的情况。这种问题产生的原因是，本地组策略配置不符合。具体解决办法：', 'Win10', '1', 43, 0, 'admin', '2018-01-25 11:55:10', '2018-07-04 09:17:02');
INSERT INTO `tb_article` VALUES (40, 'ThinkPHP中的动态缓存（S方法）和快速缓存（F方法）', 27, 'H,T', 'http://pbfngo7k2.bkt.clouddn.com/04456e39c59043dabe88583085c3c674.jpg', '系统默认的缓存方式是采用File方式缓存，我们可以在项目配置文件里面定义其他的缓存方式，例如，修改默认的缓存方式为Xcache（当然，你的环境需要支持Xcache）', '缓存', '1', 275, 0, 'admin', '2018-01-25 11:55:10', '2018-09-14 14:40:36');
INSERT INTO `tb_article` VALUES (42, 'php使用session来保存用户登录信息', 29, 'H', 'http://pbfngo7k2.bkt.clouddn.com/04456e39c59043dabe88583085c3c674.jpg', 'php使用session来保存用户登录信息,使用session保存页面登录信息。', 'session', '1', 240, 0, 'admin', '2018-01-25 11:55:10', '2018-06-14 21:57:32');
INSERT INTO `tb_article` VALUES (43, 'CSS固定定位{position:fixed}', 63, 'H', 'http://pbfngo7k2.bkt.clouddn.com/04456e39c59043dabe88583085c3c674.jpg', '不知道您是否留意了，浏览本站时，浏览器右下角有一个标着top的返回顶部小火箭，可以点击它返回到正在浏览的网页页眉。当滚动网页时，它的位置一直没有任何改变，您感觉它怎么样？这就是通过CSS的定位属性{position:fixed}来实现的，通过它可以让HTML元素脱离文档流固定在浏览器的某个位置。', 'position', '1', 129, 0, 'admin', '2018-01-25 11:55:10', '2019-03-08 14:55:11');
INSERT INTO `tb_article` VALUES (44, 'css input[type=file] 样式美化，input上传按钮美化', 29, 'H', 'http://pbfngo7k2.bkt.clouddn.com/04456e39c59043dabe88583085c3c674.jpg', '我们在做input文本上传的时候，html自带的上传按钮比较丑，如何对其进行美化呢？同理：input checkbox美化，input radio美化是一个道理的，后面文章会总结。', '按钮美化', '1', 199, 0, 'admin', '2018-01-25 11:55:10', '2018-06-14 21:57:06');
INSERT INTO `tb_article` VALUES (46, 'PHP人民币金额数字转中文大写的函数代码', 65, 'H', 'http://img.ilunhui.cn/20181115091509871975.jpg', '在网上看到一个非常有趣的PHP人民币金额数字转中文大写的函数，其实质就是数字转换成中文大写，测试了一下，非常有趣，随便输个数字，就可以将其大写打印出来，新手朋友们试一下吧', '人民币转大写', '1', 1, 0, 'admin', '2018-01-25 11:55:10', '2018-11-15 09:15:20');
INSERT INTO `tb_article` VALUES (50, 'winForm窗体关闭按钮实现托盘后台运行（类似QQ托盘区运行）', 64, 'H,T', 'http://img.ilunhui.cn/ed0353c9b46247999581ca21139baab5.png', '今天遇到了一个需求，如果用户不小心点击了“关闭”按钮，但是他的本意不是想要真的关闭这个窗体。 对这个需求完全可以在单击“关闭”按钮的时候弹出一个对话框，来让用户确定是否真的要退出。这是一个很好的解决方法，并且实现也是很容易的。但是人家不想这样，想要拥有类似QQ在托盘区后台运行的那种效果，没办法，只能想办法来实现了。', 'winForm', '1', 1, 0, 'admin', '2018-01-25 11:55:10', '2018-11-15 08:42:49');
INSERT INTO `tb_article` VALUES (77, '测试s', 63, 'H,T,G', 'http://img.ilunhui.cn/20181116143902852318.jpg', 'kjljkl', 'kjlkjl', '1', 1, 0, 'admin', '2018-07-10 09:35:24', '2019-01-10 14:56:41');
INSERT INTO `tb_article` VALUES (81, '为什么阿里巴巴禁止工程师直接使用日志系统(Log4j、Logback)中的 API', 64, '', '', '', '', '1', 1, 0, 'admin', '2018-11-26 17:24:06', '2018-11-26 17:24:06');
INSERT INTO `tb_article` VALUES (82, '发自肺腑深入肌肤 —— 一位武汉老程序员的自白', 64, '', '', '', '', '1', 1, 0, 'admin', '2018-11-26 17:24:07', '2018-11-26 17:24:07');
INSERT INTO `tb_article` VALUES (83, 'MySQL误删数据救命指南：必收藏', 64, '', '', '', '', '1', 1, 0, 'admin', '2018-11-26 17:24:08', '2018-11-26 17:24:08');
INSERT INTO `tb_article` VALUES (84, '月薪18k 和 月薪38K的程序员差距在哪里？ssssss', 64, '', '', '', '', '1', 1, 0, 'admin', '2018-11-26 17:24:08', '2019-01-10 22:20:22');
INSERT INTO `tb_article` VALUES (85, '【IT好望角】21个令程序员泪流满面的瞬间，我笑了！', 64, '', '', '', '', '1', 1, 0, 'admin', '2018-11-26 17:24:08', '2018-11-26 17:24:08');
INSERT INTO `tb_article` VALUES (86, 'Spring boot beetl idea热更新解决方案', 64, '', '', '', '', '1', 1, 0, 'admin', '2018-11-26 17:24:09', '2018-11-26 17:24:09');
INSERT INTO `tb_article` VALUES (87, 'Java程序员必备：微服务+开源框架+架构基础+高性能架构+设计模式', 64, '', '', '', '', '1', 1, 0, 'admin', '2018-11-26 17:24:09', '2018-11-26 17:24:09');
INSERT INTO `tb_article` VALUES (88, '2018年终巨献：阿里、腾讯最新Java面试题，你准备好进BAT了吗？', 64, '', '', '', '', '1', 1, 0, 'admin', '2018-11-26 17:24:09', '2018-11-26 17:24:09');
INSERT INTO `tb_article` VALUES (89, '32岁程序员面试，因年龄太大被拒！网友：是领导能力差怕被超越', 64, '', '', '', '', '1', 1, 0, 'admin', '2018-11-26 17:24:10', '2018-11-26 17:24:10');
INSERT INTO `tb_article` VALUES (90, 'Redis热点Key发现及常见解决方案', 64, 'H,T,G', '', '', 'Redis', '1', 1, 0, 'admin', '2018-11-26 17:24:10', '2018-12-06 08:28:24');
INSERT INTO `tb_article` VALUES (93, '用 SpringBoot 实现一个命令行应用', 64, '', '', '', '', '1', 1, 0, 'admin', '2018-11-26 17:24:12', '2019-04-13 21:32:15');
INSERT INTO `tb_article` VALUES (94, 'spring-boot-2.0.3不一样系列之源码篇 - 阶段总结', 64, 'H,T,G', '', '', '', '1', 1, 0, 'admin', '2018-11-26 17:24:13', '2019-03-21 11:29:45');
INSERT INTO `tb_article` VALUES (95, 'SpringBoot 开发一个可以在 Web 容器中运行的 War 程序', 64, '', '', '', '', '1', 1, 0, 'admin', '2018-11-26 17:24:13', '2018-11-26 17:24:13');
INSERT INTO `tb_article` VALUES (96, 'SpringBoot整合RabbitMQ之典型应用场景实战二', 64, '', '', '', '', '1', 1, 0, 'admin', '2018-11-26 17:24:14', '2018-11-26 17:24:14');
INSERT INTO `tb_article` VALUES (97, '厉害了，Spring Cloud for Alibaba 来了！', 64, '', '', '', '', '1', 1, 0, 'admin', '2018-11-26 17:24:14', '2019-03-21 11:29:25');
INSERT INTO `tb_article` VALUES (98, '玩转vue前进刷新，后退不刷新and按需刷新', 64, 'G', '', '', '', '1', 1, 0, 'admin', '2018-11-26 17:24:15', '2019-03-27 11:45:22');
INSERT INTO `tb_article` VALUES (99, '现身说法：37岁老码农找工作', 64, 'H,T,G', '/images/2019/03/27/20190327115429912951.png', '010', '01', '1', 1, 0, 'admin', '2018-11-26 17:24:15', '2019-03-27 11:54:32');
INSERT INTO `tb_article` VALUES (100, '阿里员工都是这样排查Java问题的，附工具单', 63, 'T,G', 'http://img.ilunhui.cn/20181204140047224670.png', '', '手机验证', '1', 1, 0, 'admin', '2018-11-26 17:24:16', '2019-04-13 11:00:37');
INSERT INTO `tb_article` VALUES (104, '啊啊啊啊啊', 0, 'H', '', '', '222', '1', 1, 0, 'test', '2019-05-27 14:39:38', NULL);

-- ----------------------------
-- Table structure for tb_article_cate
-- ----------------------------
DROP TABLE IF EXISTS `tb_article_cate`;
CREATE TABLE `tb_article_cate`  (
  `cate_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cate_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '栏目名称',
  `sort` int(11) NOT NULL COMMENT '排序',
  `status` int(1) NOT NULL COMMENT '是否显示 1显示 0隐藏',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '编辑时间',
  PRIMARY KEY (`cate_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 69 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文章分类表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_article_cate
-- ----------------------------
INSERT INTO `tb_article_cate` VALUES (63, 'APICloud', 10, 0, '2018-11-13 20:35:53', '2019-03-21 11:36:59');
INSERT INTO `tb_article_cate` VALUES (64, 'Java', 10, 0, '2018-11-13 20:36:02', '2019-03-21 11:36:55');
INSERT INTO `tb_article_cate` VALUES (65, 'ThinkPHP', 10, 0, '2018-11-13 20:36:16', '2019-04-01 17:13:08');
INSERT INTO `tb_article_cate` VALUES (66, 'Vue', 10, 0, '2018-11-21 17:09:11', '2019-03-21 11:36:48');
INSERT INTO `tb_article_cate` VALUES (67, 'IOS', 10, 0, '2019-02-26 20:13:52', '2019-03-27 11:45:00');
INSERT INTO `tb_article_cate` VALUES (68, 'Android', 10, 0, '2019-03-10 16:33:39', '2019-05-29 10:03:01');

-- ----------------------------
-- Table structure for tb_banner
-- ----------------------------
DROP TABLE IF EXISTS `tb_banner`;
CREATE TABLE `tb_banner`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '留言Id',
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '留言评论作者',
  `cate_id` int(11) NULL DEFAULT NULL,
  `images` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(1) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `sort` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 132 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文章评论表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_banner
-- ----------------------------
INSERT INTO `tb_banner` VALUES (1, '感谢特朗普——中美贸易战分析', 1, 'http://upload-images.jianshu.io/upload_images/9151481-633f53d509c9c133.jpg', 'https://www.jianshu.com/p/07fb7bb2b39e', 0, '2018-03-25 19:05:58', '2019-01-25 23:15:50', 10);
INSERT INTO `tb_banner` VALUES (2, '《前任3》：再爱也不回头，只是因为“作”么？', 2, 'http://img.ilunhui.cn/f02804f9-7cdb-41f0-9e17-8607678a939a.png', 'https://www.jianshu.com/p/78cb574a2750', 0, '2018-03-25 19:06:01', '2019-01-25 23:15:46', 10);
INSERT INTO `tb_banner` VALUES (124, '简评 Smartisan TNT 工作站', 1, 'http://img.ilunhui.cn/3743e1a6-21c6-45e2-9cab-2f9dbe6f7371.png', 'https://www.jianshu.com/p/947ce6d21c52', 0, '2018-06-14 11:28:42', '2019-03-21 11:37:16', 10);
INSERT INTO `tb_banner` VALUES (129, '旅游航运枢纽、全国健康旅游示范', 3, '/images/2019/04/26/20190426170330642624.jpg', 'https://weibo.com/ajaxlogin.php?framelogin=1&callback=parent.sinaSSOController.feedBackUrlCallBack', 0, '2018-07-26 17:54:53', '2019-04-26 17:03:32', 10);
INSERT INTO `tb_banner` VALUES (130, 'asdas', 3, '/images/2019/04/22/20190422111409769104.jpg', '12', 0, '2019-04-22 11:14:13', NULL, 10);
INSERT INTO `tb_banner` VALUES (131, '123', 2, '/images/2019/04/25/20190425140855188825.jpg', 'www.baidu', 1, '2019-04-25 14:09:12', '2019-04-25 14:09:21', 10);

SET FOREIGN_KEY_CHECKS = 1;
