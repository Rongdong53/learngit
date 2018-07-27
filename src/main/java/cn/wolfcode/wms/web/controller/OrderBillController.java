package cn.wolfcode.wms.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.wolfcode.wms.domain.OrderBill;
import cn.wolfcode.wms.query.OrderBillQueryObject;
import cn.wolfcode.wms.service.IOrderBillService;
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
@RequestMapping("/orderBill")
public class OrderBillController {
	@Autowired
	private IOrderBillService orderBillService;
	@Autowired
	private ISupplierService supplierService;

	@RequiredPermission("订单列表")
	@RequestMapping("/list")
	public String list(Model model, @ModelAttribute("qo")OrderBillQueryObject qo) {
		model.addAttribute("result", orderBillService.query(qo));
		model.addAttribute("suppliers", supplierService.selectAll());
		return "orderBill/list";
	}
	
	@RequiredPermission("订单删除")
	@ResponseBody
	@RequestMapping("/delete")
	public JsonResult delete(Long id) {
		JsonResult result = new JsonResult();
		try {
			if (id != null) {
				orderBillService.deleteByPrimaryKey(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.markMsg("删除失败!");
		}
		return result;
	}

	@RequiredPermission("订单编辑")
	@RequestMapping("/input")
	public String input(Model model, Long id) {
		if (id != null) {
			model.addAttribute("orderBill", orderBillService.selectByPrimaryKey(id));
		}
		model.addAttribute("suppliers", supplierService.selectAll());
		return "orderBill/input";
	}
	
	@RequestMapping("/show")
	public String show(Model model, Long id) {
		if (id != null) {
			model.addAttribute("orderBill", orderBillService.selectByPrimaryKey(id));
		}
		return "orderBill/show";
	}

	@RequiredPermission("订单保存或修改")
	@RequestMapping("/saveOrUpdate")
	public String saveOrUpdate(OrderBill orderBill) {
		if (orderBill.getId() != null) {
			orderBillService.updateByPrimaryKey(orderBill);;;
		} else {
			orderBillService.insert(orderBill);
		}
		return "redirect:/orderBill/list";
	}
	
	@ResponseBody
	@RequestMapping("/auditor")
	public JsonResult auditor(Long id) {
		JsonResult result = new JsonResult();
		try {
			if (id != null) {
				orderBillService.auditorByPrimaryKey(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.markMsg("审核失败!");
		}
		return result;
	}
}
