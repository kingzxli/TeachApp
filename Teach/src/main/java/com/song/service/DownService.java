package com.song.service;

import java.util.List;

import com.song.pojo.Down;

public interface DownService {

	void add(Down down);
	List<Down> selectAll(String openid); 
	
}
