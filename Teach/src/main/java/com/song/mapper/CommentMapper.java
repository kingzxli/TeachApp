package com.song.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.song.pojo.Comment;

@Mapper
public interface CommentMapper {
	//添加评论
	void add(Comment comment);
	//通过老师id查询
	List<Comment> select(int tid);
	//通过老师id和订单号查询
	List<Comment> selectByOrder(@Param("tid")int tid,@Param("ordernum")String ordernum);
	//通过订单号查询
	List<Comment> selectByOrdernum(String ordernum);
}
