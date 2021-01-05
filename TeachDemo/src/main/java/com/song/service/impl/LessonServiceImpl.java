package com.song.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.song.mapper.LessonMapper;
import com.song.pojo.Lesson;
import com.song.service.LessonService;

@Service
public class LessonServiceImpl implements LessonService{

			
	@Autowired
	private LessonMapper lessonMapper;
	
	@Override
	public void insert(Lesson lesson) {
		// TODO Auto-generated method stub
		lessonMapper.insert(lesson);
	}

	@Override
	public Lesson selectById(int tid) {
		// TODO Auto-generated method stub
		return lessonMapper.selectById(tid);
	}

	@Override
	public void update(Lesson lesson) {
		// TODO Auto-generated method stub
		lessonMapper.update(lesson);
	}

}
