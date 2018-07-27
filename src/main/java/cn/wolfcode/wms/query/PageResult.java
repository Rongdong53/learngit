package cn.wolfcode.wms.query;

import java.util.Collections;
import java.util.List;

import lombok.Getter;
import lombok.ToString;

/**
 * @author DRD
 * @date 2018年6月25日 上午12:07:04
 * @website www.wolfcode.cn
 * @description
 */
@Getter
@ToString
public class PageResult {
	private int currentPage;
	private int pageSize;

	private int rows;
	private List<?> data;

	private int prevPage;
	private int nextPage;
	private int totalPage;

	public PageResult(int currentPage, int pageSize, int rows, List<?> data) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.rows = rows;
		this.data = data;

		this.totalPage = rows % pageSize == 0 ? rows / pageSize : rows / pageSize + 1;
		this.prevPage = currentPage - 1 > 1 ? currentPage - 1 : 1;
		this.nextPage = currentPage + 1 < totalPage ? currentPage + 1 : totalPage;
		
	}
	public PageResult(int pageSize){
		this(1,pageSize,0,Collections.emptyList());
	}
}
