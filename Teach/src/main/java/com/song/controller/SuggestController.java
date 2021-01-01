package com.song.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.song.pojo.Suggest;
import com.song.service.SuggestService;
import com.song.util.JsonResult;


@RestController
@RequestMapping("suggest")
public class SuggestController {
	
	@Autowired
	private SuggestService suggestService;
	
	@PostMapping("add")
	public JsonResult add(Suggest suggest) {
		suggestService.add(suggest);
		return JsonResult.ok();
	}
	
	
	

}
