package cn.wolfcode.wms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wolfcode.wms.domain.Employee;
import cn.wolfcode.wms.domain.SystemMenu;
import cn.wolfcode.wms.mapper.SystemMenuMapper;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.ISystemMenuService;
import cn.wolfcode.wms.util.UserContext;

/**
 * @author DRD
 * @date 2018年7月9日 下午1:22:11
 * @website www.wolfcode.cn
 * @description
 */
@Service
public class SystemMenuServiceImpl implements ISystemMenuService {

	@Autowired
	private SystemMenuMapper systemmenuMapper;
	
	@Override
	public void save(SystemMenu systemmenu) {
		systemmenuMapper.save(systemmenu);
	}

	@Override
	public void delete(Long id) {
		systemmenuMapper.delete(id);
	}

	@Override
	public void update(SystemMenu systemmenu) {
		systemmenuMapper.update(systemmenu);
	}

	@Override
	public SystemMenu get(Long id) {
		return systemmenuMapper.get(id);
	}

	@Override
	public List<SystemMenu> listAll() {
		return systemmenuMapper.listAll();
	}

	@Override
	public PageResult query(QueryObject qo) {
		int rows = systemmenuMapper.queryForCount(qo);
		List<SystemMenu> data = systemmenuMapper.queryForList(qo);
		if(rows == 0){
			return new PageResult(qo.getPageSize());
		}
		return new PageResult(qo.getCurrentPage(), qo.getPageSize(), rows, data);
	}

	@Override
	public List<Map<String, Object>> selectMenuByParentSn(String parentSn) {
		//当用户是超级管理员时，查询出全部菜单
		Employee currentEmp = UserContext.getCurrentEmp();
		if(currentEmp.isAdmin()){
			return systemmenuMapper.selectMenuByParentSn(parentSn);
		}
		//当用户不是超级管理员时，根据员工id查询菜单
		return systemmenuMapper.selectMenuByParentSnAndEmpId(parentSn,currentEmp.getId());
	}

}
