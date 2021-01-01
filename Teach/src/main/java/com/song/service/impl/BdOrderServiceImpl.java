package com.song.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.song.mapper.BdOrderMapper;
import com.song.pojo.BdOrder;
import com.song.service.BdOrderService;

@Service
public class BdOrderServiceImpl implements BdOrderService{

	@Autowired
	private BdOrderMapper bdorderMapper;
	
	@Override
	public void insert(BdOrder bdorder) {
		// TODO Auto-generated method stub
		bdorderMapper.insert(bdorder);
	}

	@Override
	public void update(BdOrder bdorder) {
		// TODO Auto-generated method stub
		bdorderMapper.update(bdorder);
	}

	@Override
	public BdOrder selectBytp(String tpOrderId) {
		// TODO Auto-generated method stub
		return bdorderMapper.selectBytp(tpOrderId);
	}

	@Override
	public void delete(String tpOrderId) {
		// TODO Auto-generated method stub
		bdorderMapper.delete(tpOrderId);
	}

	
	
}
