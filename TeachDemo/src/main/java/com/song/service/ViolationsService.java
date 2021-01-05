package com.song.service;

import java.util.List;

import com.song.pojo.Violations;

public interface ViolationsService {
	
	void add(Violations v);
	List<Violations> select(int tid);
	
}
