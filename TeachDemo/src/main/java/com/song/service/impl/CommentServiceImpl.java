package com.song.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.song.mapper.CommentMapper;
import com.song.pojo.Comment;
import com.song.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private CommentMapper commentMapper;
	
	@Override
	public void add(Comment comment) {
		// TODO Auto-generated method stub
		commentMapper.add(comment);
	}

	@Override
	public List<Comment> select(int tid) {
		// TODO Auto-generated method stub
		return commentMapper.select(tid);
	}

	@Override
	public List<Comment> selectByOrder(int tid, String ordernum) {
		// TODO Auto-generated method stub
		return commentMapper.selectByOrder(tid, ordernum);
	}

	@Override
	public List<Comment> selectByOrdernum(String ordernum) {
		// TODO Auto-generated method stub
		return commentMapper.selectByOrdernum(ordernum);
	}

}
