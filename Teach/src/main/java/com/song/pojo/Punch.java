package com.song.pojo;

import java.util.Arrays;
import java.util.List;

public class Punch {
	
	private Integer id;
	private String pname;
	private String textbook;
	private String one;
	private String two;
	private String three;
	private String four;
	private int expression;
	private int job;
	private int grasp;
	private int review;
	private String content;
	private String feedback;
	private String treview;
	private String late;
	private String remark;
	private String times;
	private String ordernum;
	private String five;
	private int classhour;
	private Exam[] exam;
	private List<Exam> exams;
	private Integer tid;
	
	private String types;
	private String openId;
	private String formId;
	private String popenid;
	
	
	private Integer status;
	
	
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getPopenid() {
		return popenid;
	}
	public void setPopenid(String popenid) {
		this.popenid = popenid;
	}
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	public String getFormId() {
		return formId;
	}
	public void setFormId(String formId) {
		this.formId = formId;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getTypes() {
		return types;
	}
	public void setTypes(String types) {
		this.types = types;
	}
	public List<Exam> getExams() {
		return exams;
	}
	public void setExams(List<Exam> exams) {
		this.exams = exams;
	}
	public Exam[] getExam() {
		return exam;
	}
	public void setExam(Exam[] exam) {
		this.exam = exam;
	}
	public String getFive() {
		return five;
	}
	public void setFive(String five) {
		this.five = five;
	}
	public int getClasshour() {
		return classhour;
	}
	public void setClasshour(int classhour) {
		this.classhour = classhour;
	}
	public String getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getTextbook() {
		return textbook;
	}
	public void setTextbook(String textbook) {
		this.textbook = textbook;
	}
	public String getOne() {
		return one;
	}
	public void setOne(String one) {
		this.one = one;
	}
	public String getTwo() {
		return two;
	}
	public void setTwo(String two) {
		this.two = two;
	}
	public String getThree() {
		return three;
	}
	public void setThree(String three) {
		this.three = three;
	}
	public String getFour() {
		return four;
	}
	public void setFour(String four) {
		this.four = four;
	}
	public int getExpression() {
		return expression;
	}
	public void setExpression(int expression) {
		this.expression = expression;
	}
	public int getJob() {
		return job;
	}
	public void setJob(int job) {
		this.job = job;
	}
	public int getGrasp() {
		return grasp;
	}
	public void setGrasp(int grasp) {
		this.grasp = grasp;
	}
	public int getReview() {
		return review;
	}
	public void setReview(int review) {
		this.review = review;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	public String getTreview() {
		return treview;
	}
	public void setTreview(String treview) {
		this.treview = treview;
	}
	public String getLate() {
		return late;
	}
	public void setLate(String late) {
		this.late = late;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getTimes() {
		return times;
	}
	public void setTimes(String times) {
		this.times = times;
	}
	@Override
	public String toString() {
		return "Punch [id=" + id + ", pname=" + pname + ", textbook=" + textbook + ", one=" + one + ", two=" + two
				+ ", three=" + three + ", four=" + four + ", expression=" + expression + ", job=" + job + ", grasp="
				+ grasp + ", review=" + review + ", content=" + content + ", feedback=" + feedback + ", treview="
				+ treview + ", late=" + late + ", remark=" + remark + ", times=" + times + ", ordernum=" + ordernum
				+ ", five=" + five + ", classhour=" + classhour + ", exam=" + Arrays.toString(exam) + "]";
	}
	
}
