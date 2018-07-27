package cn.wolfcode.wms.util;

/**
 * @author DRD
 * @date 2018年7月12日 下午1:17:43
 * @website www.wolfcode.cn
 * @description
 */
public class PermissionException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public PermissionException() {
		super();
	}

	public PermissionException(String message, Throwable cause) {
		super(message, cause);
	}

	public PermissionException(String message) {
		super(message);
	}
}
