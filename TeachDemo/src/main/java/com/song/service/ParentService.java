package com.song.service;


import org.apache.ibatis.annotations.Param;

import com.song.pojo.Parent;

/**
 * 
 * @author Song
 *
 */
public interface ParentService {

	//插入数据
	int add(Parent parent);
	//通过openid查
	Parent selectByOpenid(String openid);
	//通过id查
	Parent selectByid(int tid);
	//通过id删除
	void delete(int id);
	//通过名称查openid
	String selectByName(String name);
	void updateIdentity(@Param("online")String online,@Param("openid")String openid);
	Parent selectByOnId(@Param("online")String online,@Param("id")int id);
	void updateMoney(@Param("money")Double money,@Param("id")int id);
	//查金钱
	Double selectMoney(String openid);
	
	Parent Login(Parent parent);
}
