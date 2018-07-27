package cn.wolfcode.wms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.wolfcode.wms.domain.Role;
import cn.wolfcode.wms.query.QueryObject;

/**
 * @author DRD
 * @date 2018年7月9日 下午12:46:05
 * @website www.wolfcode.cn
 * @description
 */
public interface RoleMapper {
	
	/**保存角色对象
	 * @param role 角色对象
	 */
	void save(Role role);
	
	/**根据角色id删除角色对象
	 * @param id 要删除角色对象的id
	 */
	void delete(Long id);
	
	/**更新角色对象
	 * @param role 更新的角色对象
	 */
	void update(Role role);
	
	/**根据角色id查询角色对象
	 * @param id 要查询的角色id
	 * @return 返回角色对象，如果没有查询到，返回null
	 */
	Role get(Long id);
	
	/**查询所有的角色对象
	 * @return 角色对象集合，如果没有查询到，返回空集合
	 */
	List<Role> listAll();
	
	/**查询总计录数
	 * @param qo 
	 * @return
	 */
	int queryForCount(QueryObject qo);
	/**查询每页的数据
	 * @param qo
	 * @return
	 */
	List<Role> queryForList(QueryObject qo);

	/**维护角色和权限的关系,往中间表保存数据
	 * @param roleId 角色id
	 * @param permissionId 权限Id
	 */
	void insertPermissionRelation(@Param("roleId")Long roleId, @Param("permissionId")Long permissionId);

	/**维护角色和权限的关系，删除中间表数据
	 * @param roleId 角色Id
	 */
	void deletePermissionRelation(Long roleId);

	/**维护角色和菜单的关系，往中间表保存数据
	 * @param roleId 角色ID
	 * @param menuId 菜单ID
	 */
	void insertSystemMenuRelation(@Param("roleId")Long roleId, @Param("menuId")Long menuId);

	/**维护角色和菜单的关系，删除中间表数据
	 * @param roleId 角色ID
	 */
	void deleteSystemMenuRelation(Long roleId);
}
