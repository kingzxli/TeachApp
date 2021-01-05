package com.song.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author Song
 *
 */
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String avatarUrl;
	private String nickName;
	private String gender;
	private String openid;
	private String sessionkey;
	private String phone;
	
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAvatarUrl() {
		return avatarUrl;
	}
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getSessionkey() {
		return sessionkey;
	}
	public void setSessionkey(String sessionkey) {
		this.sessionkey = sessionkey;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", avatarUrl=" + avatarUrl + ", nickName=" + nickName + ", gender=" + gender
				+ ", openid=" + openid + ", sessionkey=" + sessionkey + ", phone=" + phone + "]";
	}
	
	
}
