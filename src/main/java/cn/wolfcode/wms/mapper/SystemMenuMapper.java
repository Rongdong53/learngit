package cn.wolfcode.wms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.wolfcode.wms.domain.SystemMenu;
import cn.wolfcode.wms.query.QueryObject;

/**
 * @author DRD
 * @date 2018年7月9日 下午12:46:05
 * @website www.wolfcode.cn
 * @description
 */
public interface SystemMenuMapper {
	
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
	
	/**查询总计录数
	 * @param qo 
	 * @return
	 */
	int queryForCount(QueryObject qo);
	/**查询每页的数据
	 * @param qo
	 * @return
	 */
	List<SystemMenu> queryForList(QueryObject qo);

	/**加载父菜单中的子菜单
	 * @param parentSn 父菜单中的编码
	 * @return 查询到的子菜单
	 */
	List<Map<String, Object>> selectMenuByParentSn(String parentSn);

	/**根据员工id查询菜单
	 * @param parentSn 父菜单中的编码
	 * @param empId 员工id
	 * @return
	 */
	List<Map<String, Object>> selectMenuByParentSnAndEmpId(@Param("parentSn")String parentSn, @Param("empId")Long empId);
}
