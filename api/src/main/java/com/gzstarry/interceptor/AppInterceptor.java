package com.gzstarry.interceptor;

import com.gzstarry.annotation.AppLogin;
import com.gzstarry.common.exception.WebException;
import com.gzstarry.common.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * app拦截器
 *
 * @author MakerLin makerlin96@gmail.com
 */
@Component
public class AppInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private JwtUtils jwtUtils;

    /**
     * 登录用户ID
     */
    public static final String USER_KEY = "userId";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        AppLogin annotation;
        if(handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod) handler).getMethodAnnotation(AppLogin.class);
        }else{
            return true;
        }
        //代表此接口没有添加AppLOGIN注解，所有用户都可访问
        if(annotation == null){
            return true;
        }
        //获取用户凭证
        String token = request.getHeader(jwtUtils.getHeader());
        if(StringUtils.isBlank(token)){
            token = request.getParameter(jwtUtils.getHeader());
        }
        //凭证为空 所有请求必须在HTTP头部添加TOKEN
        if(StringUtils.isBlank(token)){
            throw new WebException(jwtUtils.getHeader() + "不能为空", HttpStatus.UNAUTHORIZED.value());
        }
        Claims claims = jwtUtils.getClaimByToken(token);
        if(claims == null || jwtUtils.isTokenExpired(token)){
            throw new WebException(jwtUtils.getHeader() + "失效，请重新登录", HttpStatus.UNAUTHORIZED.value());
        }
        //设置userId，所有接口通过AppLoginUser注解 获取用户信息
        request.setAttribute(USER_KEY, claims.getSubject());

        return true;
    }
}
