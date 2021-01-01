package com.song.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.song.pojo.Lesson;

@Mapper
public interface LessonMapper {
	
	//添加授课时间
	void insert(Lesson lesson);
	//通过用户id查数据
	Lesson selectById(int tid);
	//修改授课时间
	void update(Lesson lesson);

}
