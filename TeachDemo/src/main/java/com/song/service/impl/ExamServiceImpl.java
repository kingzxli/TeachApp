package com.song.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.song.mapper.ExamMapper;
import com.song.pojo.Exam;
import com.song.service.ExamService;

@Service
public class ExamServiceImpl implements ExamService{

	@Autowired
	private ExamMapper examMapper;

	@Override
	public void insert(Exam exam) {
		// TODO Auto-generated method stub
		examMapper.insert(exam);
	}

	@Override
	public void update(Exam exam) {
		// TODO Auto-generated method stub
		examMapper.update(exam);
	}

	@Override
	public void delete(String ordernum) {
		// TODO Auto-generated method stub
		examMapper.delete(ordernum);
	}

	@Override
	public List<Exam> selectById(String ordernum) {
		// TODO Auto-generated method stub
		return examMapper.selectById(ordernum);
	}
	

}
