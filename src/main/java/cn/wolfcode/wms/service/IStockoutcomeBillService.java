package cn.wolfcode.wms.service;

import java.util.List;

import cn.wolfcode.wms.domain.StockoutcomeBill;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;

/**
 * @author DRD
 * @date 2018年7月13日 下午7:26:00
 * @website www.wolfcode.cn
 * @description
 */
public interface IStockoutcomeBillService {
	void deleteByPrimaryKey(Long id);

    void insert(StockoutcomeBill record);

    StockoutcomeBill selectByPrimaryKey(Long id);

    List<StockoutcomeBill> selectAll();

    void updateByPrimaryKey(StockoutcomeBill record);
    
    PageResult query(QueryObject qo);

	void auditorByPrimaryKey(Long id);
}
