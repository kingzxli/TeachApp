package com.song.mapper;

import java.util.List;

import com.song.pojo.Coupons;

public interface CouponsMapper {

	//添加
	void add(Coupons coupons);
	//查看使用状态
	List<Coupons> selectByStatus(Coupons coupons);
	//改状态
	void updateStatus(int id);
}
