package com.song.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.song.pojo.Punch;

@Mapper
public interface PunchMapper {

	int insert(Punch punch);
	//进入日历查询
	Set<String> selectAll(@Param("month")String month,@Param("year")String year,@Param("ordernum")String ordernum);
	//点击进入详情(有打卡的情况下)
	Punch selectByOrderTime(@Param("times")String times,@Param("ordernum")String ordernum);
	//点击进入详情(没有打卡的情况下)
	Punch selectByOrderTimeF(@Param("times")String times,@Param("ordernum")String ordernum);
	//删卡
	void delete(String times);
	//修改打卡
	void update(Punch punch);
	//查询该学生上课时间
	List<Punch> selectTime(String openid);
	//查详情
	Punch selectById(@Param("id")int id);
	
	//查打卡的次数
	Integer selectCount(String ordernum);
	
}
