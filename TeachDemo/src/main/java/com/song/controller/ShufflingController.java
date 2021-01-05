package com.song.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.song.pojo.Shuffling;
import com.song.service.ShufflingService;
import com.song.util.JsonResult;

/**
 * 
 * @author Song
 *
 */

@RestController
@RequestMapping("shuffling")
public class ShufflingController {
	
	@Autowired
	private ShufflingService shufflingService;
	
	@GetMapping("/selectbytype")
	public JsonResult selectAll(int type) {
		List<Shuffling> s = shufflingService.selectByType(type);
		return JsonResult.ok(s);
	}
	
	@PostMapping("/selectbyid")
	public JsonResult selectbyid(int id) {
		Shuffling s = shufflingService.selectByid(id);
		return JsonResult.ok(s);
	}
	
	
	
	
	@PostMapping("add")
	public JsonResult add(@RequestBody Shuffling shuffling) {
		shufflingService.add(shuffling);
		return JsonResult.ok();
	}
	
	@GetMapping("delete")
	public JsonResult adeletedd(Integer id) {
		System.out.println(id);
		shufflingService.delete(id);
		return JsonResult.ok();
	}
	
	@PostMapping("update")
	public JsonResult update(@RequestBody Shuffling shuffling) {
		shufflingService.update(shuffling);
		return JsonResult.ok();
	}
	
	@GetMapping("selectall")
	public JsonResult selectall() {
		List<Shuffling> s = shufflingService.selectAll();
		return JsonResult.ok(s);
	}
	
	
	
	
	
	
	
}
