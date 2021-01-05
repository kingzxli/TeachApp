package com.song.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.song.mapper.DownMapper;
import com.song.mapper.NoticeMapper;
import com.song.pojo.Down;
import com.song.pojo.Notice;
import com.song.service.DownService;
import com.song.service.NoticeService;

@Service
public class NoticeServiceImpl implements NoticeService{
	
	@Autowired
	private NoticeMapper noticeMapper;

	@Override
	public List<Notice> selectAll() {
		// TODO Auto-generated method stub
		return noticeMapper.selectAll();
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		noticeMapper.delete(id);
	}

	@Override
	public void insert(Notice notice) {
		// TODO Auto-generated method stub
		noticeMapper.insert(notice);
	}

	@Override
	public void update(Notice notice) {
		// TODO Auto-generated method stub
		noticeMapper.update(notice);
	}


}
