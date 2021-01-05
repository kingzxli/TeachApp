package com.song.mapper;

import java.util.List;

import com.song.pojo.Suggest;

public interface SuggestMapper {

	void add(Suggest suggest);
	
	//后台查询
	List<Suggest> selectAll();
	
}
