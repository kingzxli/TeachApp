package com.song.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.song.pojo.Kechou;

@Mapper
public interface KechouMapper {

	List<Kechou> selectById(int tid);
	
}
