package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.Employee;
import cn.wolfcode.wms.query.EmployeeQueryObject;
import cn.wolfcode.wms.query.PageResult;

import java.util.List;

/**
 * @author DRD
 * @date 2018年7月9日 下午1:21:08
 * @website www.wolfcode.cn
 * @description
 */
public interface IEmployeeService {
	/**保存员工对象
	 * @param employee 员工对象
	 */
	void save(Employee employee, Long[] roleIds);
	
	/**根据员工id删除员工对象
	 * @param id 要删除员工对象的id
	 */
	void delete(Long id);
	
	/**更新员工对象
	 * @param employee 更新的员工对象
	 * @param roleIds 
	 */
	void update(Employee employee, Long[] roleIds);
	
	/**根据员工id查询员工对象
	 * @param id 要查询的员工id
	 * @return 返回员工对象，如果没有查询到，返回null
	 */
	Employee get(Long id);
	
	/**查询所有的员工对象
	 * @return 员工对象集合，如果没有查询到，返回空集合
	 */
	List<Employee> listAll();
	
	/**封装分页数据
	 * @param qo
	 * @return
	 */
	PageResult query(EmployeeQueryObject qo);

	/**登入验证
	 * @param userName 用户名
	 * @param password 密码
	 */
	void queryUserNameAndPassword(String userName, String password);

	/**批量删除员工
	 * @param ids 员工ID
	 */
	void batchdelete(Long[] ids);
}
