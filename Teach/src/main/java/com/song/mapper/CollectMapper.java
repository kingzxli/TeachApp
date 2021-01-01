package com.song.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.song.pojo.Collect;

@Mapper
public interface CollectMapper {

	void insert(@Param("tid")Integer tid,@Param("pid")Integer pid,@Param("type")String type);
	//查老师收藏
	Collect select(@Param("tid")Integer tid,@Param("pid")Integer pid);
	//查文章收藏
	Collect selectT(@Param("tid")Integer tid,@Param("pid")Integer pid);
	//删除老师类型的收藏
	void delete(@Param("tid")Integer tid,@Param("pid")Integer pid);
	//删除文本类型的收藏
	void deleteT(@Param("tid")Integer tid,@Param("pid")Integer pid);
	//查询收藏列表
	List<Collect> selectByPid(int pid);
	List<Collect> selectByid(int pid);
}
