package cn.wolfcode.wms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wolfcode.wms.domain.Product;
import cn.wolfcode.wms.mapper.ProductMapper;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IProductService;

/**
 * @author DRD
 * @date 2018年7月13日 下午7:27:22
 * @website www.wolfcode.cn
 * @description
 */
@Service
public class ProductServiceImpl implements IProductService {
	@Autowired
	private ProductMapper productMapper;
	@Override
	public void deleteByPrimaryKey(Long id) {
		productMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void insert(Product record) {
		productMapper.insert(record);
	}

	@Override
	public Product selectByPrimaryKey(Long id) {
		return productMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Product> selectAll() {
		return productMapper.selectAll();
	}

	@Override
	public void updateByPrimaryKey(Product record) {
		productMapper.updateByPrimaryKey(record);
	}

	@Override
	public PageResult query(QueryObject qo) {
		int rows = productMapper.queryForCount(qo);
		if(rows == 0){
			return new PageResult(qo.getPageSize());
		}
		List<Product> data = productMapper.queryForList(qo);
		return new PageResult(qo.getCurrentPage(), qo.getPageSize(), rows, data);
	}

}
