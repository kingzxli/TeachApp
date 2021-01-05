package com.song.mapper;

import java.util.List;

import com.song.pojo.Shuffflings;

public interface ShufflingsMapper {

	List<Shuffflings> select(int type);
	
}
