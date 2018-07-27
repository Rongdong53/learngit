package cn.wolfcode.wms.mapper;

import java.util.List;

import cn.wolfcode.wms.domain.StockincomeBill;
import cn.wolfcode.wms.query.QueryObject;

public interface StockincomeBillMapper {
    void deleteByPrimaryKey(Long id);

    void insert(StockincomeBill record);

    StockincomeBill selectByPrimaryKey(Long id);

    List<StockincomeBill> selectAll();

    void updateByPrimaryKey(StockincomeBill record);
    
    int queryForCount(QueryObject qo);
    List<StockincomeBill> queryForList(QueryObject qo);

	/**修改审核状态值和审核时间和审核人
	 * @param orderBill
	 */
	void updateAuditorByPrimaryKey(StockincomeBill orderBill);
}