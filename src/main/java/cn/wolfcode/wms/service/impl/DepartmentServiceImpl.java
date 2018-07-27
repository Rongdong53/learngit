package cn.wolfcode.wms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wolfcode.wms.domain.Department;
import cn.wolfcode.wms.mapper.DepartmentMapper;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IDepartmentService;

/**
 * @author DRD
 * @date 2018年7月9日 下午1:22:11
 * @website www.wolfcode.cn
 * @description
 */
@Service
public class DepartmentServiceImpl implements IDepartmentService {

	@Autowired
	private DepartmentMapper departmentMapper;
	
	@Override
	public void save(Department department) {
		departmentMapper.save(department);
	}

	@Override
	public void delete(Long id) {
		departmentMapper.delete(id);
	}

	@Override
	public void update(Department department) {
		departmentMapper.update(department);
	}

	@Override
	public Department get(Long id) {
		return departmentMapper.get(id);
	}

	@Override
	public List<Department> listAll() {
		return departmentMapper.listAll();
	}

	@Override
	public PageResult query(QueryObject qo) {
		int rows = departmentMapper.queryForCount(qo);
		List<Department> data = departmentMapper.queryForList(qo);
		if(rows == 0){
			return new PageResult(qo.getPageSize());
		}
		return new PageResult(qo.getCurrentPage(), qo.getPageSize(), rows, data);
	}

}
