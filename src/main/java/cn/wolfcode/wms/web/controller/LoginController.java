package cn.wolfcode.wms.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.wolfcode.wms.service.IEmployeeService;
import cn.wolfcode.wms.util.LogicException;

/**
 * @author DRD
 * @date 2018年7月10日 下午10:25:12
 * @website www.wolfcode.cn
 * @description
 */
@Controller
public class LoginController {
	@Autowired
	private IEmployeeService employeeService;

	@RequestMapping("/login")
	public String login(Model model,String userName,String password){
		try {
			employeeService.queryUserNameAndPassword(userName,password);
		} catch (LogicException e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", e.getMessage());
			return "forward:/login.jsp";
		}
		return "redirect:/main";
	}

	@RequestMapping("/main")
	public String main() {
		return "main";
	}
}
