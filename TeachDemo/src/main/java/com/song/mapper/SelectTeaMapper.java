package com.song.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.song.pojo.SelectTea;
import com.song.pojo.TeaSelect;

public interface SelectTeaMapper {

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
	
	//老师接单
	void addTeaSel(@Param("topenid")String topenid,@Param("sid")int sid,@Param("addtime")String addtime,@Param("tid")Integer tid);
	
	//家长看接单的老师
	List<TeaSelect> selectByPid(Integer pid); 
	
	//老师查他所接的单
	List<TeaSelect> selectByTopenid(String openid);
	
	//修改老师接单状态
	void updateStatus(@Param("id")String id,@Param("status")Integer status);
	//修改家长接单状态
	void updatePstatus(@Param("id")String id,@Param("status")Integer status);
	
	//点击生源接单查看是否接过单
	TeaSelect selectStuta(@Param("sid")int sid,@Param("openid")String openid);
	
	
	//后台查询生源接单
	List<SelectTea> selectall(SelectTea selectTea);

	//后台查看接单情况
	List<SelectTea> selectBystatus(String sid);
}
