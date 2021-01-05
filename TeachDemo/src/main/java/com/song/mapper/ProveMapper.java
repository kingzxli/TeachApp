package com.song.mapper;

import org.apache.ibatis.annotations.Param;

import com.song.pojo.Prove;

/**
 * 
 * @author Song
 *
 */
public interface ProveMapper {
	
	//添加认证信息
	int add(Prove prove);
	//添加教师认证和学生认证
	void update(Prove prove);
	//根据type跟用户id查询
	Prove selectByIdcard(int tid);
	Prove selectByTeach(int tid);
	Prove selectByStudent(int tid);
	void updateStatus(int tid);
	void updateStatus1(int tid);
	void updateStatus2(int tid);
	void delete(int tid);
	
	
	Prove selectByTid(int tid);
}
