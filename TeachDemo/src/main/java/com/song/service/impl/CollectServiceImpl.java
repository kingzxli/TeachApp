package com.song.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.song.mapper.CollectMapper;
import com.song.pojo.Collect;
import com.song.pojo.Goods;
import com.song.service.CollectService;

@Service
public class CollectServiceImpl implements CollectService{

	@Autowired
	private CollectMapper collectMapper;
	
	@Override
	public Collect select(Integer tid, Integer pid) {
		// TODO Auto-generated method stub
		return collectMapper.select(tid, pid);
	}

	@Override
	public void delete(Integer tid, Integer pid) {
		// TODO Auto-generated method stub
		collectMapper.delete(tid, pid);
	}

	@Override
	public List<Collect> selectByPid(int pid) {
		// TODO Auto-generated method stub
		return collectMapper.selectByPid(pid);
	}

	@Override
	public void insert(Integer tid, Integer pid, String type) {
		// TODO Auto-generated method stub
		collectMapper.insert(tid, pid, type);
	}

	@Override
	public List<Collect> selectByid(int pid) {
		// TODO Auto-generated method stub
		return collectMapper.selectByid(pid);
	}

	@Override
	public Collect selectT(Integer tid, Integer pid) {
		// TODO Auto-generated method stub
		return collectMapper.selectT(tid, pid);
	}

	@Override
	public void deleteT(Integer tid, Integer pid) {
		// TODO Auto-generated method stub
		collectMapper.deleteT(tid, pid);
	}



}
