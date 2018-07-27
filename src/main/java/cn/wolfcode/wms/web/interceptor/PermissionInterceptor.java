package cn.wolfcode.wms.web.interceptor;

import java.lang.reflect.Method;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.wolfcode.wms.domain.Employee;
import cn.wolfcode.wms.util.PermissionException;
import cn.wolfcode.wms.util.PermissionUtil;
import cn.wolfcode.wms.util.RequiredPermission;
import cn.wolfcode.wms.util.UserContext;

/**
 * @author DRD
 * @date 2018年7月11日 上午1:13:18
 * @website www.wolfcode.cn
 * @description
 */
public class PermissionInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Employee employee = UserContext.getCurrentEmp();
		// 判断当前用户是否是超级管理员，是放行
		if (employee.isAdmin()) {
			return true;
		}
		//判断当前handler是不是HandlerMethod，不是放行
		if(!(handler instanceof HandlerMethod)){
			return true;
		}
		// 判断当前方法是否贴有自定义注解，没贴放行
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();
		if(!method.isAnnotationPresent(RequiredPermission.class)){
			return true;
		}
		// 判断当前用户是否有权限访问，有放行
		Set<String> expressions =  UserContext.getEmpExpressions();
		String expression = PermissionUtil.getExpression(method);
		if(expressions.contains(expression)){
			return true;
		}
		throw new PermissionException();
	}
}
