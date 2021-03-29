package com.song.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.song.pojo.Teacher;

@Mapper
public interface TeacherMapper {

	//插入数据
	int add(Teacher teacher);
	//通过openid查
	Teacher selectByOpenid(String openid);
	//通过id查一个老师
	Teacher selectById(@Param("id")Integer id,@Param("openid")String openid);
	//通过id删除
	void delete(int id);
	//查询所有
	List<Teacher> selectAll(@Param("grade")String grade,@Param("project")String project,@Param("sex")String sex,@Param("types")String types,@Param("address")String address);
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
	List<Teacher> selectByCondition(@Param("project")String project);
	//修改课时
	void updateNumber(@Param("number")String number,@Param("id")int id);
	//更改老师类型
	void updatetypes(@Param("level")String level,@Param("types")String types,@Param("openid")String openid);
	//修改老师等级
	void updateLevel(@Param("openid")String openid,@Param("level")String level);
	//修改老师价格
	void updatePrice(@Param("price")int price,@Param("openid")String openid);
	void updateActivate(@Param("activate")int activate,@Param("id")int id);
	//更改地理经纬度
	void updateLatlng(@Param("latlng")String latlng,@Param("openid")String openid);
	//通过科目查询审核已经通过
	List<Teacher> selectByProject(@Param("project")String project);
	
	String selectOther(int id);
	
	Teacher selectById2(Teacher teacher);
	
	List<String> selectByProjectCity(@Param("city")String city,@Param("area")String area);
	
	void updateLocation(Teacher teacher);
	
	void updateInfo(Teacher teacher);
}
