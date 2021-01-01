package com.song.service;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.song.pojo.Exam;
import com.song.pojo.Punch;

public interface ExamService {
	
	void insert(Exam exam);
	void update(Exam exam);
	void delete(String ordernum);
	List<Exam> selectById(String ordernum);
}
