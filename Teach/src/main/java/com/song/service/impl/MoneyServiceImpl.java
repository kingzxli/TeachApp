package com.song.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.song.mapper.MoneyMapper;
import com.song.pojo.Money;
import com.song.service.MoneyService;

@Service
public class MoneyServiceImpl implements MoneyService{

	@Autowired
	private MoneyMapper moneyMapper;

	@Override
	public void add(String openid, String moneys) {
		// TODO Auto-generated method stub
		moneyMapper.add(openid, moneys);
	}

	@Override
	public String selectAll(String shareId) {
		// TODO Auto-generated method stub
		return moneyMapper.selectAll(shareId);
	}
	

}
