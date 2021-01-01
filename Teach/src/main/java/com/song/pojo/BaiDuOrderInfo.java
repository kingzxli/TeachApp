package com.song.pojo;

import com.alibaba.fastjson.JSONArray;

public class BaiDuOrderInfo {
	
	private String dealId;
    private String appKey;
    private String totalAmount;
    private String tpOrderId;
    private String dealTitle;
    private String signFieldsRange;
    private String rsaSign;
    private String bizInfo;
	public String getDealId() {
		return dealId;
	}
	public void setDealId(String dealId) {
		this.dealId = dealId;
	}
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getTpOrderId() {
		return tpOrderId;
	}
	public void setTpOrderId(String tpOrderId) {
		this.tpOrderId = tpOrderId;
	}
	public String getDealTitle() {
		return dealTitle;
	}
	public void setDealTitle(String dealTitle) {
		this.dealTitle = dealTitle;
	}
	public String getSignFieldsRange() {
		return signFieldsRange;
	}
	public void setSignFieldsRange(String signFieldsRange) {
		this.signFieldsRange = signFieldsRange;
	}
	public String getRsaSign() {
		return rsaSign;
	}
	public void setRsaSign(String rsaSign) {
		this.rsaSign = rsaSign;
	}
	public String getBizInfo() {
		return bizInfo;
	}
	public void setBizInfo(String bizInfo) {
		this.bizInfo = bizInfo;
	}
    
	
	//这里写一个方法，提供传入bizInfo对象来给this.bizInfo的值，当然也可以直接使用set方法赋值
    public void setBaiDuBizInfo(BaiDuBizInfo bizInfo) {
    	Object obj = JSONArray.toJSON(bizInfo);
        String json = obj.toString();
        StringBuffer buffer = new StringBuffer("{\"tpData\":");
        buffer.append(json);
        buffer.append("}");
        this.setBizInfo(buffer.toString());
    }
}
