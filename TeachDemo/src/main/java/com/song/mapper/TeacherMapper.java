package com.song.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.song.pojo.Teacher;

public interface TeacherMapper {

	//插入数据
	int add(Teacher teacher);
	//通过openid查
	Teacher selectByOpenid(String openid);
	//通过id查一个老师
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
	//修改积分
	void updateJifen(@Param("jifen")int jifen,@Param("id")int id);
	//修改是否当前身份identity
	void updateIdentity(@Param("online")String online,@Param("openid")String openid);
	//修改推荐人的金钱
	void updateOnId(@Param("money")String money,@Param("openid")String openid);
	//查是否当前身份
	Teacher selectByOnId(@Param("online")String online,@Param("id")int id);
	//查金钱
	Double selectMoney(String openid);
	//首页搜索框
	List<Teacher> selectByCondition(String project);
	//修改课时
	void updateNumber(@Param("number")String number,@Param("id")int id);
	
	//查询申请的老师
	List<Teacher> selectByZero(Teacher teacher);
	//同意老师申请
	void updateActivate(@Param("price")Integer price,@Param("otherpri")String otherpri,@Param("activate")Integer activate,@Param("id")Integer id);
	//后台老师条件查询
	List<Teacher> selectByNamePro(Teacher teacher);
	//更改prove表status
	void updateProve(@Param("status")int status,@Param("tid")int tid);
	//修改拒绝老师的消息
	void updateOther(@Param("other")String other,@Param("tid")int tid);
	
	List<Teacher> selectByActivate(Teacher teacher);
	
}
