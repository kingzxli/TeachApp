package com.song.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.song.mapper.ViolationsMapper;
import com.song.pojo.Violations;
import com.song.service.ViolationsService;

@Service
public class ViolationsServiceImpl implements ViolationsService{

	@Autowired
	private ViolationsMapper violationsMapper;
	
	@Override
	public void add(Violations v) {
		// TODO Auto-generated method stub
		violationsMapper.add(v);
	}

	@Override
	public List<Violations> select(int tid) {
		// TODO Auto-generated method stub
		return violationsMapper.select(tid);
	}

	@Override
	public List<Violations> selectByTime(String times) {
		// TODO Auto-generated method stub
		return violationsMapper.selectByTime(times);
	}
	
}
