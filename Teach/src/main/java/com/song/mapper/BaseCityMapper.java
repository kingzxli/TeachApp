package com.song.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.song.entity.BaseCity;


@Mapper
public interface BaseCityMapper {

	List<BaseCity> selectList(BaseCity baseCity);

}
