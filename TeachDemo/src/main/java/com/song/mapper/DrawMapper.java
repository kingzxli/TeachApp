package com.song.mapper;

import java.util.List;

import com.song.pojo.Draw;

public interface DrawMapper {
	
	//添加提现记录
	void add(Draw draw);
	//查提现记录
	List<Draw> selectByOpenid(String openid);
	
	//后台返回提现申请记录
	List<Draw> selectAll();
	
}
