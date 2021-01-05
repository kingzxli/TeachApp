package com.song.service;

import java.util.List;

import com.song.pojo.Shuffflings;

public interface ShufflingsService {
	
	List<Shuffflings> select(int type);

}
