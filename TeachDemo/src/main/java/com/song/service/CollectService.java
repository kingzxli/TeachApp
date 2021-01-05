package com.song.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.song.pojo.Collect;
import com.song.pojo.Goods;

public interface CollectService {

	void insert(@Param("tid")Integer tid,@Param("pid")Integer pid,@Param("type")String type);
	Collect select(@Param("tid")Integer tid,@Param("pid")Integer pid);
	Collect selectT(@Param("tid")Integer tid,@Param("pid")Integer pid);
	void delete(@Param("tid")Integer tid,@Param("pid")Integer pid);
	void deleteT(@Param("tid")Integer tid,@Param("pid")Integer pid);
	List<Collect> selectByPid(int pid);
	List<Collect> selectByid(int pid);
}
