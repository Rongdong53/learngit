package cn.wolfcode.wms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wolfcode.wms.domain.Depot;
import cn.wolfcode.wms.mapper.DepotMapper;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IDepotService;

/**
 * @author DRD
 * @date 2018年7月13日 下午7:27:22
 * @website www.wolfcode.cn
 * @description
 */
@Service
public class DepotServiceImpl implements IDepotService {
	@Autowired
	private DepotMapper depotMapper;
	@Override
	public void deleteByPrimaryKey(Long id) {
		depotMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void insert(Depot record) {
		depotMapper.insert(record);
	}

	@Override
	public Depot selectByPrimaryKey(Long id) {
		return depotMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Depot> selectAll() {
		return depotMapper.selectAll();
	}

	@Override
	public void updateByPrimaryKey(Depot record) {
		depotMapper.updateByPrimaryKey(record);
	}

	@Override
	public PageResult query(QueryObject qo) {
		int rows = depotMapper.queryForCount(qo);
		if(rows == 0){
			return new PageResult(qo.getPageSize());
		}
		List<Depot> data = depotMapper.queryForList(qo);
		return new PageResult(qo.getCurrentPage(), qo.getPageSize(), rows, data);
	}

}
