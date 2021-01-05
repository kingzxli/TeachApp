package com.song.service;

import com.song.pojo.Reward;

public interface RewardService {
	
	void add (Reward reward);
	void update(Reward reward);

	
	Reward selectByOrdernum(String ordernum);
	
}
