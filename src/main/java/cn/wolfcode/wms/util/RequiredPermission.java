package cn.wolfcode.wms.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author DRD
 * @date 2018年7月10日 下午7:03:18
 * @website www.wolfcode.cn
 * @description
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiredPermission {
	String value();
}
