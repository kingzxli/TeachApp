package com.song.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.song.pojo.Down;

@Mapper
public interface DownMapper {
	
	void add(Down down);
	List<Down> selectAll(String openid); 
	
}
