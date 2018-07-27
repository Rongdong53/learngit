package cn.wolfcode.wms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wolfcode.wms.domain.Supplier;
import cn.wolfcode.wms.mapper.SupplierMapper;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.ISupplierService;

/**
 * @author DRD
 * @date 2018年7月13日 下午7:27:22
 * @website www.wolfcode.cn
 * @description
 */
@Service
public class SupplierServiceImpl implements ISupplierService {
	@Autowired
	private SupplierMapper supplierMapper;
	@Override
	public void deleteByPrimaryKey(Long id) {
		supplierMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void insert(Supplier record) {
		supplierMapper.insert(record);
	}

	@Override
	public Supplier selectByPrimaryKey(Long id) {
		return supplierMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Supplier> selectAll() {
		return supplierMapper.selectAll();
	}

	@Override
	public void updateByPrimaryKey(Supplier record) {
		supplierMapper.updateByPrimaryKey(record);
	}

	@Override
	public PageResult query(QueryObject qo) {
		int rows = supplierMapper.queryForCount(qo);
		if(rows == 0){
			return new PageResult(qo.getPageSize());
		}
		List<Supplier> data = supplierMapper.queryForList(qo);
		return new PageResult(qo.getCurrentPage(), qo.getPageSize(), rows, data);
	}

}
