package com.song.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.song.pojo.Parent;
import com.song.pojo.SelectTea;
import com.song.pojo.SelectTeaVo;
import com.song.pojo.TeaSelect;
import com.song.pojo.Teacher;

@Mapper
public interface SelectTeaMapper{

	//添加找老师数据
	void add(SelectTea selectTea);
	//查询生源接单列表
	List<SelectTea> selectByType(@Param("tea_type")String tea_type,@Param("address")String address,@Param("project")String project,@Param("tea_sex")String tea_sex);
	//查询所有
	List<SelectTea> selectAll(@Param("tea_type")String tea_type,@Param("address")String address,@Param("project")String project,@Param("tea_sex")String tea_sex);
	//根据学生id查 
	List<SelectTea> selectById(int id);
	//删除
	void delete(String id);
	//通过数据id查详情
	SelectTea selectId(String id);
	
	//老师接单
	void addTeaSel(@Param("topenid")String topenid,@Param("sid")int sid,@Param("addtime")String addtime,@Param("tid")Integer tid,@Param("pid")Integer pid);
	
	//通过sid查所有的单
	List<String> selectBySid(@Param("sid") Integer sid,@Param("id") Integer id);
	
	//家长看接单的老师
	List<TeaSelect> selectByPid(Integer pid); 
	
	//老师查他所接的单
	List<TeaSelect> selectByTopenid(String openid);
	
	//修改老师接单状态
	void updateStatus(@Param("id")String id,@Param("status")int status);
	//修改家长接单状态
	void updatePstatus(@Param("id")String id,@Param("status")int status);
	
	//点击生源接单查看是否接过单
	List<TeaSelect> selectStuta(@Param("sid")String sid,@Param("openid")String openid);
	
	//查生源我的老师
	Set<Teacher> SelectTea(Integer pid);
	//查生源我的学生
	Set<Parent> SelectStu(Integer tid);
	//根据sid查生源信息
	SelectTea selectBySids(Integer sid);
	
	
	
	//老师取消接单
	void deleteSt(@Param("id")int id,@Param("tid")int tid);
	List<TeaSelect> selectSt(int id);
	
	List<SelectTea> selectAllDate();
	
	void updateById(SelectTea selectTea);
	
	List<SelectTeaVo> selectByType2(SelectTeaVo selectTea);
	
	SelectTea selectBySid2(Integer sid);
	
	void updateStatusNo(Integer id);
	
}
