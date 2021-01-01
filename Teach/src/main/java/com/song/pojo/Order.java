package com.song.pojo;

import java.util.List;

public class Order {

	private Integer id ;
	private String addtime;//添加时间 
	private String openId;//openid
	private String totalfee;//应付款
	private String body;//年级科目 
	private String times; //上课时间
	private Integer tid;//老师id
	private String addr;//上课地址
	private String ordernum; //订单号
	private String price;//课程单价
	private String num;//课次课时
	private String totalnum;//总课时
	private String totalprice;//总价
	private String types;
	private String status;//订单状态
	private String comment;//是否评论
	private String timestatus;//订单完成状态
	private String username;
	private String tel;
	private String popenid;
	private String orderpar;
	private int isbd;
	
	private String image;
	private String name;
	
	private List<Parent> parent;
	private List<Teacher> teacher;
	
	
	
	public int getIsbd() {
		return isbd;
	}
	public void setIsbd(int isbd) {
		this.isbd = isbd;
	}
	public String getOrderpar() {
		return orderpar;
	}
	public void setOrderpar(String orderpar) {
		this.orderpar = orderpar;
	}
	public String getTotalfee() {
		return totalfee;
	}
	public void setTotalfee(String totalfee) {
		this.totalfee = totalfee;
	}
	public String getPopenid() {
		return popenid;
	}
	public void setPopenid(String popenid) {
		this.popenid = popenid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getTimestatus() {
		return timestatus;
	}
	public void setTimestatus(String timestatus) {
		this.timestatus = timestatus;
	}
	public List<Parent> getParent() {
		return parent;
	}
	public void setParent(List<Parent> parent) {
		this.parent = parent;
	}
	public List<Teacher> getTeacher() {
		return teacher;
	}
	public void setTeacher(List<Teacher> teacher) {
		this.teacher = teacher;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTypes() {
		return types;
	}
	public void setTypes(String types) {
		this.types = types;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getTotalnum() {
		return totalnum;
	}
	public void setTotalnum(String totalnum) {
		this.totalnum = totalnum;
	}
	public String getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(String totalprice) {
		this.totalprice = totalprice;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getTimes() {
		return times;
	}
	public void setTimes(String times) {
		this.times = times;
	}
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", addtime=" + addtime + ", openId=" + openId + ", totalfee=" + totalfee + ", body="
				+ body + ", times=" + times + ", tid=" + tid + ", addr=" + addr + ", ordernum=" + ordernum + ", price="
				+ price + ", num=" + num + ", totalnum=" + totalnum + ", totalprice=" + totalprice + ", types=" + types
				+ ", status=" + status + ", comment=" + comment + ", timestatus=" + timestatus + ", username="
				+ username + ", tel=" + tel + ", popenid=" + popenid + ", image=" + image + ", name=" + name
				+ ", parent=" + parent + ", teacher=" + teacher + "]";
	}
	
}
