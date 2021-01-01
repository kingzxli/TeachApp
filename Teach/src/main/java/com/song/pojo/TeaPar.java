package com.song.pojo;

import java.util.List;

public class TeaPar {

	private int id;
	private String openid;
	private int tid;
	
	private List<Teacher> teacher;
	private List<Parent> parent;
	
	public List<Teacher> getTeacher() {
		return teacher;
	}
	public void setTeacher(List<Teacher> teacher) {
		this.teacher = teacher;
	}
	public List<Parent> getParent() {
		return parent;
	}
	public void setParent(List<Parent> parent) {
		this.parent = parent;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	
}
