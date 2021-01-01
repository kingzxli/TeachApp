package com.song.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.song.mapper.KechouMapper;
import com.song.pojo.Kechou;
import com.song.service.KechouService;


@Service
public class KechouServiceImpl implements KechouService{
	
	@Autowired
	private KechouMapper kechouMapper;

	@Override
	public List<Kechou> selectById(int tid) {
		// TODO Auto-generated method stub
		return kechouMapper.selectById(tid);
	}

}
