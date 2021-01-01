package com.song.pojo;

import java.util.List;
import lombok.Data;

/**
 * 
 * @author Song
 *
 */
@Data
public class Teacher{

	/**
	 * 
	 */
	//老师id
	private Integer id;
	private int activate;
	private String money;
	//性别
	private String sex;
	private String name;
	private Integer price;
	private String openid;
	private String school;
	private String image;
	private String seniority;
	private String education;
	//区域
	private String location;
	private String level;
	private String number;
	private String character;
	private String phone;
	private String type;
	private String latlng;
	private String[] chara;
	private String types;
	private String duration;
	//科目
	private String project;
	private String otherpro;
	private String begin;
	private String end;
	private String profession;
	private String title;
	private String titleimage;
	private String honor;
	private String honorimage;
	private String study;
	private String sbegin;
	private String send;
	private String scontent;
	private String suctitle;
	private String sucontent;
	private String photo;
	private Integer jifen;
	private String online;
	private String grade;
	private String unionid;
	
	private List<Parent> parent;
	private List<SelectTea> st;
	private String otherpri;
	private String otherscontent;
	
	private String names;
	private String front;
	private String back;
	private String face;
	private String idcard;
	private String graduatenum;
	private String graduateimage;
	private String teachnum;
	private String teachimage;		
	/**
	 * 经度
	 */
	private String longitude;
	/**
	 * 纬度
	 */
	private String latitude;
}
