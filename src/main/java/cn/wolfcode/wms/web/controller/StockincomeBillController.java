package cn.wolfcode.wms.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.wolfcode.wms.domain.StockincomeBill;
import cn.wolfcode.wms.query.StockincomeBillQueryObject;
import cn.wolfcode.wms.service.IDepotService;
import cn.wolfcode.wms.service.IStockincomeBillService;
import cn.wolfcode.wms.util.JsonResult;
import cn.wolfcode.wms.util.RequiredPermission;

/**
 * @author DRD
 * @date 2018年7月9日 下午2:06:42
 * @website www.wolfcode.cn
 * @description
 */
@Controller
@RequestMapping("/stockincomeBill")
public class StockincomeBillController {
	@Autowired
	private IStockincomeBillService stockincomeBillService;
	@Autowired
	private IDepotService depotService;


	@RequiredPermission("采购入库单列表")
	@RequestMapping("/list")
	public String list(Model model, @ModelAttribute("qo")StockincomeBillQueryObject qo) {
		model.addAttribute("result", stockincomeBillService.query(qo));
		model.addAttribute("depots", depotService.selectAll());
		return "stockincomeBill/list";
	}
	
	@RequiredPermission("采购入库单删除")
	@ResponseBody
	@RequestMapping("/delete")
	public JsonResult delete(Long id) {
		JsonResult result = new JsonResult();
		try {
			if (id != null) {
				stockincomeBillService.deleteByPrimaryKey(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.markMsg("删除失败!");
		}
		return result;
	}

	@RequiredPermission("采购入库单编辑")
	@RequestMapping("/input")
	public String input(Model model, Long id) {
		if (id != null) {
			model.addAttribute("stockincomeBill", stockincomeBillService.selectByPrimaryKey(id));
		}
		model.addAttribute("depots", depotService.selectAll());
		return "stockincomeBill/input";
	}

	@RequiredPermission("采购入库单保存或修改")
	@RequestMapping("/saveOrUpdate")
	public String saveOrUpdate(StockincomeBill stockincomeBill) {
		if (stockincomeBill.getId() != null) {
			stockincomeBillService.updateByPrimaryKey(stockincomeBill);;;
		} else {
			stockincomeBillService.insert(stockincomeBill);
		}
		return "redirect:/stockincomeBill/list";
	}
	
	@ResponseBody
	@RequestMapping("/auditor")
	public JsonResult auditor(Long id) {
		JsonResult result = new JsonResult();
		try {
			if (id != null) {
				stockincomeBillService.auditorByPrimaryKey(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.markMsg("审核失败!");
		}
		return result;
	}
	
	@RequestMapping("/show")
	public String show(Model model, Long id) {
		if (id != null) {
			model.addAttribute("stockincomeBill", stockincomeBillService.selectByPrimaryKey(id));
		}
		return "stockincomeBill/show";
	}
}
