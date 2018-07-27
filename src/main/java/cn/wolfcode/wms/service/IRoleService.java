package cn.wolfcode.wms.service;

import java.util.List;

import cn.wolfcode.wms.domain.Role;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;

/**
 * @author DRD
 * @date 2018年7月9日 下午1:21:08
 * @website www.wolfcode.cn
 * @description
 */
public interface IRoleService {
	/**
	 * 保存角色对象
	 * 
	 * @param role
	 *            角色对象
	 */
	void save(Role role, Long[] permissionIds, Long[] menuIds);

	/**
	 * 根据角色id删除角色对象
	 * 
	 * @param id
	 *            要删除角色对象的id
	 */
	void delete(Long id);

	/**
	 * 更新角色对象
	 * 
	 * @param role
	 *            更新的角色对象
	 * @param permissionIds
	 * @param menuIds
	 */
	void update(Role role, Long[] permissionIds, Long[] menuIds);

	/**
	 * 根据角色id查询角色对象
	 * 
	 * @param id
	 *            要查询的角色id
	 * @return 返回角色对象，如果没有查询到，返回null
	 */
	Role get(Long id);

	/**
	 * 查询所有的角色对象
	 * 
	 * @return 角色对象集合，如果没有查询到，返回空集合
	 */
	List<Role> listAll();

	/**
	 * 封装分页数据
	 * 
	 * @param qo
	 * @return
	 */
	PageResult query(QueryObject qo);
}
