package cn.wolfcode.wms.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wolfcode.wms.domain.OrderBill;
import cn.wolfcode.wms.domain.OrderBillItem;
import cn.wolfcode.wms.mapper.OrderBillItemMapper;
import cn.wolfcode.wms.mapper.OrderBillMapper;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IOrderBillService;
import cn.wolfcode.wms.util.UserContext;

/**
 * @author DRD
 * @date 2018年7月13日 下午7:27:22
 * @website www.wolfcode.cn
 * @description
 */
@Service
public class OrderBillServiceImpl implements IOrderBillService {
	@Autowired
	private OrderBillMapper orderBillMapper;
	@Autowired
	private OrderBillItemMapper orderBillitemMapper;

	@Override
	public void deleteByPrimaryKey(Long id) {
		// 删除明细表
		orderBillitemMapper.deleteByBillId(id);
		orderBillMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void insert(OrderBill record) {
		// 设置录入时间,录入人
		record.setInputTime(new Date());
		record.setInputUser(UserContext.getCurrentEmp());

		List<OrderBillItem> items = record.getItems();
		// 根据订单明细计算订单的总金额和总数量
		BigDecimal totalAmount = BigDecimal.ZERO;
		BigDecimal totalNumber = BigDecimal.ZERO;
		for (OrderBillItem item : items) {
			totalNumber = totalNumber.add(item.getNumber());
			// 明细小计，四舍五入
			BigDecimal amount = item.getNumber().multiply(item.getCostPrice()).setScale(2, BigDecimal.ROUND_HALF_UP);
			totalAmount = totalAmount.add(amount);
		}
		record.setTotalAmount(totalAmount);
		record.setTotalNumber(totalNumber);
		orderBillMapper.insert(record);

		// 保存订单明细
		for (OrderBillItem item : items) {
			BigDecimal amount = item.getCostPrice().multiply(item.getNumber()).setScale(2, BigDecimal.ROUND_HALF_UP);
			item.setAmount(amount);
			item.setBillId(record.getId());
			orderBillitemMapper.insert(item);
		}
	}

	@Override
	public OrderBill selectByPrimaryKey(Long id) {
		return orderBillMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<OrderBill> selectAll() {
		return orderBillMapper.selectAll();
	}

	@Override
	public void updateByPrimaryKey(OrderBill record) {
		List<OrderBillItem> items = record.getItems();
		// 根据订单明细计算订单的总金额和总数量
		BigDecimal totalAmount = BigDecimal.ZERO;
		BigDecimal totalNumber = BigDecimal.ZERO;
		for (OrderBillItem item : items) {
			totalNumber = totalNumber.add(item.getNumber());
			// 明细小计，四舍五入
			BigDecimal amount = item.getNumber().multiply(item.getCostPrice()).setScale(2, BigDecimal.ROUND_HALF_UP);
			totalAmount = totalAmount.add(amount);
		}
		record.setTotalAmount(totalAmount);
		record.setTotalNumber(totalNumber);
		orderBillMapper.updateByPrimaryKey(record);

		// 删除旧明细
		orderBillitemMapper.deleteByBillId(record.getId());
		// 保存新明细
		for (OrderBillItem item : items) {
			BigDecimal amount = item.getCostPrice().multiply(item.getNumber()).setScale(2, BigDecimal.ROUND_HALF_UP);
			item.setAmount(amount);
			item.setBillId(record.getId());
			orderBillitemMapper.insert(item);
		}
	}

	@Override
	public PageResult query(QueryObject qo) {
		int rows = orderBillMapper.queryForCount(qo);
		if (rows == 0) {
			return new PageResult(qo.getPageSize());
		}
		List<OrderBill> data = orderBillMapper.queryForList(qo);
		return new PageResult(qo.getCurrentPage(), qo.getPageSize(), rows, data);
	}

	@Override
	public void auditorByPrimaryKey(Long id) {
		// 修改状态值和审核时间和审核人
		OrderBill orderBill = orderBillMapper.selectByPrimaryKey(id);
		//判断当前订单是否审核
		if (orderBill.getStatus() == OrderBill.STATUS_NOMAL) {
			orderBill.setStatus(OrderBill.STATUS_AUDIT);
			orderBill.setAuditTime(new Date());
			orderBill.setAuditor(UserContext.getCurrentEmp());
			orderBillMapper.updateAuditorByPrimaryKey(orderBill);
		}
	}

}
