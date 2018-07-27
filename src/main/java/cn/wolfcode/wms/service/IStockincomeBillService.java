package cn.wolfcode.wms.service;

import java.util.List;

import cn.wolfcode.wms.domain.StockincomeBill;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;

/**
 * @author DRD
 * @date 2018年7月13日 下午7:26:00
 * @website www.wolfcode.cn
 * @description
 */
public interface IStockincomeBillService {
	void deleteByPrimaryKey(Long id);

    void insert(StockincomeBill record);

    StockincomeBill selectByPrimaryKey(Long id);

    List<StockincomeBill> selectAll();

    void updateByPrimaryKey(StockincomeBill record);
    
    PageResult query(QueryObject qo);

	void auditorByPrimaryKey(Long id);
}
