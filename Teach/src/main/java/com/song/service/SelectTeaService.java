package com.song.service;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.song.entity.Page;
import com.song.pojo.Parent;
import com.song.pojo.SelectTea;
import com.song.pojo.SelectTeaVo;
import com.song.pojo.TeaSelect;
import com.song.pojo.Teacher;

public interface SelectTeaService {

	//添加找老师数据
	void add(SelectTea selectTea);
	//查询生源接单列表
	List<SelectTea> selectByType(@Param("tea_type")String tea_type,@Param("address")String address,@Param("project")String project,@Param("tea_sex")String tea_sex);
	//查询所有
	List<SelectTea> selectAll(@Param("tea_type")String tea_type,@Param("address")String address,@Param("project")String project,@Param("tea_sex")String tea_sex);
	//根据学生id查 
	List<SelectTea> selectById(int id);
	//删除
	void delete(int id);
	//通过数据id查详情
	SelectTea selectId(int id);
	
	void addTeaSel(@Param("topenid")String topenid,@Param("sid")int sid,@Param("tid")Integer tid,@Param("pid")Integer pid);
	
	List<TeaSelect> selectByPid(Integer pid); 
	List<TeaSelect> selectByTopenid(String openid);
	void updateStatus(int id,int status);
	void updatePstatus(@Param("id")int id,@Param("status")int status);
	
	List<TeaSelect> selectStuta(int sid,String openid);
	
	List<String> selectBySid(@Param("sid") Integer sid,@Param("id") Integer id);
	
	Set<Teacher> SelectTea(Integer pid);
	Set<Parent> SelectStu(Integer tid);
	
	void selectBySids(Integer id,Integer status,Integer sid,String topenid,String name);
	
	void deleteSt(@Param("id")int id,@Param("tid")int tid);
	List<TeaSelect> selectSt(int id);
	
	List<SelectTea> selectAllDate();
	
	void updateById(SelectTea selectTea);
	
	List<SelectTeaVo> selectByType2(Page page,Teacher teacher);
	
	void updateStatusNo(Integer id);
	
	void updateDateNew();
	
}
