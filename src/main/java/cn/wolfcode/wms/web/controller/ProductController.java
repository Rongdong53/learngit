package cn.wolfcode.wms.web.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.wolfcode.wms.domain.Brand;
import cn.wolfcode.wms.domain.Product;
import cn.wolfcode.wms.query.ProductQueryObject;
import cn.wolfcode.wms.service.IBrandService;
import cn.wolfcode.wms.service.IProductService;
import cn.wolfcode.wms.util.JsonResult;
import cn.wolfcode.wms.util.RequiredPermission;
import cn.wolfcode.wms.util.UploadUtil;

/**
 * @author DRD
 * @date 2018年7月9日 下午2:06:42
 * @website www.wolfcode.cn
 * @description
 */
@Controller
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private IProductService productService;
	@Autowired
	private IBrandService brandService;
	@Autowired
	private ServletContext ctx;

	@RequiredPermission("商品列表")
	@RequestMapping("/list")
	public String list(Model model, @ModelAttribute("qo") ProductQueryObject qo) {
		model.addAttribute("brands", brandService.selectAll());
		model.addAttribute("result", productService.query(qo));
		return "product/list";
	}

	@RequestMapping("/productList")
	public String productList(Model model, @ModelAttribute("qo") ProductQueryObject qo) {
		model.addAttribute("brands", brandService.selectAll());
		model.addAttribute("result", productService.query(qo));
		return "product/productList";
	}

	@RequiredPermission("商品删除")
	@ResponseBody
	@RequestMapping("/delete")
	public JsonResult delete(Long id, String imagePath) {
		JsonResult result = new JsonResult();
		UploadUtil.deleteFile(ctx, imagePath);
		try {
			if (id != null) {
				productService.deleteByPrimaryKey(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.markMsg("删除失败!");
		}
		return result;
	}

	@RequiredPermission("商品编辑")
	@RequestMapping("/input")
	public String input(Model model, Long id) {
		if (id != null) {
			model.addAttribute("product", productService.selectByPrimaryKey(id));
		}
		model.addAttribute("brands", brandService.selectAll());
		return "product/input";
	}

	@RequiredPermission("商品保存或修改")
	@RequestMapping("/saveOrUpdate")
	public String saveOrUpdate(Product product, MultipartFile pic) {
		// 如果之前有图片(根据product的imagePath来判断),而且用户需要修改图片
		// 判断字符串为是否为空，应该判断null和空字符串
		if (pic.getSize() != 0 && StringUtils.hasLength(product.getImagePath())) {
			// 在磁盘中删除旧图片
			UploadUtil.deleteFile(ctx, product.getImagePath());
		}
		// 解决保存没选图片时有imagePath生成
		if (!pic.isEmpty()) {
			String imagePath = UploadUtil.upload(pic, ctx.getRealPath("/upload"));
			product.setImagePath(imagePath);
		}
		Brand brand = brandService.selectByPrimaryKey(product.getBrandId());
		product.setBrandName(brand.getName());
		if (product.getId() != null) {
			productService.updateByPrimaryKey(product);
		} else {
			productService.insert(product);
		}
		return "redirect:/product/list";
	}
}
