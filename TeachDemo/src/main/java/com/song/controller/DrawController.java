package com.song.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.song.pojo.Draw;
import com.song.pojo.Parent;
import com.song.pojo.Teacher;
import com.song.service.DrawService;
import com.song.service.ParentService;
import com.song.service.TeacherService;
import com.song.util.JsonResult;

@RestController
@RequestMapping("draw")
public class DrawController {
	
	@Autowired
	private DrawService drawService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private ParentService parentService;
	
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
	
	
	@GetMapping("selectall")
	public JsonResult selectall(int pageId,int pageSize) {
		PageHelper.startPage(pageId, pageSize);
		List<Draw> d = drawService.selectAll();
		for(int i=0;i<d.size();i++) {
			Teacher t = teacherService.selectByOpenid(d.get(i).getOpenid());
			Parent p = parentService.selectByOpenid(d.get(i).getOpenid());
			if(t!=null) {
				d.get(i).setName(t.getName());
				PageInfo<Draw> page = new PageInfo<Draw>(d,pageSize);
				page.setPageNum(pageId);
				page.setPageSize(pageSize);
				page.setSize(d.size());
				page.setList(d);
				return JsonResult.ok(page);
			}
			if(p!=null) {
				d.get(i).setName(p.getName());
				PageInfo<Draw> page = new PageInfo<Draw>(d,pageSize);
				page.setPageNum(pageId);
				page.setPageSize(pageSize);
				page.setSize(d.size());
				page.setList(d);
				return JsonResult.ok(page);
			}
		}
		return null;
		
	}
	
}
