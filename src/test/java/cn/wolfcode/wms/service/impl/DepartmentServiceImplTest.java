package cn.wolfcode.wms.service.impl;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.wolfcode.wms.domain.Department;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IDepartmentService;

/**
 * @author DRD
 * @date 2018年7月9日 下午1:30:50
 * @website www.wolfcode.cn
 * @description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class DepartmentServiceImplTest {

	@Autowired
	private IDepartmentService departmentService;
	@Test
	public void testSave() {
		Department department = new Department();
		department.setName("xxx");
		department.setSn("ssss");
		departmentService.save(department);
	}

	@Test
	public void testDelete() {
		departmentService.delete(8L);
	}

	@Test
	public void testUpdate() {
		Department department = new Department();
		department.setId(8L);
		department.setName("yyy");
		department.setSn("aaa");
		departmentService.update(department );
	}

	@Test
	public void testGet() {
		Department department = departmentService.get(1L);
		System.out.println(department);
	}

	@Test
	public void testListAll() {
		List<Department> listAll = departmentService.listAll();
		System.out.println(listAll);
	}
	
	@Test
	public void testQuery() throws Exception {
		QueryObject qo = new QueryObject();
		PageResult result = departmentService.query(qo);
		System.out.println(result);
	}
}
