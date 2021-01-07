package com.song.pojo;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class SelectTeaVo {
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
	private int stuid;
	private String goclass;
	private String school;
	private String addtime;
	private Integer pstatus;
	private String popenid;
	private String teachingType;
	private String latlng;
	private String latitude;
	private BigDecimal distance;
	private List<Teacher> teacher;
	private String level;

}
