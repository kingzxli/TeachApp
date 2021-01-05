package com.song.mapper;

import com.song.pojo.Reward;

public interface RewardMapper {

	void add (Reward reward);
	void update(Reward reward);
	
	Reward selectByOrdernum(String ordernum);
	
}
