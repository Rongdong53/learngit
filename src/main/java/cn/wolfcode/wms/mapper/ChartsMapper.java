package cn.wolfcode.wms.mapper;

import cn.wolfcode.wms.query.QueryObject;

import java.util.List;
import java.util.Map;

/**
 * @author DRD
 * @date 2018年7月18日 下午7:05:34
 * @website www.wolfcode.cn
 * @description
 */
public interface ChartsMapper {
	
/**查询订货报表
 * @param qo 查询条件
 * @return
 */
  List<Map<String,Object>> chartOrder(QueryObject qo);
/**查询销售报表
 * @param qo 查询条件
 * @return
 */
  List<Map<String,Object>> chartSale(QueryObject qo);
}
