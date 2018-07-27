package cn.wolfcode.wms.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.wolfcode.wms.query.ProductStockQueryObject;
import cn.wolfcode.wms.service.IBrandService;
import cn.wolfcode.wms.service.IDepotService;
import cn.wolfcode.wms.service.IProductStockService;

/**
 * @author DRD
 * @date 2018年7月17日 下午11:41:41
 * @website www.wolfcode.cn
 * @description
 */
@Controller
@RequestMapping("/productStock")
public class ProductStockController {
	@Autowired
	private IProductStockService productStockService;
	@Autowired
	private IDepotService depotService;
	@Autowired
	private IBrandService brandService;
	
	@RequestMapping("/list")
	public String list(Model model,@ModelAttribute("qo") ProductStockQueryObject qo){
		model.addAttribute("result", productStockService.query(qo));
		model.addAttribute("depots", depotService.selectAll());
		model.addAttribute("brands", brandService.selectAll());
		return "productStock/list";
	}
}
