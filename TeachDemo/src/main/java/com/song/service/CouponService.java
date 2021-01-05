package com.song.service;

import java.util.List;

import com.song.pojo.Coupons;

public interface CouponService {
	
	void add(Coupons coupons);
	List<Coupons> selectByStatus(Coupons coupons);
	void updateStatus(int id);
}
