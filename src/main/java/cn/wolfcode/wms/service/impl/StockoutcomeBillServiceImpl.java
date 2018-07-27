package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.ProductStock;
import cn.wolfcode.wms.domain.Saleaccount;
import cn.wolfcode.wms.domain.StockoutcomeBill;
import cn.wolfcode.wms.domain.StockoutcomeBillItem;
import cn.wolfcode.wms.mapper.ProductStockMapper;
import cn.wolfcode.wms.mapper.SaleaccountMapper;
import cn.wolfcode.wms.mapper.StockoutcomeBillItemMapper;
import cn.wolfcode.wms.mapper.StockoutcomeBillMapper;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IStockoutcomeBillService;
import cn.wolfcode.wms.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author DRD
 * @date 2018年7月13日 下午7:27:22
 * @website www.wolfcode.cn
 * @description
 */
@Service
public class StockoutcomeBillServiceImpl implements IStockoutcomeBillService {
    @Autowired
    private StockoutcomeBillMapper stockoutcomeBillMapper;
    @Autowired
    private StockoutcomeBillItemMapper stockoutcomeBillItemMapper;
    @Autowired
    private ProductStockMapper productStockMapper;
    @Autowired
    private SaleaccountMapper saleaccountMapper;

    @Override
    public void deleteByPrimaryKey(Long id) {
        stockoutcomeBillMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insert(StockoutcomeBill record) {
        // 设置录入时间和录入人
        record.setInputTime(new Date());
        record.setInputUser(UserContext.getCurrentEmp());
        // 计算totalNumber和totalAmount
        List<StockoutcomeBillItem> items = record.getItems();
        BigDecimal totalNumber = BigDecimal.ZERO;
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (StockoutcomeBillItem item : items) {
            totalNumber = totalNumber.add(item.getNumber()); // 总数
            totalAmount = totalAmount.add(item.getSalePrice().multiply(item.getNumber())).setScale(2,
                    BigDecimal.ROUND_HALF_UP);// 总金额
        }
        record.setTotalAmount(totalAmount);
        record.setTotalNumber(totalNumber);
        // 保存订单
        stockoutcomeBillMapper.insert(record);

        // 保存订单明细
        for (StockoutcomeBillItem item : items) {
            // 设置订单id(billId)和总金额(amount)
            item.setBillId(record.getId());
            item.setAmount(item.getSalePrice().multiply(item.getNumber()).setScale(2, BigDecimal.ROUND_HALF_UP));
            stockoutcomeBillItemMapper.insert(item);
        }
    }

    @Override
    public StockoutcomeBill selectByPrimaryKey(Long id) {
        return stockoutcomeBillMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<StockoutcomeBill> selectAll() {
        return stockoutcomeBillMapper.selectAll();
    }

    @Override
    public void updateByPrimaryKey(StockoutcomeBill record) {
        // 计算totalNumber和totalAmount
        List<StockoutcomeBillItem> items = record.getItems();
        BigDecimal totalNumber = BigDecimal.ZERO;
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (StockoutcomeBillItem item : items) {
            totalNumber = totalNumber.add(item.getNumber()); // 总数
            totalAmount = totalAmount.add(item.getSalePrice().multiply(item.getNumber())).setScale(2,
                    BigDecimal.ROUND_HALF_UP);// 总金额
        }
        record.setTotalAmount(totalAmount);
        record.setTotalNumber(totalNumber);
        // 保存订单
        stockoutcomeBillMapper.updateByPrimaryKey(record);

        // 根据订单ID删除明细表
        stockoutcomeBillItemMapper.deleteByBillId(record.getId());
        // 保存订单明细
        for (StockoutcomeBillItem item : items) {
            // 设置订单id(billId)和总金额(amount)
            item.setBillId(record.getId());
            item.setAmount(item.getSalePrice().multiply(item.getNumber()).setScale(2, BigDecimal.ROUND_HALF_UP));
            stockoutcomeBillItemMapper.insert(item);
        }
    }

    @Override
    public PageResult query(QueryObject qo) {
        int rows = stockoutcomeBillMapper.queryForCount(qo);
        if (rows == 0) {
            return new PageResult(qo.getPageSize());
        }
        List<StockoutcomeBill> data = stockoutcomeBillMapper.queryForList(qo);
        return new PageResult(qo.getCurrentPage(), qo.getPageSize(), rows, data);
    }

    @Override
    public void auditorByPrimaryKey(Long id) {
        // 更新状态值和审核时间和审核人的ID
        StockoutcomeBill stockoutcomeBill = stockoutcomeBillMapper.selectByPrimaryKey(id);
        if (stockoutcomeBill.getStatus() == StockoutcomeBill.STATUS_NOMAL) {
            stockoutcomeBill.setAuditTime(new Date());
            stockoutcomeBill.setStatus(StockoutcomeBill.STATUS_AUDIT);
            stockoutcomeBill.setAuditor(UserContext.getCurrentEmp());
            stockoutcomeBillMapper.updateAuditor(stockoutcomeBill);

            List<StockoutcomeBillItem> items = stockoutcomeBill.getItems();
            for (StockoutcomeBillItem item : items) {
                ProductStock productStock = productStockMapper.selectByProductIdAndDepotId(item.getProduct().getId(),
                        stockoutcomeBill.getDepot().getId());
                // 商品不存在，给用户错误信息提示
                if (productStock == null) {
                    throw new RuntimeException(
                            "商品[" + item.getProduct().getName() + "]在:" + stockoutcomeBill.getDepot().getName() + "中不存在");
                }
                // 数量不足，给用户错误信息提示
                if (productStock.getStoreNumber().compareTo(item.getNumber()) < 0) {
                    throw new RuntimeException("商品[" + item.getProduct().getName() + "]库存量[" + productStock.getStoreNumber()
                            + "]不足[" + item.getNumber() + "]");
                }
                //够，减少库存
                productStock.setStoreNumber(productStock.getStoreNumber().subtract(item.getNumber()));
                productStock.setAmount(productStock.getStoreNumber().multiply(productStock.getPrice()));
                productStockMapper.updateByPrimaryKey(productStock);
            }

            //保存销售账单
            //一条销售出库单的明细对应一条的销售账
            for (StockoutcomeBillItem item : stockoutcomeBill.getItems()) {
                Saleaccount sa = new Saleaccount();
                sa.setVdate(stockoutcomeBill.getVdate());
                sa.setNumber(item.getNumber());
                sa.setProductId(item.getProduct().getId());
                sa.setSalemanId(stockoutcomeBill.getInputUser().getId());
                sa.setClientId(stockoutcomeBill.getClient().getId());
                ProductStock ps = productStockMapper.selectByProductIdAndDepotId(stockoutcomeBill.getDepot().getId(), sa.getProductId());
                sa.setCostPrice(ps.getPrice());
                sa.setCostAmount(sa.getNumber().multiply(sa.getCostPrice()));
                sa.setSalePrice(item.getSalePrice());
                sa.setSaleAmount(sa.getNumber().multiply(sa.getSalePrice()));
                saleaccountMapper.insert(sa);
            }

        }

    }
}
