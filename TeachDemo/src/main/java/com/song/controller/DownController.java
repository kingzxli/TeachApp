package com.song.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.song.pojo.Down;
import com.song.service.DownService;
import com.song.util.JsonResult;

@RestController
@RequestMapping("down")
public class DownController {
	
	@Autowired
	private DownService downService;
	
	
	@GetMapping("select")
	public JsonResult select() {
		List<Down> d = downService.select();
		return JsonResult.ok(d);
	}
	
	
}
