package com.song.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.song.pojo.Down;
import com.song.pojo.Notice;
import com.song.service.DownService;
import com.song.service.NoticeService;
import com.song.util.JsonResult;

@RestController
@RequestMapping("notice")
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	
	@GetMapping("select")
	public JsonResult select() {
		List<Notice> n = noticeService.selectAll();
		return JsonResult.ok(n);
	}
	
	
	@GetMapping("delete")
	public JsonResult delete(int id) {
		noticeService.delete(id);
		return JsonResult.ok();
	}
	
	
	@GetMapping("add")
	public JsonResult insert(Notice notice) {
		noticeService.insert(notice);
		return JsonResult.ok();
	}
	
	@GetMapping("update")
	public JsonResult update(Notice notice) {
		noticeService.update(notice);
		return JsonResult.ok();
	}
	
}
