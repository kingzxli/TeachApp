package com.song.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface MoneyMapper {

	void add(@Param("openid")String openid,@Param("moneys")String moneys);
	
	//查金钱
	String selectAll(String shareId);
	
}
