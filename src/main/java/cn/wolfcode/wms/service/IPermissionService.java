package cn.wolfcode.wms.service;

import java.util.List;

import cn.wolfcode.wms.domain.Permission;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;

/**
 * @author DRD
 * @date 2018年7月9日 下午1:21:08
 * @website www.wolfcode.cn
 * @description
 */
public interface IPermissionService {
	
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
	
	/**封装分页数据
	 * @param qo
	 * @return
	 */
	PageResult query(QueryObject qo);

	/**加载权限
	 * 
	 */
	void loadPermission();
}
