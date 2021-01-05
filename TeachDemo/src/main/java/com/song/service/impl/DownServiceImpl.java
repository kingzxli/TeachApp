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
	public List<Down> select() {
		// TODO Auto-generated method stub
		return downMapper.select();
	}


}
