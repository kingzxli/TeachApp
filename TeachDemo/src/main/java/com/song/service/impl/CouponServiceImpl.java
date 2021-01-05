package com.song.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.song.mapper.CouponsMapper;
import com.song.pojo.Coupons;
import com.song.service.CouponService;

@Service
public class CouponServiceImpl implements CouponService{

	@Autowired
	private CouponsMapper couponsMapper;
	
	@Override
	public void add(Coupons coupons) {
		// TODO Auto-generated method stub
		couponsMapper.add(coupons);
	}

	@Override
	public List<Coupons> selectByStatus(Coupons coupons) {
		// TODO Auto-generated method stub
		return couponsMapper.selectByStatus(coupons);
	}

	@Override
	public void updateStatus(int id) {
		// TODO Auto-generated method stub
		couponsMapper.updateStatus(id);
	}


}
