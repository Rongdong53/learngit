package cn.wolfcode.wms.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.wolfcode.wms.util.UserContext;

/**
 * @author DRD
 * @date 2018年7月11日 上午12:46:37
 * @website www.wolfcode.cn
 * @description
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Object object = UserContext.getCurrentEmp();
		if(object != null){
			return true;
		}
		response.sendRedirect("/login.jsp");
		return false;
	}
}
