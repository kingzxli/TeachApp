package com.song.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.song.pojo.Suggest;

@Mapper
public interface SuggestMapper {

	void add(Suggest suggest);
	
}
