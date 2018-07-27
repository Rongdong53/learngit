package cn.wolfcode.wms.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.wolfcode.wms.domain.StockoutcomeBill;
import cn.wolfcode.wms.query.StockoutcomeBillQueryObject;
import cn.wolfcode.wms.service.IClientService;
import cn.wolfcode.wms.service.IDepotService;
import cn.wolfcode.wms.service.IStockoutcomeBillService;
import cn.wolfcode.wms.util.JsonResult;
import cn.wolfcode.wms.util.RequiredPermission;

/**
 * @author DRD
 * @date 2018年7月9日 下午2:06:42
 * @website www.wolfcode.cn
 * @description
 */
@Controller
@RequestMapping("/stockoutcomeBill")
public class StockoutcomeController {
	@Autowired
	private IStockoutcomeBillService stockoutcomeBillService;
	@Autowired
	private IDepotService depotService;
	@Autowired
	private IClientService clientService;
	


	@RequiredPermission("销售出库单列表")
	@RequestMapping("/list")
	public String list(Model model, @ModelAttribute("qo")StockoutcomeBillQueryObject qo) {
		model.addAttribute("result", stockoutcomeBillService.query(qo));
		model.addAttribute("depots", depotService.selectAll());
		model.addAttribute("clients", clientService.selectAll());
		return "stockoutcomeBill/list";
	}
	
	@RequiredPermission("销售出库单删除")
	@ResponseBody
	@RequestMapping("/delete")
	public JsonResult delete(Long id) {
		JsonResult result = new JsonResult();
		try {
			if (id != null) {
				stockoutcomeBillService.deleteByPrimaryKey(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.markMsg("删除失败!");
		}
		return result;
	}

	@RequiredPermission("销售出库单编辑")
	@RequestMapping("/input")
	public String input(Model model, Long id) {
		if (id != null) {
			model.addAttribute("stockoutcomeBill", stockoutcomeBillService.selectByPrimaryKey(id));
		}
		model.addAttribute("depots", depotService.selectAll());
		model.addAttribute("clients", clientService.selectAll());
		return "stockoutcomeBill/input";
	}
	
	@RequestMapping("/show")
	public String show(Model model, Long id) {
		if (id != null) {
			model.addAttribute("stockoutcomeBill", stockoutcomeBillService.selectByPrimaryKey(id));
		}
		model.addAttribute("depots", depotService.selectAll());
		model.addAttribute("clients", clientService.selectAll());
		return "stockoutcomeBill/show";
	}
	
	@ResponseBody
	@RequestMapping("/auditor")
	public JsonResult auditor(Long id) {
		JsonResult result = new JsonResult();
		try {
			if (id != null) {
				stockoutcomeBillService.auditorByPrimaryKey(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.markMsg(e.getMessage());
		}
		return result;
	}

	@RequiredPermission("销售出库单保存或修改")
	@RequestMapping("/saveOrUpdate")
	public String saveOrUpdate(StockoutcomeBill stockoutcomeBill) {
		if (stockoutcomeBill.getId() != null) {
			stockoutcomeBillService.updateByPrimaryKey(stockoutcomeBill);
		} else {
			stockoutcomeBillService.insert(stockoutcomeBill);
		}
		return "redirect:/stockoutcomeBill/list";
	}
}
