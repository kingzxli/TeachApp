package com.song.pojo;

import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 
 * @author Song
 *
 */
@Data
public class SelectTea {
	
	private String id;
	private String address;
	private String detailed;
	private String otherrequire;
	private String stusituation;
	private String tearequire;
	private String stusex;
	private String teasex;
	private String stumessage;
	private String culture;
	private String grade;
	private String subject;
	private String teatype;
	private String trial;
	private String phone;
	private Integer stuid;
	private String goclass;
	private String school;
	private String addtime;
	private Integer pstatus;
	private String popenid;
	private String teachingType;
	private String latlng;
	private String latitude;
	private String sysCreatedBy;
	private Date sysCreated;
	/**
	 * 了解我们的途径
	 */
	private String way;
	
	private List<Teacher> teacher;
	
}
