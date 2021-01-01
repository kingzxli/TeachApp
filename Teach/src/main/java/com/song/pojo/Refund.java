package com.song.pojo;

import java.util.List;

public class Refund {
	
	private Integer id;
	private String openid;
	private String ordernum;
	private String status;
	private String money;
	private Order order;
	private Parent parent;
	
	
	
	public Parent getParent() {
		return parent;
	}
	public void setParent(Parent parent) {
		this.parent = parent;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}
	@Override
	public String toString() {
		return "Refund [id=" + id + ", openid=" + openid + ", ordernum=" + ordernum + ", status=" + status + ", money="
				+ money + ", order=" + order + ", parent=" + parent + "]";
	}
	
}
