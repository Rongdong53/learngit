package cn.wolfcode.wms.query;

import lombok.Getter;
import lombok.Setter;

/**
 * @author DRD
 * @date 2018年6月25日 上午12:03:06
 * @website www.wolfcode.cn
 * @description
 */
@Setter
@Getter
public class QueryObject {
	private int currentPage = 1;
	private int pageSize = 5;
	
	public int getStart(){
		return (currentPage - 1) * pageSize;
	}
}
