package com.song.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.song.pojo.Shuffling;

/**
 * 
 * @author Song
 *
 */
@Mapper
public interface ShufflingMapper {
	
	//通过type获取广告
	List<Shuffling> selectByType(int type);
	//点击进入详情
	Shuffling selectByid(int id);
	
	
	
	
	
	//添加广告
	void add(Shuffling shuffling);
	//删除广告
	void delete(Integer id);
	//修改广告
	void update(Shuffling shuffling);
	//查所有
	List<Shuffling> selectAll();
}
