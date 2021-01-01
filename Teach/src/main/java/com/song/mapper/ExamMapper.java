package com.song.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.song.pojo.Exam;

@Mapper
public interface ExamMapper {

	void insert(Exam exam);
	void update(Exam exam);
	void delete(String ordernum);
	List<Exam> selectById(String ordernum);
	
}
