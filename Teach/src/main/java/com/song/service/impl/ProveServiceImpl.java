package com.song.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.song.mapper.ProveMapper;
import com.song.pojo.Prove;
import com.song.service.ProveService;

@Service
public class ProveServiceImpl implements ProveService{
	
	@Autowired
	private ProveMapper proveMapper;

	@Override
	public int add(Prove prove) {
		// TODO Auto-generated method stub
		return proveMapper.add(prove);
	}

	@Override
	public void update(Prove prove) {
		// TODO Auto-generated method stub
		proveMapper.update(prove); 
	}

	@Override
	public Prove selectByIdcard(int tid) {
		// TODO Auto-generated method stub
		return proveMapper.selectByIdcard(tid);
	}

	@Override
	public Prove selectByTeach(int tid) {
		// TODO Auto-generated method stub
		return proveMapper.selectByTeach(tid);
	}

	@Override
	public Prove selectByStudent(int tid) {
		// TODO Auto-generated method stub
		return proveMapper.selectByStudent(tid);
	}

	@Override
	public void updateStatus(int tid) {
		// TODO Auto-generated method stub
		proveMapper.updateStatus(tid);
	}

	@Override
	public void updateStatus1(int tid) {
		// TODO Auto-generated method stub
		proveMapper.updateStatus1(tid);
	}

	@Override
	public void updateStatus2(int tid) {
		// TODO Auto-generated method stub
		proveMapper.updateStatus2(tid);
	}

}
