package cn.wolfcode.wms.util;

import java.lang.reflect.Method;

/**
 * @author DRD
 * @date 2018年7月10日 下午7:10:21
 * @website www.wolfcode.cn
 * @description
 */
public class PermissionUtil {
	private PermissionUtil(){}
	
	public static String getExpression(Method method){
		//类的全限定名
		String qulifiedName = method.getDeclaringClass().getName();
		//方法名
		String methodName = method.getName();
		return qulifiedName + ":" + methodName;
	}
}
