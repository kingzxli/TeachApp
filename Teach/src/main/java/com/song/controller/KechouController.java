package com.song.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.song.pojo.Kechou;
import com.song.service.KechouService;
import com.song.util.JsonResult;

@RestController
@RequestMapping("kechou")
public class KechouController {
	
	@Autowired
	private KechouService kechouService;
	
	@PostMapping("selectById")
	public JsonResult selectById(int tid) {
		List<Kechou> k = kechouService.selectById(tid);
		return JsonResult.ok(k);
	}

}
