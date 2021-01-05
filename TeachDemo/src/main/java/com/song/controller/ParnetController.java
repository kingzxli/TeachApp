package com.song.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.song.config.MySpringMvcConfigurer;
import com.song.pojo.Parent;
import com.song.service.ParentService;
import com.song.util.JsonResult;
import com.song.util.MdUtil;

@RestController
@RequestMapping("parent")
//@SessionAttributes(value = {"parent"})
public class ParnetController {

	@Autowired
	private ParentService parentService;

	
	@PostMapping("login")
//	@ModelAttribute("parent")
	public JsonResult Login(Model model,@RequestBody Parent parent,HttpServletRequest request,HttpServletResponse response) {
		System.out.println("session====="+request.getSession().getId());
		String password = MdUtil.md(parent.getPassword());
		parent.setPassword(password);
		System.out.println(password);
		Parent p = parentService.Login(parent);
		if(p!=null) {
			HttpSession session = request.getSession();
			System.out.println("获取的===="+session.getId());
			session.setAttribute("parent","admin");
			model.addAttribute("parent", "admin");
			return JsonResult.ok(session.getId());
		}else {
			return JsonResult.errorMsg("账号密码错误");
		}
	}
	
	
}
