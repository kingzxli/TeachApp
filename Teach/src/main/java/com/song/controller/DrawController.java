package com.song.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.song.pojo.Draw;
import com.song.service.DrawService;
import com.song.util.JsonResult;

@RestController
@RequestMapping("draw")
public class DrawController {
	
	@Autowired
	private DrawService drawService;
	
	@PostMapping("add")
	public JsonResult add(Draw draw) {
		String todays = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		draw.setAddtime(todays);
		drawService.add(draw);
		return JsonResult.ok();
	}

	@PostMapping("select")
	public JsonResult select(String openid) {
		List<Draw> d = drawService.selectByOpenid(openid);
		return JsonResult.ok(d);
	}
	
}
