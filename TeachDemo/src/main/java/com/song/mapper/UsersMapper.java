package com.song.mapper;

import com.song.pojo.Users;

public interface UsersMapper {
	
	Users Login(Users u);
	void add(Users user);

}
