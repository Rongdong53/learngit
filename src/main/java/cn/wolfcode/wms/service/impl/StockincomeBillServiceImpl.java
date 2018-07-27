package cn.wolfcode.wms.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wolfcode.wms.domain.ProductStock;
import cn.wolfcode.wms.domain.StockincomeBill;
import cn.wolfcode.wms.domain.StockincomeBillItem;
import cn.wolfcode.wms.mapper.ProductStockMapper;
import cn.wolfcode.wms.mapper.StockincomeBillItemMapper;
import cn.wolfcode.wms.mapper.StockincomeBillMapper;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IStockincomeBillService;
import cn.wolfcode.wms.util.UserContext;

/**
 * @author DRD
 * @date 2018年7月13日 下午7:27:22
 * @website www.wolfcode.cn
 * @description
 */
@Service
public class StockincomeBillServiceImpl implements IStockincomeBillService {
	@Autowired
	private StockincomeBillMapper stockincomeBillMapper;
	@Autowired
	private StockincomeBillItemMapper stockincomeBillitemMapper;
	@Autowired
	private ProductStockMapper productStockMapper;

	@Override
	public void deleteByPrimaryKey(Long id) {
		// 删除明细表
		stockincomeBillitemMapper.deleteByBillId(id);
		stockincomeBillMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void insert(StockincomeBill record) {
		// 设置录入时间,录入人
		record.setInputTime(new Date());
		record.setInputUser(UserContext.getCurrentEmp());

		List<StockincomeBillItem> items = record.getItems();
		// 根据订单明细计算订单的总金额和总数量
		BigDecimal totalAmount = BigDecimal.ZERO;
		BigDecimal totalNumber = BigDecimal.ZERO;
		for (StockincomeBillItem item : items) {
			totalNumber = totalNumber.add(item.getNumber());
			// 明细小计，四舍五入
			BigDecimal amount = item.getNumber().multiply(item.getCostPrice()).setScale(2, BigDecimal.ROUND_HALF_UP);
			totalAmount = totalAmount.add(amount);
		}
		record.setTotalAmount(totalAmount);
		record.setTotalNumber(totalNumber);
		stockincomeBillMapper.insert(record);

		// 保存订单明细
		for (StockincomeBillItem item : items) {
			BigDecimal amount = item.getCostPrice().multiply(item.getNumber()).setScale(2, BigDecimal.ROUND_HALF_UP);
			item.setAmount(amount);
			item.setBillId(record.getId());
			stockincomeBillitemMapper.insert(item);
		}
	}

	@Override
	public StockincomeBill selectByPrimaryKey(Long id) {
		return stockincomeBillMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<StockincomeBill> selectAll() {
		return stockincomeBillMapper.selectAll();
	}

	@Override
	public void updateByPrimaryKey(StockincomeBill record) {
		List<StockincomeBillItem> items = record.getItems();
		// 根据订单明细计算订单的总金额和总数量
		BigDecimal totalAmount = BigDecimal.ZERO;
		BigDecimal totalNumber = BigDecimal.ZERO;
		for (StockincomeBillItem item : items) {
			totalNumber = totalNumber.add(item.getNumber());
			// 明细小计，四舍五入
			BigDecimal amount = item.getNumber().multiply(item.getCostPrice()).setScale(2, BigDecimal.ROUND_HALF_UP);
			totalAmount = totalAmount.add(amount);
		}
		record.setTotalAmount(totalAmount);
		record.setTotalNumber(totalNumber);
		stockincomeBillMapper.updateByPrimaryKey(record);

		// 删除旧明细
		stockincomeBillitemMapper.deleteByBillId(record.getId());
		// 保存新明细
		for (StockincomeBillItem item : items) {
			BigDecimal amount = item.getCostPrice().multiply(item.getNumber()).setScale(2, BigDecimal.ROUND_HALF_UP);
			item.setAmount(amount);
			item.setBillId(record.getId());
			stockincomeBillitemMapper.insert(item);
		}
	}

	@Override
	public PageResult query(QueryObject qo) {
		int rows = stockincomeBillMapper.queryForCount(qo);
		if (rows == 0) {
			return new PageResult(qo.getPageSize());
		}
		List<StockincomeBill> data = stockincomeBillMapper.queryForList(qo);
		return new PageResult(qo.getCurrentPage(), qo.getPageSize(), rows, data);
	}

	@Override
	public void auditorByPrimaryKey(Long id) {
		// 修改状态值和审核时间和审核人
		StockincomeBill stockincomeBill = stockincomeBillMapper.selectByPrimaryKey(id);
		// 判断当前订单是否审核
		if (stockincomeBill.getStatus() == StockincomeBill.STATUS_NOMAL) {
			stockincomeBill.setStatus(StockincomeBill.STATUS_AUDIT);
			stockincomeBill.setAuditTime(new Date());
			stockincomeBill.setAuditor(UserContext.getCurrentEmp());
			stockincomeBillMapper.updateAuditorByPrimaryKey(stockincomeBill);
		}
		List<StockincomeBillItem> items = stockincomeBill.getItems();
		for (StockincomeBillItem item : items) {
			ProductStock productStock = productStockMapper.selectByProductIdAndDepotId(item.getProduct().getId(),
					stockincomeBill.getDepot().getId());
			// 该商品不存在该仓库中
			if (productStock == null) {
				productStock = new ProductStock();
				productStock.setPrice(item.getCostPrice());
				productStock.setStoreNumber(item.getNumber());
				productStock.setAmount(item.getAmount());
				productStock.setProduct(item.getProduct());
				productStock.setDepot(stockincomeBill.getDepot());
				// 保存
				productStockMapper.insert(productStock);
			} else {
				// 库存总数量
				BigDecimal storeNumber = productStock.getStoreNumber().add(item.getNumber());
				// 库存总金额
				BigDecimal amount = productStock.getAmount().add(item.getAmount());
				// 求库存价格
				BigDecimal price = amount.divide(storeNumber, 2, BigDecimal.ROUND_HALF_UP);
				productStock.setAmount(amount);
				productStock.setStoreNumber(storeNumber);
				productStock.setPrice(price);
				// 修改
				productStockMapper.updateByPrimaryKey(productStock);
			}
		}

	}

}
