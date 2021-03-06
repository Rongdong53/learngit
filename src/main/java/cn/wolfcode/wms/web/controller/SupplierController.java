package cn.wolfcode.wms.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.wolfcode.wms.domain.Supplier;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.ISupplierService;
import cn.wolfcode.wms.util.JsonResult;
import cn.wolfcode.wms.util.RequiredPermission;

/**
 * @author DRD
 * @date 2018年7月9日 下午2:06:42
 * @website www.wolfcode.cn
 * @description
 */
@Controller
@RequestMapping("/supplier")
public class SupplierController {
	@Autowired
	private ISupplierService supplierService;


	@RequiredPermission("供应商列表")
	@RequestMapping("/list")
	public String list(Model model, @ModelAttribute("qo")QueryObject qo) {
		model.addAttribute("result", supplierService.query(qo));
		return "supplier/list";
	}
	
	@RequiredPermission("供应商删除")
	@ResponseBody
	@RequestMapping("/delete")
	public JsonResult delete(Long id) {
		JsonResult result = new JsonResult();
		try {
			if (id != null) {
				supplierService.deleteByPrimaryKey(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.markMsg("删除失败!");
		}
		return result;
	}

	@RequiredPermission("供应商编辑")
	@RequestMapping("/input")
	public String input(Model model, Long id) {
		if (id != null) {
			model.addAttribute("supplier", supplierService.selectByPrimaryKey(id));
		}
		return "supplier/input";
	}

	@RequiredPermission("供应商保存或修改")
	@RequestMapping("/saveOrUpdate")
	public String saveOrUpdate(Supplier supplier) {
		if (supplier.getId() != null) {
			supplierService.updateByPrimaryKey(supplier);;;
		} else {
			supplierService.insert(supplier);
		}
		return "redirect:/supplier/list";
	}
}
