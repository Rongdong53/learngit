package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.util.UserContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LogoutController {

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute(UserContext.EMPLOYEE_IN_SESSION);
        return "redirect:/login.jsp";
    }
}
