package com.gzstarry.annotation;

import java.lang.annotation.*;

/**
 * app登录注解
 *
 * @author MakerLin makerlin96@gmail.com
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AppLogin {
}
