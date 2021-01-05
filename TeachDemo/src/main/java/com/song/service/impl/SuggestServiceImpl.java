package com.song.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.song.mapper.SuggestMapper;
import com.song.pojo.Suggest;
import com.song.service.SuggestService;

@Service
public class SuggestServiceImpl implements SuggestService{

	@Autowired
	private SuggestMapper suggestMapper;
	
	@Override
	public void add(Suggest suggest) {
		// TODO Auto-generated method stub
		suggestMapper.add(suggest);
	}

	@Override
	public List<Suggest> selectAll() {
		// TODO Auto-generated method stub
		return suggestMapper.selectAll();
	}
	
}
