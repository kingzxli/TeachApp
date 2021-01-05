package com.song.mapper;

import java.util.List;

import com.song.pojo.Exam;

public interface ExamMapper {

	void insert(Exam exam);
	void update(Exam exam);
	void delete(String ordernum);
	List<Exam> selectById(String ordernum);
	
}
