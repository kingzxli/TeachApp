package com.song.pojo;


public class BdOrder {
	
	private String userId;
	private String orderId;
	private String unitPrice;
	private String count;
	private String totalMoney;
	private String payMoney;
	private String promoMoney;
	private String hbMoney;
	private String hbBalanceMoney;
	private String giftCardMoney;
	private String dealId;
	private String payTime;
	private String promoDetail;
	private String payType;
	private String partnerId;
	private String status;
	private String tpOrderId;
	private String returnData;
	private String rsaSign;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(String totalMoney) {
		this.totalMoney = totalMoney;
	}

	public String getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(String payMoney) {
		this.payMoney = payMoney;
	}

	public String getPromoMoney() {
		return promoMoney;
	}

	public void setPromoMoney(String promoMoney) {
		this.promoMoney = promoMoney;
	}

	public String getHbMoney() {
		return hbMoney;
	}

	public void setHbMoney(String hbMoney) {
		this.hbMoney = hbMoney;
	}

	public String getHbBalanceMoney() {
		return hbBalanceMoney;
	}

	public void setHbBalanceMoney(String hbBalanceMoney) {
		this.hbBalanceMoney = hbBalanceMoney;
	}

	public String getGiftCardMoney() {
		return giftCardMoney;
	}

	public void setGiftCardMoney(String giftCardMoney) {
		this.giftCardMoney = giftCardMoney;
	}

	public String getDealId() {
		return dealId;
	}

	public void setDealId(String dealId) {
		this.dealId = dealId;
	}

	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	public String getPromoDetail() {
		return promoDetail;
	}

	public void setPromoDetail(String promoDetail) {
		this.promoDetail = promoDetail;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTpOrderId() {
		return tpOrderId;
	}

	public void setTpOrderId(String tpOrderId) {
		this.tpOrderId = tpOrderId;
	}

	public String getReturnData() {
		return returnData;
	}

	public void setReturnData(String returnData) {
		this.returnData = returnData;
	}

	public String getRsaSign() {
		return rsaSign;
	}

	public void setRsaSign(String rsaSign) {
		this.rsaSign = rsaSign;
	}

	@Override
	public String toString() {
		return "BdOrder [userId=" + userId + ", orderId=" + orderId + ", unitPrice=" + unitPrice + ", count=" + count
				+ ", totalMoney=" + totalMoney + ", payMoney=" + payMoney + ", promoMoney=" + promoMoney + ", hbMoney="
				+ hbMoney + ", hbBalanceMoney=" + hbBalanceMoney + ", giftCardMoney=" + giftCardMoney + ", dealId="
				+ dealId + ", payTime=" + payTime + ", promoDetail=" + promoDetail + ", payType=" + payType
				+ ", partnerId=" + partnerId + ", status=" + status + ", tpOrderId=" + tpOrderId + ", returnData="
				+ returnData + ", rsaSign=" + rsaSign + "]";
	}
	
}
