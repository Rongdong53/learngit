package cn.wolfcode.wms.mapper;

import cn.wolfcode.wms.domain.StockincomeBillItem;

public interface StockincomeBillItemMapper {

	 void insert(StockincomeBillItem record);

	 void deleteByBillId(Long billId);

}