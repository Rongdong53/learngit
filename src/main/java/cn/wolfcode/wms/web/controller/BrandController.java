package cn.wolfcode.wms.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.wolfcode.wms.domain.Brand;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IBrandService;
import cn.wolfcode.wms.util.JsonResult;
import cn.wolfcode.wms.util.RequiredPermission;

/**
 * @author DRD
 * @date 2018年7月9日 下午2:06:42
 * @website www.wolfcode.cn
 * @description
 */
@Controller
@RequestMapping("/brand")
public class BrandController {
	@Autowired
	private IBrandService brandService;


	@RequiredPermission("品牌列表")
	@RequestMapping("/list")
	public String list(Model model, @ModelAttribute("qo")QueryObject qo) {
		model.addAttribute("result", brandService.query(qo));
		return "brand/list";
	}
	
	@RequiredPermission("品牌删除")
	@ResponseBody
	@RequestMapping("/delete")
	public JsonResult delete(Long id) {
		JsonResult result = new JsonResult();
		try {
			if (id != null) {
				brandService.deleteByPrimaryKey(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.markMsg("删除失败!");
		}
		return result;
	}

	@RequiredPermission("品牌编辑")
	@RequestMapping("/input")
	public String input(Model model, Long id) {
		if (id != null) {
			model.addAttribute("brand", brandService.selectByPrimaryKey(id));
		}
		return "brand/input";
	}

	@RequiredPermission("品牌保存或修改")
	@RequestMapping("/saveOrUpdate")
	public String saveOrUpdate(Brand brand) {
		if (brand.getId() != null) {
			brandService.updateByPrimaryKey(brand);;;
		} else {
			brandService.insert(brand);
		}
		return "redirect:/brand/list";
	}
}
