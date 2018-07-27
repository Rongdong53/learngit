package cn.wolfcode.wms.mapper;

import java.util.List;
import java.util.Set;

import cn.wolfcode.wms.domain.Permission;
import cn.wolfcode.wms.query.QueryObject;

/**
 * @author DRD
 * @date 2018年7月9日 下午12:46:05
 * @website www.wolfcode.cn
 * @description
 */
public interface PermissionMapper {
	
	/**保存权限对象
	 * @param permission 权限对象
	 */
	void save(Permission permission);
	
	/**根据权限id删除权限对象
	 * @param id 要删除权限对象的id
	 */
	void delete(Long id);
	
	/**根据权限id查询权限对象
	 * @param id 要查询的权限id
	 * @return 返回权限对象，如果没有查询到，返回null
	 */
	Permission get(Long id);
	
	/**查询所有的权限对象
	 * @return 权限对象集合，如果没有查询到，返回空集合
	 */
	List<Permission> listAll();
	
	/**查询总计录数
	 * @param qo 
	 * @return
	 */
	int queryForCount(QueryObject qo);
	/**查询每页的数据
	 * @param qo
	 * @return
	 */
	List<Permission> queryForList(QueryObject qo);

	/**查询所有的权限表达式
	 * @return
	 */
	List<String> selectExpression();

	/**查询员工的权限表达式
	 * @param employeeId 员工Id
	 * @return
	 */
	Set<String> queryExpressionByEmployeeId(Long employeeId);
}
