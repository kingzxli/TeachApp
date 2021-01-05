package com.song.service;

import java.util.List;

import com.song.pojo.Suggest;

public interface SuggestService {

	void add(Suggest suggest);
	
	
	List<Suggest> selectAll();
}
