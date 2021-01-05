package com.song.service;

import com.song.pojo.Users;

public interface UsersService {
	
	Users Login(Users u);
	void add(Users user);

}
