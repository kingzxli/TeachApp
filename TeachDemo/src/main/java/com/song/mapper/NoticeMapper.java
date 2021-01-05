package com.song.mapper;

import java.util.List;

import com.song.pojo.Down;
import com.song.pojo.Notice;

public interface NoticeMapper {
	
	List<Notice> selectAll();
	void delete(int id);
	void insert(Notice notice);
	void update(Notice notice);
	
}
