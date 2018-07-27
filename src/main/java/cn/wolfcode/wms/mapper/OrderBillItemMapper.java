package cn.wolfcode.wms.mapper;

import cn.wolfcode.wms.domain.OrderBillItem;

public interface OrderBillItemMapper {

    void insert(OrderBillItem record);

	void deleteByBillId(Long billId);

}