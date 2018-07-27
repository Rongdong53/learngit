package cn.wolfcode.wms.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.wolfcode.wms.domain.Role;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IPermissionService;
import cn.wolfcode.wms.service.IRoleService;
import cn.wolfcode.wms.service.ISystemMenuService;
import cn.wolfcode.wms.util.JsonResult;
import cn.wolfcode.wms.util.RequiredPermission;

/**
 * @author DRD
 * @date 2018年7月9日 下午2:06:42
 * @website www.wolfcode.cn
 * @description
 */
@Controller
@RequestMapping("/role")
public class RoleController {
	@Autowired
	private IRoleService roleService;
	@Autowired
	private IPermissionService permissionService;
	@Autowired
	private ISystemMenuService systemMenuService;


	@RequiredPermission("角色列表")
	@RequestMapping("/list")
	public String list(Model model, @ModelAttribute("qo") QueryObject qo) {
		model.addAttribute("result", roleService.query(qo));
		return "role/list";
	}

	@RequiredPermission("角色删除")
	@ResponseBody
	@RequestMapping("/delete")
	public JsonResult delete(Long id) {
		JsonResult result = new JsonResult();
		try {
			if (id != null) {
				roleService.delete(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.markMsg("删除失败!");
		}
		return result;
	}

	@RequiredPermission("角色编辑")
	@RequestMapping("/input")
	public String input(Model model, Long id) {
		if (id != null) {
			model.addAttribute("role", roleService.get(id));
		}
		model.addAttribute("menus", systemMenuService.listAll());
		model.addAttribute("permissions", permissionService.listAll());
		return "role/input";
	}

	@RequiredPermission("角色保存或修改")
	@RequestMapping("/saveOrUpdate")
	public String saveOrUpdate(Role role, Long[] permissionIds, Long[] menuIds) {
		if (role.getId() != null) {
			roleService.update(role, permissionIds,menuIds);
		} else {
			roleService.save(role, permissionIds,menuIds);
		}
		return "redirect:/role/list";
	}
}
