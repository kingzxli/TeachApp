package com.song.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	public JsonResult selectById(@RequestBody Kechou kechou) {
		System.out.println(kechou.getTid());
		List<Kechou> k = kechouService.selectById(kechou.getTid());
		return JsonResult.ok(k);
	}

	@PostMapping("add")
	public JsonResult add(@RequestBody Kechou k) {
		String todays = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		k.setAddtime(todays);
		kechouService.add(k);
		return JsonResult.ok();
	}
	
	@PostMapping("update")
	public JsonResult update(@RequestBody Kechou k) {
		kechouService.update(k);
		return JsonResult.ok();
	}
	
	
	@PostMapping("delete")
	public JsonResult delete(@RequestBody Kechou k) {
		kechouService.delete(k.getId());
		return JsonResult.ok();
	}
	
	
}
