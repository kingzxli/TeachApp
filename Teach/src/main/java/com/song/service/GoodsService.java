package com.song.service;

import java.util.List;

import com.song.pojo.Goods;

public interface GoodsService {

	List<Goods> selectAll(Goods goods);
	Goods selectById(int id);
	
	
	
	
	List<Goods> select();
	void delete(int id);
	void add(Goods goods);
	void update(Goods goods);
}
