package cn.wolfcode.wms.service;

import java.util.List;

import cn.wolfcode.wms.domain.Department;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;

/**
 * @author DRD
 * @date 2018年7月9日 下午1:21:08
 * @website www.wolfcode.cn
 * @description
 */
public interface IDepartmentService {
	/**保存部门对象
	 * @param department 部门对象
	 */
	void save(Department department);
	
	/**根据部门id删除部门对象
	 * @param id 要删除部门对象的id
	 */
	void delete(Long id);
	
	/**更新部门对象
	 * @param department 更新的部门对象
	 */
	void update(Department department);
	
	/**根据部门id查询部门对象
	 * @param id 要查询的部门id
	 * @return 返回部门对象，如果没有查询到，返回null
	 */
	Department get(Long id);
	
	/**查询所有的部门对象
	 * @return 部门对象集合，如果没有查询到，返回空集合
	 */
	List<Department> listAll();
	
	/**封装分页数据
	 * @param qo
	 * @return
	 */
	PageResult query(QueryObject qo);
}
