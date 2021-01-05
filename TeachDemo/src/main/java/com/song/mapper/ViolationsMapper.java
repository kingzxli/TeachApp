package com.song.mapper;

import java.util.List;

import com.song.pojo.Violations;

public interface ViolationsMapper {

	void add(Violations v);
	List<Violations> select(int tid);
	
}
