package com.song.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.song.mapper.RefundMapper;
import com.song.pojo.Refund;
import com.song.service.RefundService;

@Service
public class RefundServiceImpl implements RefundService{

	@Autowired
	private RefundMapper refundMapper;
	
	@Override
	public void add(Refund refund) {
		// TODO Auto-generated method stub
		refundMapper.add(refund);
	}

	@Override
	public List<Refund> selectByOpenid(String openid) {
		// TODO Auto-generated method stub
		return refundMapper.selectByOpenid(openid);
	}

	@Override
	public List<Refund> selectAll() {
		// TODO Auto-generated method stub
		return refundMapper.selectAll();
	}

	@Override
	public void update(String money, String ordernum) {
		// TODO Auto-generated method stub
		refundMapper.update(money, ordernum);
	}


}
