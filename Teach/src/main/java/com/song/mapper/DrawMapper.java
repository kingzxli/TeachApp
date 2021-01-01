package com.song.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.song.pojo.Draw;

@Mapper
public interface DrawMapper {
	
	//添加提现记录
	void add(Draw draw);
	//查提现记录
	List<Draw> selectByOpenid(String openid);
	
}
