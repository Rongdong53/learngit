package cn.wolfcode.wms.util;

import lombok.Getter;

/**
 * @author DRD
 * @date 2018年7月10日 下午1:28:10
 * @website www.wolfcode.cn
 * @description
 */
@Getter
public class JsonResult {
	private String msg;
	private boolean success = true;
	
	//标识错误信息
	public void markMsg(String msg){
		this.success = false;
		this.msg = msg;
	}
}
