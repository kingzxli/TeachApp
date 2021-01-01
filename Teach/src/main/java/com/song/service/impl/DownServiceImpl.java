package com.song.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.song.mapper.DownMapper;
import com.song.pojo.Down;
import com.song.service.DownService;

@Service
public class DownServiceImpl implements DownService{
	
	@Autowired
	private DownMapper downMapper;
	

	@Override
	public void add(Down down) {
		// TODO Auto-generated method stub
		downMapper.add(down);
	}


	@Override
	public List<Down> selectAll(String openid) {
		// TODO Auto-generated method stub
		return downMapper.selectAll(openid);
	}
	
}
