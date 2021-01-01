package com.song.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.song.pojo.Violations;
import com.song.service.ViolationsService;
import com.song.util.JsonResult;

@RestController
@RequestMapping("violation")
public class ViolationsController {
	
	@Autowired
	private ViolationsService violationsService;
	
	@PostMapping("select")
	public JsonResult select(int tid) {
		List<Violations> v = violationsService.select(tid);
		return JsonResult.ok(v);
	}

}
