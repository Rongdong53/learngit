package cn.wolfcode.wms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wolfcode.wms.domain.Brand;
import cn.wolfcode.wms.mapper.BrandMapper;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IBrandService;

/**
 * @author DRD
 * @date 2018年7月13日 下午7:27:22
 * @website www.wolfcode.cn
 * @description
 */
@Service
public class BrandServiceImpl implements IBrandService {
	@Autowired
	private BrandMapper brandMapper;
	@Override
	public void deleteByPrimaryKey(Long id) {
		brandMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void insert(Brand record) {
		brandMapper.insert(record);
	}

	@Override
	public Brand selectByPrimaryKey(Long id) {
		return brandMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Brand> selectAll() {
		return brandMapper.selectAll();
	}

	@Override
	public void updateByPrimaryKey(Brand record) {
		brandMapper.updateByPrimaryKey(record);
	}

	@Override
	public PageResult query(QueryObject qo) {
		int rows = brandMapper.queryForCount(qo);
		if(rows == 0){
			return new PageResult(qo.getPageSize());
		}
		List<Brand> data = brandMapper.queryForList(qo);
		return new PageResult(qo.getCurrentPage(), qo.getPageSize(), rows, data);
	}

}
