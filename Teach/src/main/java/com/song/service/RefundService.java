package com.song.service;

import java.util.List;

import com.song.pojo.Refund;

public interface RefundService {

	void add(Refund refund);
	//更改退款状态
	void update(String money,String ordernum);
	
	List<Refund> selectByOpenid(String openid);
	List<Refund> selectAll();
}
