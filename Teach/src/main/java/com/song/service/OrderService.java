package com.song.service;


import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.song.pojo.Order;
import com.song.pojo.Teacher;

public interface OrderService {

	List<Order> selctByPopenid(String popenid);
	//通过openid查订单
	List<Order> selectByOpenid(String openid);
	//插入数据
	void insert(Order order);
	//修改订单状态
	void updateStatus(@Param("status")String status,@Param("ordernum")String ordernum);
	//通过订单号查询订单
	Order selectByOrderNum(String ordernum);
	//返回待支付订单
	List<Order> selectByStatus(String openid);
	//返回所有订单
	List<Order> selectAll(String openid);
	//通过订单号查询订单详情
	Order selectByOrder(String ordernum);
	//返回未评价的订单
	List<Order> selectByComment(String openid);
	//修改订单评论状态
	void updateComment(String ordernum);
	//查询已经评论
	List<Order> selectByComment2(String openid);
	//通过老师查学生
	Set<Order> selectParByTea(int tid);
	//通过学生查老师
	Set<Teacher> selectTeaByPar(String openid);
	//家长删订单
	void delete(int id);
	//通过老师id查进行中订单
	List<Order> selectbytid(int tid);
	
	void updateTimeStatus(@Param("timestatus")String timestatus,@Param("ordernum")String ordernum);
	void deleteByOrdernum(String ordernum);
	
	
	List<Order> selectByBdAll(Order order);
}

