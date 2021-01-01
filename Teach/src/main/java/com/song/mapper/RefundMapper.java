package com.song.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.song.pojo.Refund;

@Mapper
public interface RefundMapper {

	void add(Refund refund);
	//更改退款状态
	void update(String money,String ordernum);
	
	List<Refund> selectByOpenid(String openid);
	
	
	List<Refund> selectAll();
	
}
