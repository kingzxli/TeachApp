package com.song.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.song.pojo.Shuffflings;
import com.song.service.ShufflingsService;
import com.song.util.JsonResult;

@RestController
@RequestMapping("shus")
public class ShufflingsController {

	@Autowired
	private ShufflingsService shufflingsService;
	
	@GetMapping("select")
	public JsonResult select(int type) {
		List<Shuffflings> select = shufflingsService.select(type);
		return JsonResult.ok(select);
	}
	
}
