package cn.wolfcode.wms.util;

/**
 * @author DRD
 * @date 2018年7月10日 下午10:48:31
 * @website www.wolfcode.cn
 * @description
 */
public class LogicException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public LogicException() {
		super();
	}

	public LogicException(String message, Throwable cause) {
		super(message, cause);
	}

	public LogicException(String message) {
		super(message);
	}
	
}
