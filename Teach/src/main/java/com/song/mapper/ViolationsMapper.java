package com.song.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.song.pojo.Violations;

@Mapper
public interface ViolationsMapper {

	void add(Violations v);
	List<Violations> select(int tid);
	//通过时间差
	List<Violations> selectByTime(String times);
	
}
