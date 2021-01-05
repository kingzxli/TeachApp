package com.song.service;

import com.song.pojo.Lesson;

public interface LessonService {
	
	//添加授课时间
	void insert(Lesson lesson);
	//通过用户id查数据
	Lesson selectById(int tid);
	//修改授课时间
	void update(Lesson lesson);

}
