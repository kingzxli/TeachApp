package com.song.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.song.pojo.Users;
import com.song.service.UsersService;
import com.song.util.JsonResult;

@RestController
@RequestMapping("users")
public class UsersController {
	
	@Autowired
	private UsersService usersService;
	
	@GetMapping("login")
	public JsonResult Login(Users user) {
		Users u = usersService.Login(user);
		return JsonResult.ok(u);
	}
	
	@GetMapping("add")
	public JsonResult add(Users user) {
		usersService.add(user);
		return JsonResult.ok();
	}

}
