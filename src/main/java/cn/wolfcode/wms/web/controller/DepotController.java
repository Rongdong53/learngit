package cn.wolfcode.wms.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.wolfcode.wms.domain.Depot;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IDepotService;
import cn.wolfcode.wms.util.JsonResult;
import cn.wolfcode.wms.util.RequiredPermission;

/**
 * @author DRD
 * @date 2018年7月9日 下午2:06:42
 * @website www.wolfcode.cn
 * @description
 */
@Controller
@RequestMapping("/depot")
public class DepotController {
	@Autowired
	private IDepotService depotService;


	@RequiredPermission("仓库列表")
	@RequestMapping("/list")
	public String list(Model model, @ModelAttribute("qo")QueryObject qo) {
		model.addAttribute("result", depotService.query(qo));
		return "depot/list";
	}
	
	@RequiredPermission("仓库删除")
	@ResponseBody
	@RequestMapping("/delete")
	public JsonResult delete(Long id) {
		JsonResult result = new JsonResult();
		try {
			if (id != null) {
				depotService.deleteByPrimaryKey(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.markMsg("删除失败!");
		}
		return result;
	}

	@RequiredPermission("仓库编辑")
	@RequestMapping("/input")
	public String input(Model model, Long id) {
		if (id != null) {
			model.addAttribute("depot", depotService.selectByPrimaryKey(id));
		}
		return "depot/input";
	}

	@RequiredPermission("仓库保存或修改")
	@RequestMapping("/saveOrUpdate")
	@ResponseBody
	public JsonResult saveOrUpdate(Depot depot) {
		JsonResult result = new JsonResult();
		try {
			if (depot.getId() != null) {
				depotService.updateByPrimaryKey(depot);;;
			} else {
				depotService.insert(depot);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.markMsg("保存失败");
		}
		return result;
	}
}
