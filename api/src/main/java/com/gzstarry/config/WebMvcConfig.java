package com.gzstarry.config;

import com.gzstarry.interceptor.AppHandlerMethodArgumentResolver;
import com.gzstarry.interceptor.AppInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * WebMvc配置
 *
 * @author MakerLin makerlin96@gmail.com
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private AppInterceptor appInterceptor;
    @Autowired
    private AppHandlerMethodArgumentResolver appLoginUserHandlerMethodArgumentResolver;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 所有/api/**的请求被拦截
        registry.addInterceptor(appInterceptor).addPathPatterns("/api/**");
    }


    /**
     * 参数解析器
     * @param argumentResolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(appLoginUserHandlerMethodArgumentResolver);
    }
}