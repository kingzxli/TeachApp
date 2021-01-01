package com.song.pojo;

public class BaiDuMsg {

	private Integer errno;
	private String msg;
	private Object data;
	private Integer code;
	private Object errmsg;
	
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public Object getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(Object errmsg) {
		this.errmsg = errmsg;
	}
	public Integer getErrno() {
		return errno;
	}
	public void setErrno(Integer errno) {
		this.errno = errno;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	
	
}
