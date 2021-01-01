package com.song.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.song.mapper.GoodsMapper;
import com.song.pojo.Goods;
import com.song.service.GoodsService;

@Service
public class GoodsServiceImpl implements GoodsService{

	@Autowired
	private GoodsMapper goodsMapper;

	@Override
	public List<Goods> selectAll(Goods goods) {
		// TODO Auto-generated method stub
		return goodsMapper.selectAll(goods);
	}

	@Override
	public Goods selectById(int id) {
		// TODO Auto-generated method stub
		return goodsMapper.selectById(id);
	}

	@Override
	public List<Goods> select() {
		// TODO Auto-generated method stub
		return goodsMapper.select();
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		goodsMapper.delete(id);
	}

	@Override
	public void add(Goods goods) {
		// TODO Auto-generated method stub
		goodsMapper.add(goods);
	}

	@Override
	public void update(Goods goods) {
		// TODO Auto-generated method stub
		goodsMapper.update(goods);
	}

	

}
