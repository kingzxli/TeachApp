package com.song.service;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.song.pojo.Punch;

public interface PunchService {
	
	int insert(Punch punch);
	Set<String> selectAll(@Param("month")String month,@Param("year")String year,@Param("ordernum")String ordernum);
	Punch selectByOrderTime(@Param("times")String times,@Param("ordernum")String ordernum);
	Punch selectByOrderTimeF(@Param("times")String times,@Param("ordernum")String ordernum);
	void delete(String times);
	void update(Punch punch);
	//查询该学生上课时间
	List<Punch> selectTime(String openid);
	//查详情
	Punch selectById(@Param("id")int id);
	
	Integer selectCount(String ordernum);
}
