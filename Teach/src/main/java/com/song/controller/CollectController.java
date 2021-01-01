package com.song.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.song.pojo.Collect;
import com.song.pojo.Goods;
import com.song.pojo.Teacher;
import com.song.service.CollectService;
import com.song.service.TeacherService;
import com.song.util.JsonResult;

@RestController
@RequestMapping("collect")
public class CollectController {

	@Autowired
	private CollectService collectService;
	@Autowired
	private TeacherService teacherService;
	
	@PostMapping("addtext")
	public JsonResult addtext(Integer pid,Integer tid) {
		collectService.insert(tid, pid,"text");
		return JsonResult.ok();
	}
	
	@PostMapping("add")
	public JsonResult add(Integer tid,Integer pid) {
		collectService.insert(tid, pid,"teacher");
		return JsonResult.ok();
	}
	
	@PostMapping("delete")
	public JsonResult delete(Integer tid,Integer pid) {
		collectService.delete(tid, pid);
		return JsonResult.ok();
	}
	
	@PostMapping("deleteT")
	public JsonResult deleteT(Integer tid,Integer pid) {
		collectService.deleteT(tid, pid);
		return JsonResult.ok();
	}
	
	@PostMapping("select")
	public JsonResult select(Integer tid,Integer pid) {
		Collect c = collectService.select(tid, pid);
		if(c!=null) {
			return JsonResult.ok(c);
		}else {
			return JsonResult.ok(null);
		}
	}
	
	@PostMapping("selectT")
	public JsonResult selectT(Integer tid,Integer pid) {
		Collect c = collectService.selectT(tid, pid);
		if(c!=null) {
			return JsonResult.ok(c);
		}else {
			return JsonResult.ok(null);
		}
	}
	
	
	//查询老师收藏列表
	@PostMapping("selectByPid")
	public JsonResult selectByPid(Integer pid) {
		List<Teacher> t = new ArrayList<Teacher>();
		List<Collect> c = collectService.selectByPid(pid);
		for (int i=0; i<c.size();i++) {
			t.addAll(c.get(i).getTeacher());
		}
		return JsonResult.ok(t);
	}
	
	//查询文章收藏列表
	@PostMapping("selectByid")
	public JsonResult selectByid(Integer id) {
		List<Goods> g = new ArrayList<Goods>();
		List<Collect> c = collectService.selectByid(id);
		if(c.size()!=0) {
			for (int i=0; i<c.size();i++) {
				g.addAll(c.get(i).getGoods());
			}
		}
		return JsonResult.ok(g);
	}
	
}
