package cn.wolfcode.wms.mapper;

import cn.wolfcode.wms.domain.StockoutcomeBillItem;

public interface StockoutcomeBillItemMapper {

    void insert(StockoutcomeBillItem record);

    StockoutcomeBillItem selectByPrimaryKey(Long id);

	void deleteByBillId(Long billId);

}