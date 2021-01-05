package com.song.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.song.pojo.Coupons;
import com.song.service.CouponService;
import com.song.util.JsonResult;

@RestController
@RequestMapping("coupons")
public class CouponsController {
	
	@Autowired
	private CouponService couponService;
	
	@PostMapping("select")
	public JsonResult select(Coupons coupons) {
		List<Coupons> list = couponService.selectByStatus(coupons);
		return JsonResult.ok(list);
	}

}
