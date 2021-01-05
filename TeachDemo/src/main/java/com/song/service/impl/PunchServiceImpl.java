package com.song.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.song.mapper.PunchMapper;
import com.song.pojo.Punch;
import com.song.service.PunchService;

@Service
public class PunchServiceImpl implements PunchService{
	
	@Autowired
	private PunchMapper punchMapper;

	@Override
	public int insert(Punch punch) {
		// TODO Auto-generated method stub
		return punchMapper.insert(punch);
	}

	@Override
	public Punch selectByOrderTime(String times, String ordernum) {
		// TODO Auto-generated method stub
		return punchMapper.selectByOrderTime(times, ordernum);
	}

	@Override
	public Punch selectByOrderTimeF(String times, String ordernum) {
		// TODO Auto-generated method stub
		return punchMapper.selectByOrderTimeF(times, ordernum);
	}

	@Override
	public void delete(String times) {
		// TODO Auto-generated method stub
		punchMapper.delete(times);
	}

	@Override
	public void update(Punch punch) {
		// TODO Auto-generated method stub
		punchMapper.update(punch);
	}

	@Override
	public Set<String> selectAll(String month, String year, String ordernum) {
		// TODO Auto-generated method stub
		return punchMapper.selectAll(month, year, ordernum);
	}

	@Override
	public List<Punch> selectTime(String openid) {
		// TODO Auto-generated method stub
		return punchMapper.selectTime(openid);
	}

	@Override
	public Punch selectById(int id) {
		// TODO Auto-generated method stub
		return punchMapper.selectById(id);
	}


}
