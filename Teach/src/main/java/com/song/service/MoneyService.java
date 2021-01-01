package com.song.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.song.pojo.Money;

public interface MoneyService {
	
	void add(@Param("openid")String openid,@Param("moneys")String moneys);
	String selectAll(String shareId);

}
