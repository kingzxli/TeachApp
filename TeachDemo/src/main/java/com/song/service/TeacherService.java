package com.song.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.song.pojo.Shuffling;
import com.song.pojo.Teacher;

/**
 * 
 * @author Song
 *
 */
public interface TeacherService {

	//插入数据
	int add(Teacher teacher);
	//通过openid查
	Teacher selectByOpenid(String openid);
	//通过id查
	Teacher selectById(@Param("id")int id,@Param("openid")String openid);
	//通过id删除
	void delete(int id);
	//查询所有
	List<Teacher> selectAll(@Param("grade")String grade,@Param("project")String project,@Param("sex")String sex,@Param("types")String types);
	//修改老师资料
	void update(Teacher teacher);
	//修改老师院校
	void updateschool(Teacher teacher);
	//修改老师职称
	void updatetitle(Teacher teacher);
	//修改老师荣誉
	void updatehonor(Teacher teacher);
	//修改老师教学经历
	void updatexperience(Teacher teacher);
	//修改老师成功案例
	void updatesuccess(Teacher teacher);
	//修改个人标签
	void updatecharacter(Teacher teacher);
	//老师生活照
	void updatephoto(Teacher teacher);
	///首页老师查询
	List<Teacher> selectindex();
	void updateJifen(@Param("jifen")int jifen,@Param("id")int id);
	void updateIdentity(@Param("online")String online,@Param("openid")String openid);
	void updateOnId(@Param("money")String money,@Param("openid")String openid);
	Teacher selectByOnId(@Param("online")String online,@Param("id")int id);
	Double selectMoney(String openid);
	List<Teacher> selectByCondition(String project);
	void updateNumber(@Param("number")String number,@Param("id")int id);
	
	
	List<Teacher> selectByZero(Teacher teacher);
	void updateActivate(@Param("price")Integer price,@Param("otherpri")String otherpri,@Param("activate")Integer activate,@Param("id")Integer id);
	List<Teacher> selectByNamePro(Teacher teacher);
	void updateProve(@Param("status")int status,@Param("tid")int tid);
	void updateOther(@Param("other")String other,@Param("tid")int tid);
	List<Teacher> selectByActivate(Teacher teacher);
}
