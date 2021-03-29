package com.song.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.song.pojo.SelectTea;
import com.song.pojo.TeaSelect;

public interface SelectTeaService {

	//添加找老师数据
	void add(SelectTea selectTea);
	//查询生源接单列表
	List<SelectTea> selectByType(@Param("tea_type")String tea_type,@Param("address")String address,@Param("project")String project,@Param("tea_sex")String tea_sex);
	//查询所有
	List<SelectTea> selectAll();
	//根据学生id查 
	List<SelectTea> selectById(int id);
	//删除
	void delete(String id);
	//通过数据id查详情
	SelectTea selectId(int id);
	
	void addTeaSel(@Param("topenid")String topenid,@Param("sid")int sid,@Param("addtime")String addtime,@Param("tid")Integer tid);
	List<TeaSelect> selectByPid(Integer pid); 
	List<TeaSelect> selectByTopenid(String openid);
	void updateStatus(@Param("id")String id,@Param("status")int status);
	void updatePstatus(@Param("id")String id,@Param("status")int status);
	
	TeaSelect selectStuta(int sid,String openid);
	
	List<SelectTea> selectall(SelectTea selectTea);
	List<SelectTea> selectBystatus(String sid);
}
