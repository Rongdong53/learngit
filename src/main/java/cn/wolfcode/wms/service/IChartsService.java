package cn.wolfcode.wms.service;

import cn.wolfcode.wms.query.QueryObject;

import java.util.List;
import java.util.Map;

/**
 * @author DRD
 * @date 2018年7月18日 下午7:03:49
 * @website www.wolfcode.cn
 * @description
 */
public interface IChartsService {
	List<Map<String,Object>> chartOrder(QueryObject qo);
	List<Map<String,Object>> chartSale(QueryObject qo);
}
