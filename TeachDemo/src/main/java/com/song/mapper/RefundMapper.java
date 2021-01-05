package com.song.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.song.pojo.Refund;

public interface RefundMapper {

	void add(Refund refund);
	//更改退款状态
	void update(@Param("money")String money,@Param("ordernum")String ordernum);
	
	List<Refund> selectByOpenid(String openid);
	
	
	List<Refund> selectAll();
	
}
