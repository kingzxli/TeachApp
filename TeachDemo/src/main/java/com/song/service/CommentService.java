package com.song.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.song.pojo.Comment;

public interface CommentService {

	void add(Comment comment);
	List<Comment> select(int tid);
	List<Comment> selectByOrder(@Param("tid")int tid,@Param("ordernum")String ordernum);
	List<Comment> selectByOrdernum(String ordernum);
}
