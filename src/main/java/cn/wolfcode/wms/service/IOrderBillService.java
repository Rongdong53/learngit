package cn.wolfcode.wms.service;

import java.util.List;

import cn.wolfcode.wms.domain.OrderBill;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;

/**
 * @author DRD
 * @date 2018年7月13日 下午7:26:00
 * @website www.wolfcode.cn
 * @description
 */
public interface IOrderBillService {
	void deleteByPrimaryKey(Long id);

    void insert(OrderBill record);

    OrderBill selectByPrimaryKey(Long id);

    List<OrderBill> selectAll();

    void updateByPrimaryKey(OrderBill record);
    
    PageResult query(QueryObject qo);

	void auditorByPrimaryKey(Long id);
}
