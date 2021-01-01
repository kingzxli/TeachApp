package com.song.pojo;

public class BaiDuSyncOrderStatus {

	private String appKey;
	private String OrderId;
	private String userId;
	private String type;
	private String method;
	private String rsaSign;
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public String getOrderId() {
		return OrderId;
	}
	public void setOrderId(String orderId) {
		OrderId = orderId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getRsaSign() {
		return rsaSign;
	}
	public void setRsaSign(String rsaSign) {
		this.rsaSign = rsaSign;
	}
	
	
	
}
