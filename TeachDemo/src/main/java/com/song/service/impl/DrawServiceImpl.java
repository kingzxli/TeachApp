package com.song.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.song.mapper.DrawMapper;
import com.song.pojo.Draw;
import com.song.service.DrawService;


@Service
public class DrawServiceImpl implements DrawService{

	@Autowired
	private DrawMapper drawMapper;
	
	@Override
	public void add(Draw draw) {
		// TODO Auto-generated method stub
		drawMapper.add(draw);
	}

	@Override
	public List<Draw> selectByOpenid(String openid) {
		// TODO Auto-generated method stub
		return drawMapper.selectByOpenid(openid);
	}

	@Override
	public List<Draw> selectAll() {
		// TODO Auto-generated method stub
		return drawMapper.selectAll();
	}
	
	

}
