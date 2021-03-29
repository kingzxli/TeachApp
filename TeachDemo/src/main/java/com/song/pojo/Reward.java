package com.song.pojo;

import lombok.Data;

@Data
public class Reward {
	
	private int id;
	private String openid;//当前人的openid
	private String money;
	private String shareId;//推荐人的id
	private String status;
	private String ordernum;
	
}
