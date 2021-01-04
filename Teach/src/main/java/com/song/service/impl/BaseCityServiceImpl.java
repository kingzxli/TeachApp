package com.song.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.song.entity.BaseCity;
import com.song.mapper.BaseCityMapper;
import com.song.service.BaseCityService;


@Service
public class BaseCityServiceImpl implements BaseCityService{

	@Autowired
	private BaseCityMapper baseCityMapper;

	@Override
	public List<BaseCity> list(BaseCity baseCity) {	
		return baseCityMapper.selectList(baseCity);
	}
	
}
