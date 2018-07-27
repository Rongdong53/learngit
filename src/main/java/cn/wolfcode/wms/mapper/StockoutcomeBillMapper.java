package cn.wolfcode.wms.mapper;

import java.util.List;

import cn.wolfcode.wms.domain.StockoutcomeBill;
import cn.wolfcode.wms.query.QueryObject;

public interface StockoutcomeBillMapper {
    void deleteByPrimaryKey(Long id);

    void insert(StockoutcomeBill record);

    StockoutcomeBill selectByPrimaryKey(Long id);

    List<StockoutcomeBill> selectAll();

    void updateByPrimaryKey(StockoutcomeBill record);
    
    int queryForCount(QueryObject qo);
    List<StockoutcomeBill> queryForList(QueryObject qo);

    void updateAuditor(StockoutcomeBill stockoutcomeBill);
}