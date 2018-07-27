package cn.wolfcode.wms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.wolfcode.wms.domain.Employee;
import cn.wolfcode.wms.query.QueryObject;

/**
 * @author DRD
 * @date 2018年7月9日 下午12:46:05
 * @website www.wolfcode.cn
 * @description
 */
public interface EmployeeMapper {
	
	/**保存员工对象
	 * @param employee 员工对象
	 */
	void save(Employee employee);
	
	/**根据员工id删除员工对象
	 * @param id 要删除员工对象的id
	 */
	void delete(Long id);
	
	/**更新员工对象
	 * @param employee 更新的员工对象
	 */
	void update(Employee employee);
	
	/**根据员工id查询员工对象
	 * @param id 要查询的员工id
	 * @return 返回员工对象，如果没有查询到，返回null
	 */
	Employee get(Long id);
	
	/**查询所有的员工对象
	 * @return 员工对象集合，如果没有查询到，返回空集合
	 */
	List<Employee> listAll();
	
	/**查询总计录数
	 * @param qo 
	 * @return
	 */
	int queryForCount(QueryObject qo);
	/**查询每页的数据
	 * @param qo
	 * @return
	 */
	List<Employee> queryForList(QueryObject qo);

	/**维护关系，往中间表保存数据
	 * @param empId  员工Id
	 * @param roleId 角色Id
	 */
	void insertRoleRelation(@Param("empId")Long empId, @Param("roleId")Long roleId);

	/**维护关系，删除中间表数据
	 * @param empId 员工Id
	 */
	void deleteRoleRelation(Long empId);

	/**登入验证
	 * @param username 用户名
	 * @param password 密码
	 */
	Employee queryUserNameAndPassword(@Param("userName")String userName, @Param("password")String password);

	/**批量删除员工
	 * @param ids 员工ID
	 */
	void batchdelete(Long[] ids);
}
