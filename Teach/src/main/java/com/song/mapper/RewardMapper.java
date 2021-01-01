package com.song.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.song.pojo.Reward;

@Mapper
public interface RewardMapper {

	void add (Reward reward);
	void update(Reward reward);
	
	Reward selectByOrdernum(String ordernum);
	
}
