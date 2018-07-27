package cn.wolfcode.wms.mapper;

import java.util.List;

import cn.wolfcode.wms.domain.OrderBill;
import cn.wolfcode.wms.query.QueryObject;

public interface OrderBillMapper {
    void deleteByPrimaryKey(Long id);

    void insert(OrderBill record);

    OrderBill selectByPrimaryKey(Long id);

    List<OrderBill> selectAll();

    void updateByPrimaryKey(OrderBill record);
    
    int queryForCount(QueryObject qo);
    List<OrderBill> queryForList(QueryObject qo);

	/**修改审核状态值和审核时间和审核人
	 * @param orderBill
	 */
	void updateAuditorByPrimaryKey(OrderBill orderBill);
}