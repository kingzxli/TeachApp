package com.song.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.song.pojo.Parent;

@Mapper
public interface ParentMapper {

	//插入数据
	int add(Parent parent);
	//通过openid查
	Parent selectByOpenid(String openid);
	//通过id查
	Parent selectByid(String openId);
	//通过id删除
	void delete(int id);
	//通过名称查openid
	String selectByName(String name);
	//修改当前身份identity
	void updateIdentity(@Param("online")String online,@Param("openid")String openid);
	//查当前学生
	Parent selectByOnId(@Param("online")String online,@Param("id")int id);
	//修改金钱
	void updateMoney(@Param("money")Double money,@Param("id")int id);
	//查金钱
	Double selectMoney(String openid);
	
	void updateById(Parent parent);
	
}
