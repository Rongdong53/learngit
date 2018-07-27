package cn.wolfcode.wms.service;

import java.util.List;
import java.util.Map;

import cn.wolfcode.wms.domain.SystemMenu;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;

/**
 * @author DRD
 * @date 2018年7月9日 下午1:21:08
 * @website www.wolfcode.cn
 * @description
 */
public interface ISystemMenuService {
	/**保存菜单对象
	 * @param systemmenu 菜单对象
	 */
	void save(SystemMenu systemmenu);
	
	/**根据菜单id删除菜单对象
	 * @param id 要删除菜单对象的id
	 */
	void delete(Long id);
	
	/**更新菜单对象
	 * @param systemmenu 更新的菜单对象
	 */
	void update(SystemMenu systemmenu);
	
	/**根据菜单id查询菜单对象
	 * @param id 要查询的菜单id
	 * @return 返回菜单对象，如果没有查询到，返回null
	 */
	SystemMenu get(Long id);
	
	/**查询所有的菜单对象
	 * @return 菜单对象集合，如果没有查询到，返回空集合
	 */
	List<SystemMenu> listAll();
	
	/**封装分页数据
	 * @param qo
	 * @return
	 */
	PageResult query(QueryObject qo);

	/**加载父菜单中的子菜单
	 * @param parentSn 父菜单中的编码
	 * @return 查询到的子菜单
	 */
	List<Map<String, Object>> selectMenuByParentSn(String parentSn);
}
