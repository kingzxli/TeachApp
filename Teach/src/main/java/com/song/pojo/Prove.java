package com.song.pojo;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @author Song
 *
 */
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
	
	
	public String getWeChat() {
		return weChat;
	}

	public void setWeChat(String weChat) {
		this.weChat = weChat;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getStatus1() {
		return status1;
	}

	public void setStatus1(int status1) {
		this.status1 = status1;
	}

	public int getStatus2() {
		return status2;
	}

	public void setStatus2(int status2) {
		this.status2 = status2;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getGraduatenum() {
		return graduatenum;
	}

	public void setGraduatenum(String graduatenum) {
		this.graduatenum = graduatenum;
	}

	public String getGraduateimage() {
		return graduateimage;
	}

	public void setGraduateimage(String graduateimage) {
		this.graduateimage = graduateimage;
	}

	public String getTeachnum() {
		return teachnum;
	}

	public void setTeachnum(String teachnum) {
		this.teachnum = teachnum;
	}

	public String getTeachimage() {
		return teachimage;
	}

	public void setTeachimage(String teachimage) {
		this.teachimage = teachimage;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public String getNames() {
		return names;
	}
	public void setNames(String names) {
		this.names = names;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getFront() {
		return front;
	}
	public void setFront(String front) {
		this.front = front;
	}
	public String getBack() {
		return back;
	}
	public void setBack(String back) {
		this.back = back;
	}
	public String getFace() {
		return face;
	}
	public void setFace(String face) {
		this.face = face;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	@Override
	public String toString() {
		return "Prove [id=" + id + ", tid=" + tid + ", names=" + names + ", sex=" + sex + ", front=" + front + ", back="
				+ back + ", face=" + face + ", idcard=" + idcard + ", graduatenum=" + graduatenum + ", graduateimage="
				+ graduateimage + ", teachnum=" + teachnum + ", teachimage=" + teachimage + ", status=" + status
				+ ", status1=" + status1 + ", status2=" + status2 + "]";
	}
	
	

}
