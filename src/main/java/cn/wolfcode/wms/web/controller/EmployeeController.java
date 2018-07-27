package cn.wolfcode.wms.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.wolfcode.wms.domain.Employee;
import cn.wolfcode.wms.query.EmployeeQueryObject;
import cn.wolfcode.wms.service.IDepartmentService;
import cn.wolfcode.wms.service.IEmployeeService;
import cn.wolfcode.wms.service.IRoleService;
import cn.wolfcode.wms.util.JsonResult;

/**
 * @author DRD
 * @date 2018年7月9日 下午2:06:42
 * @website www.wolfcode.cn
 * @description
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	private IEmployeeService employeeService;
	@Autowired
	private IDepartmentService departmentService;
	@Autowired
	private IRoleService roleService;

	@RequestMapping("/list")
	public String list(Model model, @ModelAttribute("qo") EmployeeQueryObject qo) {
		model.addAttribute("result", employeeService.query(qo));
		model.addAttribute("departments", departmentService.listAll());
		return "employee/list";
	}

	@ResponseBody
	@RequestMapping("/delete")
	public JsonResult delete(Long id) {
		JsonResult result = new JsonResult();
		try {
			if (id != null) {
				employeeService.delete(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.markMsg("删除失败!");
		}
		return result;
	}

	@RequestMapping("/input")
	public String input(Model model, Long id) {
		if (id != null) {
			model.addAttribute("employee", employeeService.get(id));
		}
		model.addAttribute("roles", roleService.listAll());
		model.addAttribute("departments", departmentService.listAll());
		return "employee/input";
	}

	@RequestMapping("/saveOrUpdate")
	public String saveOrUpdate(Employee employee, Long[] roleIds) {
		if (employee.getId() != null) {
			employeeService.update(employee, roleIds);
		} else {
			employeeService.save(employee, roleIds);
		}
		return "redirect:/employee/list";
	}
	
	@ResponseBody
	@RequestMapping("/batchdelete")
	public JsonResult batchdelete(Long[] ids) {
		JsonResult result = new JsonResult();
		try {
			if (ids != null) {
				employeeService.batchdelete(ids);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.markMsg("删除失败!");
		}
		return result;
	}
}
