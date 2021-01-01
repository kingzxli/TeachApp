package com.song.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.song.pojo.BdOrder;

@Mapper
public interface BdOrderMapper {

	void insert(BdOrder bdorder);
	void update(BdOrder bdorder);
	BdOrder selectBytp(String tpOrderId);
	void delete(String tpOrderId);
	
}
