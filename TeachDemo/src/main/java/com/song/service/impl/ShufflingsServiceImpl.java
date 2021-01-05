package com.song.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.song.mapper.ShufflingsMapper;
import com.song.pojo.Shuffflings;
import com.song.service.ShufflingsService;

@Service
public class ShufflingsServiceImpl implements ShufflingsService{

	@Autowired
	private ShufflingsMapper shufflingsMapper;
	
	@Override
	public List<Shuffflings> select(int type) {
		// TODO Auto-generated method stub
		return shufflingsMapper.select(type);
	}

}
