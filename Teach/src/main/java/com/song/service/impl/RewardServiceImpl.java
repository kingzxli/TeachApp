package com.song.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.song.mapper.RewardMapper;
import com.song.pojo.Reward;
import com.song.service.RewardService;

@Service
public class RewardServiceImpl implements RewardService{

	@Autowired
	private RewardMapper rewardMapper;
	
	@Override
	public void add(Reward reward) {
		// TODO Auto-generated method stub
		rewardMapper.add(reward);
	}

	@Override
	public void update(Reward reward) {
		// TODO Auto-generated method stub
		rewardMapper.update(reward);
	}

	@Override
	public Reward selectByOrdernum(String ordernum) {
		// TODO Auto-generated method stub
		return rewardMapper.selectByOrdernum(ordernum);
	}

}
