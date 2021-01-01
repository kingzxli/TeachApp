package com.song.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.song.pojo.Goods;
import com.song.pojo.Refund;
import com.song.service.OrderService;
import com.song.service.RefundService;
import com.song.util.JsonResult;

@RestController
@RequestMapping("refund")
public class RefundController {
	
	@Autowired
	private RefundService refundService;
	@Autowired
	private OrderService orderService;
	
	@PostMapping("add")
	public JsonResult add(Refund refund) {
		refundService.add(refund);
		orderService.updateStatus("退款中", refund.getOrdernum());
		return JsonResult.ok();
	}
	
	
	//退款详情
	@PostMapping("selectByOpenid")
	public JsonResult selectByOpenid(String openid) {
		List<Refund> r = refundService.selectByOpenid(openid);
		return JsonResult.ok(r);
	}
	
	
	//后台查询
	@GetMapping("selectall")
	public JsonResult selectAll(int pageSize,int pageId) {
		PageHelper.startPage(pageId,pageSize);
		List<Refund> list = refundService.selectAll();
		PageInfo<Refund> page = new PageInfo<Refund>();
		page.setPageNum(pageId);
		page.setPageSize(pageSize);
		page.setSize(list.size());
		page.setList(list);
		return JsonResult.ok(page);
	}
	
	

}
