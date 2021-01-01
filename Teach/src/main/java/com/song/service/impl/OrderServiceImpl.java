package com.song.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.song.mapper.OrderMapper;
import com.song.pojo.Order;
import com.song.pojo.Teacher;
import com.song.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderMapper orderMapper;
	
	@Override
	public void insert(Order order) {
		// TODO Auto-generated method stub
		orderMapper.insert(order);
	}

	@Override
	public Order selectByOrderNum(String ordernum) {
		// TODO Auto-generated method stub
		return orderMapper.selectByOrderNum(ordernum);
	}

	@Override
	public void updateStatus(String status, String ordernum) {
		// TODO Auto-generated method stub
		orderMapper.updateStatus(status, ordernum);
	}

	@Override
	public List<Order> selectByStatus(String openid) {
		// TODO Auto-generated method stub
		return orderMapper.selectByStatus(openid);
	}

	@Override
	public List<Order> selectAll(String openid) {
		// TODO Auto-generated method stub
		return orderMapper.selectAll(openid);
	}

	@Override
	public Order selectByOrder(String ordernum) {
		// TODO Auto-generated method stub
		return orderMapper.selectByOrder(ordernum);
	}

	@Override
	public List<Order> selectByComment(String openid) {
		// TODO Auto-generated method stub
		return orderMapper.selectByComment(openid);
	}

	@Override
	public void updateComment(String ordernum) {
		// TODO Auto-generated method stub
		orderMapper.updateComment(ordernum);
	}

	@Override
	public List<Order> selectByComment2(String openid) {
		// TODO Auto-generated method stub
		return orderMapper.selectByComment2(openid);
	}

	@Override
	public Set<Order> selectParByTea(int tid) {
		// TODO Auto-generated method stub
		return orderMapper.selectParByTea(tid);
	}

	@Override
	public Set<Teacher> selectTeaByPar(String openid) {
		// TODO Auto-generated method stub
		return orderMapper.selectTeaByPar(openid);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		orderMapper.delete(id);
	}

	@Override
	public List<Order> selectbytid(int tid) {
		// TODO Auto-generated method stub
		return orderMapper.selectbytid(tid);
	}

	@Override
	public void updateTimeStatus(String timestatus, String ordernum) {
		// TODO Auto-generated method stub
		orderMapper.updateTimeStatus(timestatus, ordernum);
	}

	@Override
	public List<Order> selectByOpenid(String openid) {
		// TODO Auto-generated method stub
		return orderMapper.selectByOpenid(openid);
	}

	@Override
	public List<Order> selctByPopenid(String popenid) {
		// TODO Auto-generated method stub
		return orderMapper.selctByPopenid(popenid);
	}

	@Override
	public void deleteByOrdernum(String ordernum) {
		// TODO Auto-generated method stub
		orderMapper.deleteByOrdernum(ordernum);
	}

	@Override
	public List<Order> selectByBdAll(Order order) {
		// TODO Auto-generated method stub
		return orderMapper.selectByBdAll(order);
	}

}
