package com.song.service;

import java.util.List;

import com.song.pojo.Shuffling;

/**
 * 
 * @author Song
 *
 */
public interface ShufflingService {

	//通过type获取广告
	List<Shuffling> selectByType(int type);
	Shuffling selectByid(int id);
	
	void add(Shuffling shuffling);
	void delete(Integer id);
	void update(Shuffling shuffling);
	List<Shuffling> selectAll();
}
