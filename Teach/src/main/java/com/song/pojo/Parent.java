package com.song.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Parent implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String sex;
	private String image;
	private String location;
	private String name;
	private String phone;
	private String openid;
	private String latlng;
	private String online;
	private Double money;
	private String unionid;
	private Date createTime;
	private Date modifiedTime;
	
	private List<Teacher> teacher;
	
	private String grade;
	private String subject;
	private String address;
	private String detailed;
	private String goclass;
	private String trial;
	
}
