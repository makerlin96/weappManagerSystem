package com.gzstarry.shiro;

import com.gzstarry.common.exception.WebException;
import com.gzstarry.entity.sys.SysUserEntity;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * Shiro工具类
 *
 * @author MakerLin makerlin96@gmail.com
 */
public class ShiroUtils {

	public static Session getSession() {
		return SecurityUtils.getSubject().getSession();
	}

	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	public static void setSessionAttribute(Object key, Object value) {
		getSession().setAttribute(key, value);
	}

	public static Object getSessionAttribute(Object key) {
		return getSession().getAttribute(key);
	}

	public static boolean isLogin() {
		return SecurityUtils.getSubject().getPrincipal() != null;
	}

	public static SysUserEntity getUser() {
		return (SysUserEntity)getSubject().getPrincipal();
	}

	public static void logout() {
		SecurityUtils.getSubject().logout();
	}

	public static String getKaptcha(String key) {
		Object kaptcha = getSessionAttribute(key);
		if(kaptcha == null){
			throw new WebException("验证码已失效");
		}
		getSession().removeAttribute(key);
		return kaptcha.toString();
	}

}
