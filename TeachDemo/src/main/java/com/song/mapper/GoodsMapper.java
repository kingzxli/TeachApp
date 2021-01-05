package com.song.mapper;

import java.util.List;

import com.song.pojo.Goods;

public interface GoodsMapper {


	List<Goods> selectAll(Goods goods);
	Goods selectById(int id);
	
	
	
	//查所有杂货
	List<Goods> select();
	//删除
	void delete(int id);
	//添加
	void add(Goods goods);
	//修改
	void update(Goods goods);
	
	
	List<Goods> selects();
}
