package com.song.pojo;

import java.util.Arrays;

/**
 * 
 * @author Song
 *
 */
public class Lesson {

	private int id;
	private String times;
	private int tid;
	private String state;
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTimes() {
		return times;
	}
	public void setTimes(String times) {
		this.times = times;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	@Override
	public String toString() {
		return "Lesson [id=" + id + ", times=" + times + ", tid=" + tid + ", state=" + state + "]";
	}
}
