package cn.wolfcode.wms.util;

import cn.wolfcode.wms.domain.Employee;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.Set;

/**
 * @author DRD
 * @date 2018年7月12日 下午12:47:29
 * @website www.wolfcode.cn
 * @description
 */
public class UserContext {
	private UserContext() {
	}

	public final static String EMPLOYEE_IN_SESSION = "EMPLOYEE_IN_SESSION";
	public final static String EXPRESSION_IN_SESSION = "EXPRESSION_IN_SESSION";

	// 获取Session
	public static HttpSession getSession() {
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
		return servletRequestAttributes.getRequest().getSession();
	}

	// 将当前用户放入session中
	public static void setCurrentEmp(Employee employee) {
		getSession().setAttribute(EMPLOYEE_IN_SESSION, employee);
	}

	// 从session中获取当前用户
	public static Employee getCurrentEmp() {
		return (Employee) getSession().getAttribute(EMPLOYEE_IN_SESSION);
	}

	// 将当前用户的权限表达式放入session中
	public static void setEmpExpressions(Set<String> expressions) {
		getSession().setAttribute(EXPRESSION_IN_SESSION, expressions);
	}

	// 从session中获取当前用户的权限表达式
	@SuppressWarnings("unchecked")
	public static Set<String> getEmpExpressions() {
		return (Set<String>) getSession().getAttribute(EXPRESSION_IN_SESSION);
	}
}
