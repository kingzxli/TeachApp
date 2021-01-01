package com.song.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.song.mapper.ShufflingMapper;
import com.song.pojo.Shuffling;
import com.song.service.ShufflingService;

/**
 * 
 * @author Song
 *
 */
@Service
public class ShufflingServiceImpl implements ShufflingService{

	@Autowired
	private ShufflingMapper shufflingMapper;
	
	@Override
	public List<Shuffling> selectByType(int type) {
		// TODO Auto-generated method stub
		return shufflingMapper.selectByType(type);
	}

	@Override
	public Shuffling selectByid(int id) {
		// TODO Auto-generated method stub
		return shufflingMapper.selectByid(id);
	}

	@Override
	public void add(Shuffling shuffling) {
		// TODO Auto-generated method stub
		shufflingMapper.add(shuffling);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		shufflingMapper.delete(id);
	}

	@Override
	public void update(Shuffling shuffling) {
		// TODO Auto-generated method stub
		shufflingMapper.update(shuffling);
	}

	@Override
	public List<Shuffling> selectAll() {
		// TODO Auto-generated method stub
		return shufflingMapper.selectAll();
	}

}
