package com.song.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.song.pojo.Comment;
import com.song.pojo.Down;
import com.song.pojo.Goods;
import com.song.pojo.Order;
import com.song.pojo.Teacher;
import com.song.service.DownService;
import com.song.service.GoodsService;
import com.song.service.OrderService;
import com.song.service.TeacherService;
import com.song.util.JsonResult;

@RestController
@RequestMapping("goods")
public class GoodsController {

	@Autowired
	private GoodsService goodsService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private DownService downService;
	@Autowired
	private TeacherService teacherService;
	
	@PostMapping("selectAll")
	public JsonResult selectAll(Goods goods,int pageId,int pageSize) {
		PageHelper.startPage(pageId,pageSize);
		List<Goods> list = goodsService.selectAll(goods);
		PageInfo<Goods> page = new PageInfo<Goods>(list,pageSize);
		page.setPageNum(pageId);
		page.setPageSize(pageSize);
		page.setSize(list.size());
		page.setList(list);
		return JsonResult.ok(page);
	}
	
	@PostMapping("selectById")
	public JsonResult selectById(int id) {
		Goods goods = goodsService.selectById(id);
		return JsonResult.ok(goods);
	}
	
	
	@PostMapping("selectByTid")
	public JsonResult selectByTid(int tid,int id) {
		List<Order> o = orderService.selectbytid(tid);
		Teacher t = teacherService.selectById(tid, "");
		List<Down> d = downService.selectAll(t.getOpenid());
		if(o!=null) {
			Goods goods = goodsService.selectById(id);
			return JsonResult.ok(goods);
		}else if(d != null) {
			Goods goods = goodsService.selectById(id);
			return JsonResult.ok(goods);
		}else {
			return JsonResult.ok(null);
		}
	}
	
	
	
	@GetMapping("select")
	public JsonResult select(int pageId,int pageSize) {
		PageHelper.startPage(pageId,pageSize);
		List<Goods> list = goodsService.select();
		PageInfo<Goods> page = new PageInfo<Goods>();
		page.setPageNum(pageId);
		page.setPageSize(pageSize);
		page.setSize(list.size());
		page.setList(list);
		return JsonResult.ok(page);
	}
	
	
	@GetMapping("delete")
	public JsonResult delete(int id) {
		goodsService.delete(id);
		return JsonResult.ok();
	}
	
	
	@PostMapping("add")
	public JsonResult add(@RequestBody Goods goods) {
		goodsService.add(goods);
		return JsonResult.ok();
	}
	
	@PostMapping("update")
	public JsonResult update(@RequestBody Goods goods) {
		goodsService.update(goods);
		return JsonResult.ok();
	}
	
	
}
