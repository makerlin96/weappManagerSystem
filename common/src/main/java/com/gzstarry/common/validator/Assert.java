package com.gzstarry.common.validator;

import com.gzstarry.common.exception.WebException;
import org.apache.commons.lang.StringUtils;

/**
 * 数据校验
 *
 * @author MakerLin makerlin96@gmail.com
 */
public abstract class Assert {

    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new WebException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new WebException(message);
        }
    }
}
