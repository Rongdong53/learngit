package cn.wolfcode.wms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wolfcode.wms.domain.ProductStock;
import cn.wolfcode.wms.mapper.ProductStockMapper;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IProductStockService;

/**
 * @author DRD
 * @date 2018年7月17日 下午11:38:03
 * @website www.wolfcode.cn
 * @description
 */
@Service
public class ProductStockServiceImpl implements IProductStockService {
	
	@Autowired
	private ProductStockMapper productStockMapper;

	@Override
	public PageResult query(QueryObject qo) {
		int rows = productStockMapper.queryForCount(qo);
		List<ProductStock> data = productStockMapper.queryForList(qo);
		if(rows == 0){
			return new PageResult(qo.getPageSize());
		}
		return new PageResult(qo.getCurrentPage(), qo.getPageSize(), rows, data);
	}

}
