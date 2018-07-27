package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.Employee;
import cn.wolfcode.wms.mapper.EmployeeMapper;
import cn.wolfcode.wms.mapper.PermissionMapper;
import cn.wolfcode.wms.query.EmployeeQueryObject;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.service.IEmployeeService;
import cn.wolfcode.wms.util.LogicException;
import cn.wolfcode.wms.util.MD5;
import cn.wolfcode.wms.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



/**
 * @author DRD
 * @date 2018年7月9日 下午1:22:11
 * @website www.wolfcode.cn
 * @description
 */
@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private EmployeeMapper employeeMapper;
	@Autowired
	private PermissionMapper permissionMapper;

	@Override
	public void save(Employee employee, Long[] roleIds) {
		String password = MD5.encode(employee.getPassword());
		employee.setPassword(password);
		employeeMapper.save(employee);
		if(roleIds != null){
			for (Long roleId : roleIds) {
				employeeMapper.insertRoleRelation(employee.getId(),roleId);
			}
		}
	}

	@Override
	public void delete(Long id) {
		employeeMapper.deleteRoleRelation(id);
		employeeMapper.delete(id);
	}

	@Override
	public void update(Employee employee, Long[] roleIds) {
		employeeMapper.update(employee);
		employeeMapper.deleteRoleRelation(employee.getId());
		if(roleIds != null){
			for (Long roleId : roleIds) {
				employeeMapper.insertRoleRelation(employee.getId(),roleId);
			}
		}
		
	}

	@Override
	public Employee get(Long id) {
		return employeeMapper.get(id);
	}

	@Override
	public List<Employee> listAll() {
		return employeeMapper.listAll();
	}

	@Override
	public PageResult query(EmployeeQueryObject qo) {
		int rows = employeeMapper.queryForCount(qo);
		List<Employee> data = employeeMapper.queryForList(qo);
		if (rows == 0) {
			return new PageResult(qo.getPageSize());
		}
		return new PageResult(qo.getCurrentPage(), qo.getPageSize(), rows, data);
	}

	@Override
	public void queryUserNameAndPassword(String userName, String password) {
		Employee employee = employeeMapper.queryUserNameAndPassword(userName,MD5.encode(password));
		//返回错误信息
		if(employee == null){
			//抛出异常，在Controller中处理
			throw new LogicException("亲，账号或者密码错误");
		}
		//将当前用户放入session中
		UserContext.setCurrentEmp(employee);
		//将当前用户的权限表达式放入session中
		UserContext.setEmpExpressions(permissionMapper.queryExpressionByEmployeeId(employee.getId()));
	}

	@Override
	public void batchdelete(Long[] ids) {
		employeeMapper.batchdelete(ids);
	}
	

}
