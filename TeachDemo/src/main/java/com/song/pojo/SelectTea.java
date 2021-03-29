package com.song.pojo;

import java.util.Date;
import lombok.Data;

/**
 * 
 * @author Song
 *
 */
@Data
public class SelectTea {
	
	private String id;
	//授课地址
	private String address;
	//老师性别
	private String teasex;
	//科目
	private String subject;
	//家长电话
	private String phone;
	//状态
	private Integer pstatus;
	//授课方式
	private String teachingType;
	//家长姓名
	private String name ;
	
	private String parentName ;
	
	private String detailed;
	private String otherrequire;
	private String stusituation;
	private String tearequire;
	private String stusex;

	private String stumessage;
	private String culture;
	private String grade;

	private String teatype;
	private String trial;

	private int stuid;
	private String goclass;
	private String school;
	private String addtime;

	private Integer delstatus;

	private String popenid;
	
	private String sysCreatedBy;
	private Date sysCreated;
	private String way;

	
	
	
	
}
