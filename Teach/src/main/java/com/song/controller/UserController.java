package com.song.controller;

import java.security.spec.AlgorithmParameterSpec;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONObject;
import com.song.pojo.Coupons;
import com.song.pojo.Lesson;
import com.song.pojo.Parent;
import com.song.pojo.Prove;
import com.song.pojo.Teacher;
import com.song.pojo.User;
import com.song.service.CouponService;
import com.song.service.LessonService;
import com.song.service.MoneyService;
import com.song.service.ParentService;
import com.song.service.ProveService;
import com.song.service.TeacherService;
import com.song.util.HttpClientUtil;
import com.song.util.JsonResult;

/**
 * 
 * @author Song
 *
 */

@RestController
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private ParentService parentService;
	@Autowired
	private LessonService lessonService;
	@Autowired
	private CouponService couponService;
	@Autowired
	private MoneyService moneyService;
	@Autowired
	private ProveService proveService;
	
	/*
	 * 百度登陆
	 */
	@PostMapping("bdLogin")
	public JsonResult dbLogin(User u,String code) {
		String url = "https://spapi.baidu.com/oauth/jscode2sessionkey";
		Map<String, String> map = new HashMap<String, String>();
		map.put("code", code);
		map.put("client_id", "GNCUnYeQg0LBSLWRNUMhtEkOL9PNBbAe");
		map.put("sk", "WgPYixkY2IYXXuOHKY17nH6HNAwLfyq1");
		
		String result = HttpClientUtil.doPost(url, map);
		JSONObject jsonObject = JSONObject.parseObject(result);
		System.out.println(jsonObject);
		//拿到sessionkey和openid
		String sessionkey = jsonObject.get("session_key").toString();
		String openid = jsonObject.get("openid").toString();
		Parent p = parentService.selectByOpenid(openid);
		if(p!=null) {
			Map<Object,Object> m = new HashMap<Object, Object>();
			m.put("id", p.getId());
			m.put("openid", openid);
			m.put("identity", 1);
			return JsonResult.ok(m);
		}else {
			Map<Object,Object> m = new HashMap<Object, Object>();
			m.put("openid", openid);
			m.put("identity", 1);
			return JsonResult.ok(m);
		}
	}

	
	/**
	 *  百度插入数据
	 */
	@PostMapping("bdinsert")
	public JsonResult bdinsert(HttpServletRequest request,String latlng,String location) {
		ServletContext application = request.getServletContext();
		User user = (User)application.getAttribute("user");
		//家长
		Parent parent = new Parent();
		parent.setSex(user.getGender());
		parent.setName(user.getNickName());
		parent.setImage(user.getAvatarUrl());
		parent.setOpenid(user.getOpenid());
		parent.setPhone(user.getPhone());
		parent.setLocation(location);
		parent.setLatlng(latlng);
		parentService.add(parent);
		Map<Object,Object> map = new HashMap<Object, Object>();
		map.put("id", parent.getId());
		map.put("type", 1);
		return JsonResult.ok(map);
	}
	
	
	
	
	
	/**
	 * 授权登陆
	 */
	@PostMapping("login")
	public JsonResult login(HttpServletRequest request,User user,String code,String encryptedData,String iv,int types)throws Exception{
		ServletContext application = request.getServletContext();
		application.setAttribute("typess", types);
		
		
		System.out.println(types);
		String url = "https://api.weixin.qq.com/sns/jscode2session";
		Map<String, String> map = new HashMap<String, String>();
		map.put("appid", "wxf020b9146ae3ec37");
		map.put("secret", "450823f32e6c0864a51a2524bb5ddb3d");
		map.put("js_code", code);
		map.put("grant_type", "authorization_code");
		
		//请求微信官方
		String wxResult = HttpClientUtil.doPost(url, map);
		JSONObject jsonObject = JSONObject.parseObject(wxResult);
		System.out.println(jsonObject);
		//拿到sessionkey和openid
		String sessionkey = jsonObject.get("session_key").toString();
		String openid = jsonObject.get("openid").toString();
//		String unionid = jsonObject.get("unionid").toString();
		try {
			Teacher t = teacherService.selectByOpenid(openid);
			Parent p = parentService.selectByOpenid(openid);
			if(p!=null) {
				Map<Object,Object> m = new HashMap<Object, Object>();
				m.put("id", p.getId());
				m.put("type", 1);
				m.put("openid", openid);
				return JsonResult.ok(m);
			}else if(t!=null) {
				Map<Object,Object> m = new HashMap<Object, Object>();
				m.put("id", t.getId());
				m.put("type", 2);
				m.put("openid", openid);
				return JsonResult.ok(m);
			}else if(t==null && p==null) {
				String parameter = request.getParameter("user");
				JSONObject jsonObject1 = JSONObject.parseObject(parameter);
				String nickName = jsonObject1.get("nickName").toString();
				String gender = jsonObject1.get("gender").toString();
				String avatarUrl = jsonObject1.get("avatarUrl").toString();
				
				user.setAvatarUrl(avatarUrl);
				user.setNickName(nickName);
				user.setGender(gender);
				user.setSessionkey(sessionkey);
				user.setOpenid(openid);
//				user.setUnionid(unionid);
								
				application.setAttribute("user", user);
				application.setAttribute("sessionkey", sessionkey);
				
				return JsonResult.ok(user);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return JsonResult.errorMsg("服务器出错");
	}
	
	
	/**
	 * 获取定位
	 */
	@GetMapping("getpoint")
	public JsonResult getPoint(HttpServletRequest request,String location) {
		String url = "https://apis.map.qq.com/ws/geocoder/v1/";
		System.out.println(location);
		Map<String, String> map = new HashMap<String, String>();
		map.put("location", location);
		map.put("key", "BJFBZ-GUTE6-MBVSJ-M2SOK-43WT5-JFFYQ");
//		map.put("get_poi", "1");
		String result = HttpClientUtil.doGet(url, map);
		JSONObject jsonObject = JSONObject.parseObject(result);
		return JsonResult.ok(jsonObject);
	}
	
	
	/**
	 * 获取手机号码
	 */
	@PostMapping("getphone")
	public JsonResult getPhone(HttpServletRequest request,String encryptedData,String iv) {
		ServletContext application = request.getServletContext();
		Object sessionkey = application.getAttribute("sessionkey");
		User user = (User)application.getAttribute("user");
		System.out.println(sessionkey);
		byte[] encrypData = Base64.decodeBase64(encryptedData);
		byte[] ivData = Base64.decodeBase64(iv);
		byte[] sessionKey = Base64.decodeBase64(sessionkey.toString());
		try {
			String decrypt = decrypt(sessionKey,ivData,encrypData);
			JSONObject jsonObject = JSONObject.parseObject(decrypt);
			String phone = (String)jsonObject.get("phoneNumber");
			//user.setPhone("");
			user.setPhone(phone);
			System.out.println(user);
			application.setAttribute("user", user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.ok();
	}
	public static String decrypt(byte[] key, byte[] iv, byte[] encData) throws Exception {
		AlgorithmParameterSpec ivSpec = new IvParameterSpec(iv);
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
		cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
		//解析解密后的字符串
		return new String(cipher.doFinal(encData),"UTF-8");
	}
	
	
	
	/**
	 * 根据type区别身份插入表中
	 */
	@PostMapping("adduser")
	public JsonResult add(HttpServletRequest request,int type,String latlng,String location,String types) {
		String now = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	    Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR,1);
        Date time = calendar.getTime();
		Lesson lesson = new Lesson();
		Prove prove = new Prove();
		ServletContext application = request.getServletContext();
		User user = (User)application.getAttribute("user");
		int typess = (int) application.getAttribute("typess");
		if(type==1) {
			//家长
			if(typess==1) {
//				新人获得优惠券插入优惠券表
        		Coupons c = new Coupons();
        		for(int i=0;i<10;i++) {
        			c.setOpenid(user.getOpenid());
        			c.setPrice(50);
        			c.setTypes("满减");
        			c.setStartime(now);
        			c.setEndtime(new SimpleDateFormat("yyyy-MM-dd").format(time));
        			c.setStatus("1");
        			couponService.add(c);
        		}
//        		Reward r = new Reward();
        		//插入奖励表
//        		r.setShareId(shareid);
//        		r.setStatus("0");
//        		r.setOpenid(user.getOpenid());
//        		rewardService.add(r);
			}
			
			Parent parent = new Parent();
			parent.setSex(user.getGender());
			parent.setName(user.getNickName());
			parent.setImage(user.getAvatarUrl());
			parent.setOpenid(user.getOpenid());
			parent.setPhone(user.getPhone());
			parent.setLocation(location);
			parent.setLatlng(latlng);
			
			Parent p  = parentService.selectByOpenid(user.getOpenid());
			if(p == null) {
				parent.setCreateTime(new Date());
				parentService.add(parent);
				
				Parent p0  = parentService.selectByOpenid(user.getOpenid());
				parent.setId(p0.getId());
			}else {
				parent.setId(p.getId());
				parent.setModifiedTime(new Date());
				parentService.updateById(parent);
			}
						
			Map<Object,Object> map = new HashMap<Object, Object>();
			map.put("id", parent.getId());
			map.put("type", 1);
			return JsonResult.ok(map);
		}else if(type==2) {
			//老师
			Teacher dbTeacher = teacherService.selectByOpenid(user.getOpenid());
			Teacher teacher = new Teacher();
			teacher.setName(user.getNickName());
			teacher.setPhone(user.getPhone());
			teacher.setImage(user.getAvatarUrl());
			teacher.setOpenid(user.getOpenid());
			teacher.setSex(user.getGender());
			teacher.setLocation(location);
			teacher.setLatlng(latlng);
			teacher.setTypes(types);
			
			if(types.equals("大学生")) {
				teacher.setLevel("培优大学生老师");
			}else {
				teacher.setLevel("培优老师");
			}

			if(dbTeacher == null) {
				teacher.setCreateTime(new Date());
				teacherService.add(teacher);
			}else {
				teacher.setId(dbTeacher.getId());
				teacher.setModifiedTime(new Date());
				teacherService.update(teacher);
			}


			Teacher t = teacherService.selectByOpenid(user.getOpenid());
			lesson.setTid(t.getId());
			lesson.setState("true");
			lesson.setTimes("[{\"week\":\"周六\",\"time1\":\"19:00\",\"time2\":\"21:00\"}]");
			lessonService.insert(lesson);
			prove.setTid(t.getId());
			proveService.add(prove);
			Map<Object,Object> map = new HashMap<Object, Object>();
			map.put("id", t.getId());
			map.put("type", 2);
			return JsonResult.ok(map);
		}
		return JsonResult.errorMsg("添加失败");
	}
	
	
	/**
	 * 切换身份
	 */
	@SuppressWarnings("unused")
	@PostMapping("switchid")
	public JsonResult switchid(HttpServletRequest request,int type,String latlng,String location,String types,int uid,int status,String openid) {
		Teacher teacher = teacherService.selectByOpenid(openid);
		Parent parent  = parentService.selectByOpenid(openid);
		
		Lesson lesson = new Lesson();
		Prove prove = new Prove();
		Integer id = null;
		
		if(status==1) {//老师转家长
			teacherService.updateIdentity("否", openid);
			
			if(parent!=null) {
				parentService.updateIdentity("是", openid);
				id = parent.getId();
			}else {	
				parent = new Parent();
				parent.setSex(teacher.getSex());
				parent.setName(teacher.getName());
				parent.setImage(teacher.getImage());
				parent.setOpenid(teacher.getOpenid());
				parent.setPhone(teacher.getPhone());
				parent.setLocation(location);
				parent.setLatlng(latlng);
				parentService.add(parent);	
				
				Parent p  = parentService.selectByOpenid(openid);
				id = p.getId();
			}
		}else if(status==2) {
			//家长转老师
			parentService.updateIdentity("否", openid);
			
			String leavel = null;
			if(types.equals("大学生")) {
				leavel = "培优大学生老师";
			}else {
				leavel = "培优老师";
			}
			
			if(teacher!=null) {
				id = teacher.getId();
				teacher.setTypes(types);
				teacher.setOnline("是");
				teacher.setLevel(leavel);
				teacherService.update(teacher);		
			}else {
				teacher = new Teacher();
				teacher.setLevel(leavel);
				teacher.setTypes(types);
				teacher.setOnline("是");
				teacher.setLevel(leavel);
				teacher.setName(parent.getName());
				teacher.setPhone(parent.getPhone());
				teacher.setImage(parent.getImage());
				teacher.setOpenid(parent.getOpenid());
				teacher.setSex(parent.getSex());
				teacher.setLocation(location);
				teacher.setLatlng(latlng);
				teacher.setCreateTime(new Date());
				teacher.setActivate(0);
				teacherService.add(teacher);

				Teacher t = teacherService.selectByOpenid(openid);
				id = t.getId();
				
				lesson.setTid(id);
				lesson.setState("true");
				lesson.setTimes("[{\"week\":\"周一\",\"time1\":\"8:00\",\"time2\":\"10:00\"},{\"week\":\"周二\",\"time1\":\"10:00\",\"time2\":\"13:00\"},{\"week\":\"周三\",\"time1\":\"09:00\",\"time2\":\"11:00\"}]");
				lessonService.insert(lesson);
				
				prove.setTid(id);
				proveService.add(prove);
			}
		}
		
		Map<Object,Object> map = new HashMap<Object, Object>();
		map.put("id",id);
		map.put("type", status);
		return JsonResult.ok(map);
		
	}
	
	
	/**
	 * 查找金钱
	 */
	@PostMapping("selectmoney")
	public JsonResult selectMoney(String openid) {
//		Teacher t = teacherService.selectByOnId("是", id);
//		Parent p = parentService.selectByOnId("是", id);
//		if(t!=null) {
//			Double m = teacherService.selectMoney(openid);
//			return JsonResult.ok(m);
//		}else if(p!=null) {
//			Double m = parentService.selectMoney(openid);
//			return JsonResult.ok(m);
//		}
//		return JsonResult.errorMsg("fail");
		String money = moneyService.selectAll(openid);
		return JsonResult.ok(money);
	}
	
	/**
	 * 查找用户id
	 * @param openid
	 * @param type 1:家长，2：老师  默认家长
	 * @return
	 */
	@GetMapping("selectParentId")
	public JsonResult selectParentId(String openId,Integer type) {
		if(type != null && type == 2) {
			Teacher teacher = teacherService.selectByOpenid(openId);
			return JsonResult.ok(teacher);
		}else {
			Parent p = parentService.selectByOpenid(openId);
			return JsonResult.ok(p);
		}
		
	}

	
}
