package cn.wolfcode.wms.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.wolfcode.wms.domain.Department;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IDepartmentService;
import cn.wolfcode.wms.util.JsonResult;
import cn.wolfcode.wms.util.RequiredPermission;

/**
 * @author DRD
 * @date 2018年7月9日 下午2:06:42
 * @website www.wolfcode.cn
 * @description
 */
@Controller
@RequestMapping("/department")
public class DepartmentController {
	@Autowired
	private IDepartmentService departmentService;


	@RequiredPermission("部门列表")
	@RequestMapping("/list")
	public String list(Model model, @ModelAttribute("qo")QueryObject qo) {
		model.addAttribute("result", departmentService.query(qo));
		return "department/list";
	}
	
	@RequiredPermission("部门删除")
	@ResponseBody
	@RequestMapping("/delete")
	public JsonResult delete(Long id) {
		JsonResult result = new JsonResult();
		try {
			if (id != null) {
				departmentService.delete(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.markMsg("删除失败!");
		}
		return result;
	}

	@RequiredPermission("部门编辑")
	@RequestMapping("/input")
	public String input(Model model, Long id) {
		if (id != null) {
			model.addAttribute("department", departmentService.get(id));
		}
		return "department/input";
	}

	@RequiredPermission("部门保存或修改")
	@RequestMapping("/saveOrUpdate")
	public String saveOrUpdate(Department department) {
		if (department.getId() != null) {
			departmentService.update(department);
		} else {
			departmentService.save(department);
		}
		return "redirect:/department/list";
	}
}
