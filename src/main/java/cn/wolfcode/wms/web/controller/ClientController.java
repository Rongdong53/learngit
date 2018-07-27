package cn.wolfcode.wms.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.wolfcode.wms.domain.Client;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IClientService;
import cn.wolfcode.wms.util.JsonResult;
import cn.wolfcode.wms.util.RequiredPermission;

/**
 * @author DRD
 * @date 2018年7月9日 下午2:06:42
 * @website www.wolfcode.cn
 * @description
 */
@Controller
@RequestMapping("/client")
public class ClientController {
	@Autowired
	private IClientService clientService;


	@RequiredPermission("客户列表")
	@RequestMapping("/list")
	public String list(Model model, @ModelAttribute("qo")QueryObject qo) {
		model.addAttribute("result", clientService.query(qo));
		return "client/list";
	}
	
	@RequiredPermission("客户删除")
	@ResponseBody
	@RequestMapping("/delete")
	public JsonResult delete(Long id) {
		JsonResult result = new JsonResult();
		try {
			if (id != null) {
				clientService.deleteByPrimaryKey(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.markMsg("删除失败!");
		}
		return result;
	}

	@RequiredPermission("客户编辑")
	@RequestMapping("/input")
	public String input(Model model, Long id) {
		if (id != null) {
			model.addAttribute("client", clientService.selectByPrimaryKey(id));
		}
		return "client/input";
	}

	@RequiredPermission("客户保存或修改")
	@RequestMapping("/saveOrUpdate")
	public String saveOrUpdate(Client client) {
		if (client.getId() != null) {
			clientService.updateByPrimaryKey(client);;;
		} else {
			clientService.insert(client);
		}
		return "redirect:/client/list";
	}
}
