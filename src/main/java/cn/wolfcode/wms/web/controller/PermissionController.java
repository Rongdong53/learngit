package cn.wolfcode.wms.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IPermissionService;
import cn.wolfcode.wms.util.JsonResult;

/**
 * @author DRD
 * @date 2018年7月9日 下午2:06:42
 * @website www.wolfcode.cn
 * @description
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {
	@Autowired
	private IPermissionService permissionService;

	@RequestMapping("/list")
	public String list(Model model, @ModelAttribute("qo")QueryObject qo) {
		model.addAttribute("result", permissionService.query(qo));
		return "permission/list";
	}

	@ResponseBody
	@RequestMapping("/delete")
	public JsonResult delete(Long id) {
		JsonResult result = new JsonResult();
		try {
			if (id != null) {
				permissionService.delete(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.markMsg("删除失败!");
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/loadPermission")
	public JsonResult loadPermission(){
		JsonResult result = new JsonResult();
		try {
			permissionService.loadPermission();
		} catch (Exception e) {
			e.printStackTrace();
			result.markMsg("权限加载失败");
		}
		return result;
	}
}
