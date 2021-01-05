package com.song.pojo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * @author Song
 *
 */
//@JsonInclude(value = Include.NON_NULL)
public class Teacher implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private int activate;
	private String money;
	private String sex;
	private String name;
	private Integer price;
	private String openid;
	private String school;
	private String image;
	private String seniority;
	private String education;
	private String location;
	private String level;
	private String number;
	private String character;
	private String phone;
	private String type;
	private String latlng;
	private String[] chara;
	private String types;
	private String duration;
	private String project;
	private String otherpro;
	private String begin;
	private String end;
	private String profession;
	private String title;
	private String titleimage;
	private String honor;
	private String honorimage;
	private String study;
	private String sbegin;
	private String send;
	private String scontent;
	private String suctitle;
	private String sucontent;
	private String photo;
	private Integer jifen;
	private String online;
	private String grade;
	
	private List<Parent> parent;
	private List<SelectTea> st;
	
	
	private String front;
	private String back;
	private String face;
	private String idcard;
	private String graduatenum;
	private String graduateimage;
	private String teachnum;
	private String teachimage;
	private String names;
	private String weChat;
	
	private String other;
	private String otherpri;
	private String otherscontent;
	
	private String lessontime;
	
	
	
	public String getWeChat() {
		return weChat;
	}
	public void setWeChat(String weChat) {
		this.weChat = weChat;
	}
	public String getLessontime() {
		return lessontime;
	}
	public void setLessontime(String lessontime) {
		this.lessontime = lessontime;
	}
	public String getOtherscontent() {
		return otherscontent;
	}
	public void setOtherscontent(String otherscontent) {
		this.otherscontent = otherscontent;
	}
	public String getOtherpri() {
		return otherpri;
	}
	public void setOtherpri(String otherpri) {
		this.otherpri = otherpri;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	public String getNames() {
		return names;
	}
	public void setNames(String names) {
		this.names = names;
	}
	public String getFront() {
		return front;
	}
	public void setFront(String front) {
		this.front = front;
	}
	public String getBack() {
		return back;
	}
	public void setBack(String back) {
		this.back = back;
	}
	public String getFace() {
		return face;
	}
	public void setFace(String face) {
		this.face = face;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getGraduatenum() {
		return graduatenum;
	}
	public void setGraduatenum(String graduatenum) {
		this.graduatenum = graduatenum;
	}
	public String getGraduateimage() {
		return graduateimage;
	}
	public void setGraduateimage(String graduateimage) {
		this.graduateimage = graduateimage;
	}
	public String getTeachnum() {
		return teachnum;
	}
	public void setTeachnum(String teachnum) {
		this.teachnum = teachnum;
	}
	public String getTeachimage() {
		return teachimage;
	}
	public void setTeachimage(String teachimage) {
		this.teachimage = teachimage;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public List<SelectTea> getSt() {
		return st;
	}
	public void setSt(List<SelectTea> st) {
		this.st = st;
	}
	public String getOnline() {
		return online;
	}
	public void setOnline(String online) {
		this.online = online;
	}
	public Integer getJifen() {
		return jifen;
	}
	public void setJifen(Integer jifen) {
		this.jifen = jifen;
	}
	public List<Parent> getParent() {
		return parent;
	}
	public void setParent(List<Parent> parent) {
		this.parent = parent;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getSbegin() {
		return sbegin;
	}
	public void setSbegin(String sbegin) {
		this.sbegin = sbegin;
	}
	public String getSend() {
		return send;
	}
	public void setSend(String send) {
		this.send = send;
	}
	public String getScontent() {
		return scontent;
	}
	public void setScontent(String scontent) {
		this.scontent = scontent;
	}
	public String getSuctitle() {
		return suctitle;
	}
	public void setSuctitle(String suctitle) {
		this.suctitle = suctitle;
	}
	public String getSucontent() {
		return sucontent;
	}
	public void setSucontent(String sucontent) {
		this.sucontent = sucontent;
	}
	public String getStudy() {
		return study;
	}
	public void setStudy(String study) {
		this.study = study;
	}
	public String getHonor() {
		return honor;
	}
	public void setHonor(String honor) {
		this.honor = honor;
	}
	public String getHonorimage() {
		return honorimage;
	}
	public void setHonorimage(String honorimage) {
		this.honorimage = honorimage;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitleimage() {
		return titleimage;
	}
	public void setTitleimage(String titleimage) {
		this.titleimage = titleimage;
	}
	public String getBegin() {
		return begin;
	}
	public void setBegin(String begin) {
		this.begin = begin;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public String getOtherpro() {
		return otherpro;
	}
	public void setOtherpro(String otherpro) {
		this.otherpro = otherpro;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getTypes() {
		return types;
	}
	public void setTypes(String types) {
		this.types = types;
	}
	public String[] getChara() {
		return chara;
	}
	public void setChara(String[] chara) {
		this.chara = chara;
	}
	public String getCharacter() {
		return character;
	}
	public void setCharacter(String character) {
		this.character = character;
	}
	public String getLatlng() {
		return latlng;
	}
	public void setLatlng(String latlng) {
		this.latlng = latlng;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getActivate() {
		return activate;
	}
	public void setActivate(int activate) {
		this.activate = activate;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getSeniority() {
		return seniority;
	}
	public void setSeniority(String seniority) {
		this.seniority = seniority;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "Teacher [id=" + id + ", activate=" + activate + ", money=" + money + ", sex=" + sex + ", name=" + name
				+ ", price=" + price + ", openid=" + openid + ", school=" + school + ", image=" + image + ", seniority="
				+ seniority + ", education=" + education + ", location=" + location + ", level=" + level + ", number="
				+ number + ", character=" + character + ", phone=" + phone + ", type=" + type + ", latlng=" + latlng
				+ ", chara=" + Arrays.toString(chara) + ", types=" + types + ", duration=" + duration + ", project="
				+ project + ", otherpro=" + otherpro + ", begin=" + begin + ", end=" + end + ", profession="
				+ profession + ", title=" + title + ", titleimage=" + titleimage + ", honor=" + honor + ", honorimage="
				+ honorimage + ", study=" + study + ", sbegin=" + sbegin + ", send=" + send + ", scontent=" + scontent
				+ ", suctitle=" + suctitle + ", sucontent=" + sucontent + ", photo=" + photo + ", jifen=" + jifen
				+ ", online=" + online + ", grade=" + grade + ", parent=" + parent + ", st=" + st + ", front=" + front
				+ ", back=" + back + ", face=" + face + ", idcard=" + idcard + ", graduatenum=" + graduatenum
				+ ", graduateimage=" + graduateimage + ", teachnum=" + teachnum + ", teachimage=" + teachimage
				+ ", names=" + names + ", other=" + other + ", otherpri=" + otherpri + "]";
	}
}
