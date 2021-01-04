package com.song.entity;

import java.io.Serializable;
import lombok.Data;

@Data
public class BaseCity implements Serializable{
	private static final long serialVersionUID = 1L;

	private String id;
	
	// "上级id"
	private String parentId;
	
	// "区域名称"
	private String cityName;
	
	//"级别(0:国,1:省,2:市,3区县)"
	private Integer cityLevel;
	
}
