package com.song.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.song.pojo.Parent;
import com.song.pojo.Suggest;
import com.song.pojo.Teacher;
import com.song.service.ParentService;
import com.song.service.SuggestService;
import com.song.service.TeacherService;
import com.song.util.JsonResult;


@RestController
@RequestMapping("suggest")
public class SuggestController {
	
	@Autowired
	private SuggestService suggestService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private ParentService parentService;
	
	@PostMapping("add")
	public JsonResult add(Suggest suggest) {
		suggestService.add(suggest);
		return JsonResult.ok();
	}
	
	
	
	@GetMapping("selectAll")
	public JsonResult selectAll(int pageId,int pageSize) {
		PageHelper.startPage(pageId, pageSize);
		List<Suggest> s = suggestService.selectAll();
		PageInfo<Suggest> page = new PageInfo<Suggest>(s,pageSize);
		
//		for (int i=0;i<s.size();i++) {
//			if(s.get(i).getTypes()==1) {
//				Teacher t = teacherService.selectById(s.get(i).getTid(), "");
//				s.get(i).setName(t.getName());
//			}else {
//				Parent p = parentService.selectByid(s.get(i).getTid());
//				s.get(i).setName(p.getName());
//			}
//		}
		page.setPageNum(pageId);
		page.setPageSize(pageSize);
		page.setSize(s.size());
		page.setList(s);
		return JsonResult.ok(page);
	}
	

}
