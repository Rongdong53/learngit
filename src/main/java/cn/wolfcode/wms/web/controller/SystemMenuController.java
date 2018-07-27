package cn.wolfcode.wms.web.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.wolfcode.wms.domain.SystemMenu;
import cn.wolfcode.wms.query.SystemMenuQueryObject;
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
@RequestMapping("/systemmenu")
public class SystemMenuController {
	@Autowired
	private ISystemMenuService systemmenuService;

	@RequiredPermission("菜单列表")
	@RequestMapping("/list")
	public String list(Model model, @ModelAttribute("qo") SystemMenuQueryObject qo) {
		List<SystemMenu> parents = new ArrayList<>();
		// 如果父级菜单的id不为null,执行查询
		if (qo.getParentId() != null) {
			SystemMenu parent = systemmenuService.get(qo.getParentId());
			while (parent != null) {
				parents.add(parent);
				// 继续找父菜单的父菜单
				// 调用菜单的getParent()方法触发延迟加载，执行查询
				parent = parent.getParent();
			}
		}
		// 将集合中的元素顺序颠倒
		Collections.reverse(parents);
		model.addAttribute("parents", parents);
		model.addAttribute("result", systemmenuService.query(qo));
		return "systemmenu/list";
	}

	@RequiredPermission("菜单删除")
	@ResponseBody
	@RequestMapping("/delete")
	public JsonResult delete(Long id) {
		JsonResult result = new JsonResult();
		try {
			if (id != null) {
				systemmenuService.delete(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.markMsg("请删除该模块的所有子菜单!");
		}
		return result;
	}

	@RequiredPermission("菜单编辑")
	@RequestMapping("/input")
	public String input(Model model, Long id, Long parentId) {
		if (parentId == null) {
			model.addAttribute("parentName", "根目录");
		} else {
			SystemMenu systemMenu = systemmenuService.get(parentId);
			model.addAttribute("parentName", systemMenu.getName());
			model.addAttribute("parentId", systemMenu.getId());
		}
		if (id != null) {
			model.addAttribute("systemmenu", systemmenuService.get(id));
		}
		return "systemmenu/input";
	}

	@RequiredPermission("菜单保存或修改")
	@RequestMapping("/saveOrUpdate")
	public String saveOrUpdate(SystemMenu systemmenu) {
		if (systemmenu.getId() != null) {
			systemmenuService.update(systemmenu);
		} else {
			systemmenuService.save(systemmenu);
		}
		if (systemmenu.getParent().getId() == null) {
			return "redirect:/systemmenu/list";
		}
		return "redirect:/systemmenu/list?parentId=" + systemmenu.getParent().getId();
	}

	@ResponseBody
	@RequestMapping("/loadmenu")
	public List<Map<String, Object>> loadmenu(String parentSn) {
		return systemmenuService.selectMenuByParentSn(parentSn);
	}
}
