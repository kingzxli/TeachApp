package com.song.service;

import java.util.List;

import com.song.pojo.Draw;

public interface DrawService {
	
	void add(Draw draw);
	List<Draw> selectByOpenid(String openid);
}
