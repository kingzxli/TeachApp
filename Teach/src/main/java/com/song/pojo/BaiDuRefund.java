package com.song.pojo;

public class BaiDuRefund {

	private String method;
	private String orderId;
	private String userId;
	private Integer refundType;
	private String refundReason;
	private String tpOrderId;
	private String appKey;
	private String rsaSign;
	
	
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getRefundType() {
		return refundType;
	}
	public void setRefundType(Integer refundType) {
		this.refundType = refundType;
	}
	public String getRefundReason() {
		return refundReason;
	}
	public void setRefundReason(String refundReason) {
		this.refundReason = refundReason;
	}
	public String getTpOrderId() {
		return tpOrderId;
	}
	public void setTpOrderId(String tpOrderId) {
		this.tpOrderId = tpOrderId;
	}
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	
	public String getRsaSign() {
		return rsaSign;
	}
	public void setRsaSign(String rsaSign) {
		this.rsaSign = rsaSign;
	}
	@Override
	public String toString() {
		return "BaiDuRefund [method=" + method + ", orderId=" + orderId + ", userId=" + userId + ", refundType="
				+ refundType + ", refundReason=" + refundReason + ", tpOrderId=" + tpOrderId + ", appKey=" + appKey
				+ "]";
	}
	
	
}
