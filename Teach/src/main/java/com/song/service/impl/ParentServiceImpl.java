package com.song.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.song.mapper.ParentMapper;
import com.song.pojo.Parent;
import com.song.service.ParentService;

/**
 * 
 * @author Song
 *
 */
@Service
public class ParentServiceImpl implements ParentService{

	@Autowired
	private ParentMapper parentMapper;

	@Override
	public int add(Parent parent) {
		// TODO Auto-generated method stub
		return parentMapper.add(parent);
	}

	@Override
	public Parent selectByOpenid(String openid) {
		// TODO Auto-generated method stub
		return parentMapper.selectByOpenid(openid);
	}

	@Override
	public Parent selectByid(int tid) {
		// TODO Auto-generated method stub
		return parentMapper.selectByid(tid);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		parentMapper.delete(id);
	}

	@Override
	public String selectByName(String name) {
		// TODO Auto-generated method stub
		return parentMapper.selectByName(name);
	}

	@Override
	public void updateIdentity(String online, String openid) {
		// TODO Auto-generated method stub
		parentMapper.updateIdentity(online, openid);
	}

	@Override
	public Parent selectByOnId(String online, int id) {
		// TODO Auto-generated method stub
		return parentMapper.selectByOnId(online, id);
	}

	@Override
	public void updateMoney(Double money, int id) {
		// TODO Auto-generated method stub
		parentMapper.updateMoney(money, id);
	}

	@Override
	public Double selectMoney(String openid) {
		// TODO Auto-generated method stub
		return parentMapper.selectMoney(openid);
	}

}
