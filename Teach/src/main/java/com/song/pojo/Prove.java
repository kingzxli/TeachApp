package com.song.pojo;

import java.io.Serializable;

import lombok.Data;


/**
 * 
 * @author Song
 *
 */
@Data
public class Prove implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private int tid;
	private String names;
	private String sex;
	private String front;
	private String back;
	private String face;
	private String idcard;
	private String graduatenum;
	private String graduateimage;
	private String teachnum;
	private String teachimage;
	private String phone;
	private int status;
	private int status1;
	private int status2;
	private String weChat;
	private Integer activate;
	

}
