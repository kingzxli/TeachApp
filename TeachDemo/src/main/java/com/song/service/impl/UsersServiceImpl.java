package com.song.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.song.mapper.UsersMapper;
import com.song.pojo.Users;
import com.song.service.UsersService;

@Service
public class UsersServiceImpl implements UsersService{
	
	@Autowired
	private UsersMapper usersMapper;

	@Override
	public Users Login(Users u) {
		// TODO Auto-generated method stub
		return usersMapper.Login(u);
	}

	@Override
	public void add(Users user) {
		// TODO Auto-generated method stub
		usersMapper.add(user);
	}

}
