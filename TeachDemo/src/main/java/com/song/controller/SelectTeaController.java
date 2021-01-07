package com.song.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.song.pojo.SelectTea;
import com.song.pojo.TeaSelect;
import com.song.pojo.Teacher;
import com.song.service.SelectTeaService;
import com.song.service.TeacherService;
import com.song.util.HttpClientUtil;
import com.song.util.JsonResult;
import net.sf.json.JSONArray;

/**
 * 
 * @author Song
 *
 */

@RestController
@RequestMapping("selecttea")
public class SelectTeaController {
	
	@Autowired
	private SelectTeaService SelectteaService;
	@Autowired
	private TeacherService teacherService;
	
	private String loc;
	
	
	//家长接受或者拒绝单
	@PostMapping("updatestatus")
	public JsonResult updateStutas(String id,int status,String sid) {
		if(status==1) {
			SelectteaService.updatePstatus(sid, 1);
			SelectteaService.updateStatus(id, 1);
		}else {
			SelectteaService.updatePstatus(sid, 2);
			SelectteaService.updateStatus(id, 2);
		}
		return JsonResult.ok();
	}
	
	
	
	//生源接单查看是否接单
	@PostMapping("selectstatus")
	public JsonResult selectStatus(int sid,String openid) {
		TeaSelect ts = SelectteaService.selectStuta(sid, openid);
		return JsonResult.ok(ts);
	}
	
	
	//老师查看关于自己的单
	@PostMapping("selectByTopenid")
	public JsonResult selectByTopenid(String topenid) {
		List<TeaSelect> ts = SelectteaService.selectByTopenid(topenid);
		return JsonResult.ok(ts);
	}
	
	
	//家长查看接单
	@PostMapping("selectByPid")
	public JsonResult selectByPid(String openid,int pid) {
		List<TeaSelect> ts = SelectteaService.selectByPid(pid);
		//推送
		
		return JsonResult.ok(ts);
	}
	
	//接单
	@PostMapping("addTeaSel")
	public JsonResult addTeaSel(String topenid,int sid,int tid) {
		String todays = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		SelectteaService.addTeaSel(topenid, sid, todays,tid);
		//推送
		
		return JsonResult.ok();
	}
	
	
	//通过数据id查详情
	@PostMapping("selectid")
	public JsonResult selectid(int id) {
		SelectTea st = SelectteaService.selectId(id);
		return JsonResult.ok(st);
	}
		
	
	//删除
	@GetMapping("delete")
	public JsonResult delete(String id) {
		SelectteaService.delete(id);
		SelectteaService.updatePstatus(id, 2);
		SelectteaService.updateStatus(id, 2);
		return JsonResult.ok();
	}
	
	
	//根据学生id查 
	@PostMapping("selectbyid")
	public JsonResult selectById(int id) {
		List<SelectTea> list = SelectteaService.selectById(id);
		return JsonResult.ok(list);
	}
	
	
	//帮我找老师数据添加
	@GetMapping("/add")
	public JsonResult add(SelectTea selectTea) {
		String subject = selectTea.getSubject();
		System.out.println(subject);
		String todays = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		selectTea.setAddtime(todays);
		System.out.println(selectTea);
		String s = selectTea.getSubject();
		String[] as = s.split("-");
		for (int i = 0; i < as.length; i++) {
			System.out.println(as[i]);
			selectTea.setCulture(as[0]);
			selectTea.setGrade(as[1]);
			selectTea.setSubject(as[2]);
		}
		SelectteaService.add(selectTea);
		return JsonResult.ok();
	}
	
	
	//生源接单获取
	@GetMapping("select")
	public JsonResult select(int tid,int pageId,int pageSize,String address,String project,String tea_sex,String openid) {
		PageHelper.startPage(pageId,pageSize);
		String url = "https://apis.map.qq.com/ws/geocoder/v1/";
		Teacher teacher = teacherService.selectById(tid,openid);
		String type = teacher.getTypes();
		if(type.equals("大学生")) {
			List<SelectTea> list = SelectteaService.selectByType("大学生", address, project, tea_sex);
			PageInfo<SelectTea> page = new PageInfo<SelectTea>();
			//获取金纬度拼接一段
			String[] ary = new String[list.size()];
			for(int i=0;i<list.size();i++) {
				//地址拼接
				String addrs = list.get(i).getAddress().replace("-", "");
				String detailed = list.get(i).getDetailed();
				//将学生地址转经纬度
				Map<String, String> m = new HashMap<String, String>();
				m.put("address", addrs+detailed);
				m.put("key", "BJFBZ-GUTE6-MBVSJ-M2SOK-43WT5-JFFYQ");
				String result = HttpClientUtil.doGet(url, m);
				JSONObject jsonObject = JSONObject.parseObject(result);
				String object = jsonObject.get("result").toString();
				JSONObject jsonObject1 = JSONObject.parseObject(object);
				String location = jsonObject1.get("location").toString();
				JSONObject jsonObject2 = JSONObject.parseObject(location);
				 //纬度
		        String lat = jsonObject2.get("lat").toString();
		        //经度
		        String lng = jsonObject2.get("lng").toString();
		        ary[i]=lat+","+lng;
			}
			//拼接数组
	        StringBuffer sb = new StringBuffer();
	       //如果终点经纬度只有一个值就不做 ; 区分
	        if(ary.length > 0) {
	        	String[] arr = new String[ary.length];
	        	if(ary.length==1) {
	        		StringBuffer loc = sb.append(ary[0]);
	        		System.out.println(loc);
        			Map<String,String> maps = new HashMap<String, String>();
        			maps.put("mode","driving");
        			maps.put("from", teacher.getLatlng());
        			maps.put("to", loc.toString());
        			maps.put("key", "BJFBZ-GUTE6-MBVSJ-M2SOK-43WT5-JFFYQ");
        			String result = HttpClientUtil.doGet("https://apis.map.qq.com/ws/distance/v1/", maps);
        			JSONObject jsonObject = JSONObject.parseObject(result);
    				String object = jsonObject.get("result").toString();
    				JSONObject jsonObject1 = JSONObject.parseObject(object);
    				String elements = jsonObject1.get("elements").toString();
    				JSONArray json = JSONArray.fromObject(elements);
    				for(int y=0;y<json.size();y++) {
    					net.sf.json.JSONObject js = (net.sf.json.JSONObject) json.get(y);
    					arr[y] = js.getString("duration");
    				}
    				
					page.setPageNum(pageId);
					page.setPageSize(pageSize);
					page.setSize(list.size());
					page.setList(list);
					Map<Object, Object> map = new HashMap<Object, Object>();
					map.put("level", teacher.getLevel());
					map.put("duration",arr);
					map.put("page",page);
					return JsonResult.ok(map);
	        	}else {
	        		for (int i = 0; i < ary.length; i++) {
	        			loc = sb.append(ary[i].trim()+";").substring(0, sb.length()-1);
	        		}
	        		System.out.println(loc);
        			Map<String,String> maps = new HashMap<String, String>();
        			maps.put("mode","driving");
        			maps.put("from", teacher.getLatlng());
        			maps.put("to", loc);
        			maps.put("key", "BJFBZ-GUTE6-MBVSJ-M2SOK-43WT5-JFFYQ");
        			String result = HttpClientUtil.doGet("https://apis.map.qq.com/ws/distance/v1/", maps);
        			JSONObject jsonObject = JSONObject.parseObject(result);
    				String object = jsonObject.get("result").toString();
    				JSONObject jsonObject1 = JSONObject.parseObject(object);
    				String elements = jsonObject1.get("elements").toString();
    				JSONArray json = JSONArray.fromObject(elements);
    				for(int y=0;y<json.size();y++) {
    					net.sf.json.JSONObject js = (net.sf.json.JSONObject) json.get(y);
    					arr[y] = js.getString("duration");
    				}

					page.setPageNum(pageId);
					page.setPageSize(pageSize);
					page.setSize(list.size());
					page.setList(list);
					Map<Object, Object> map = new HashMap<Object, Object>();
					map.put("level", teacher.getLevel());
					map.put("duration",arr);
					map.put("page",page);
					return JsonResult.ok(map);
	        	}
	        	
	        }
		}
		if(type.equals("专职老师")) {
			List<SelectTea> list = SelectteaService.selectAll();
			PageInfo<SelectTea> page = new PageInfo<SelectTea>();
			String[] ary = new String[list.size()];
			for(int i=0;i<list.size();i++) {
				//地址拼接
				String addrs = list.get(i).getAddress().replace("-", "");
				String detailed = list.get(i).getDetailed();
				//将学生地址转经纬度
				Map<String, String> m = new HashMap<String, String>();
				m.put("address", addrs+detailed);
				m.put("key", "BJFBZ-GUTE6-MBVSJ-M2SOK-43WT5-JFFYQ");
				String result = HttpClientUtil.doGet(url, m);
				JSONObject jsonObject = JSONObject.parseObject(result);
				String object = jsonObject.get("result").toString();
				JSONObject jsonObject1 = JSONObject.parseObject(object);
				String location = jsonObject1.get("location").toString();
				JSONObject jsonObject2 = JSONObject.parseObject(location);
				 //纬度
		        String lat = jsonObject2.get("lat").toString();
		        //经度
		        String lng = jsonObject2.get("lng").toString();
		        ary[i]=lat+","+lng;
			}
			//拼接数组
	        StringBuffer sb = new StringBuffer();
	       //如果终点经纬度只有一个值就不做 ; 区分
	        if(ary.length >0) {
	        	String[] arr = new String[ary.length];
	        	if(ary.length==1) {
	        		StringBuffer loc = sb.append(ary[0]);
	        		System.out.println(loc);
        			Map<String,String> maps = new HashMap<String, String>();
        			maps.put("mode","driving");
        			maps.put("from", teacher.getLatlng());
        			maps.put("to", loc.toString());
        			maps.put("key", "BJFBZ-GUTE6-MBVSJ-M2SOK-43WT5-JFFYQ");
        			String result = HttpClientUtil.doGet("https://apis.map.qq.com/ws/distance/v1/", maps);
        			JSONObject jsonObject = JSONObject.parseObject(result);
    				String object = jsonObject.get("result").toString();
    				JSONObject jsonObject1 = JSONObject.parseObject(object);
    				String elements = jsonObject1.get("elements").toString();
    				JSONArray json = JSONArray.fromObject(elements);
    				for(int y=0;y<json.size();y++) {
    					net.sf.json.JSONObject js = (net.sf.json.JSONObject) json.get(y);
    					arr[y] = js.getString("duration");
    				}
    				page.setPageNum(pageId);
					page.setPageSize(pageSize);
					page.setSize(list.size());
					page.setList(list);
					Map<Object, Object> map = new HashMap<Object, Object>();
					map.put("level", teacher.getLevel());
					map.put("duration",arr);
					map.put("page",page);
					return JsonResult.ok(map);
	        	}else {
	        		for (int i = 0; i < ary.length; i++) {
	        			loc = sb.append(ary[i].trim()+";").substring(0, sb.length()-1);
	        		}
	        		System.out.println(loc);
        			Map<String,String> maps = new HashMap<String, String>();
        			maps.put("mode","driving");
        			maps.put("from", teacher.getLatlng());
        			maps.put("to", loc);
        			maps.put("key", "BJFBZ-GUTE6-MBVSJ-M2SOK-43WT5-JFFYQ");
        			String result = HttpClientUtil.doGet("https://apis.map.qq.com/ws/distance/v1/", maps);
        			JSONObject jsonObject = JSONObject.parseObject(result);
    				String object = jsonObject.get("result").toString();
    				JSONObject jsonObject1 = JSONObject.parseObject(object);
    				String elements = jsonObject1.get("elements").toString();
    				JSONArray json = JSONArray.fromObject(elements);
    				for(int y=0;y<json.size();y++) {
    					net.sf.json.JSONObject js = (net.sf.json.JSONObject) json.get(y);
    					arr[y] = js.getString("duration");
    				}
    				page.setPageNum(pageId);
					page.setPageSize(pageSize);
					page.setSize(list.size());
					page.setList(list);
					Map<Object, Object> map = new HashMap<Object, Object>();
					map.put("level", teacher.getLevel());
					map.put("duration",arr);
					map.put("page",page);
					return JsonResult.ok(map);
	        	}
	        }
		}
		if(type.equals("均可")) {
			List<SelectTea> list = SelectteaService.selectByType("均可", address, project, tea_sex);
			String[] ary = new String[list.size()];
			for(int i=0;i<list.size();i++) {
				//地址拼接
				String addrs = list.get(i).getAddress().replace("-", "");
				String detailed = list.get(i).getDetailed();
				//将学生地址转经纬度
				Map<String, String> m = new HashMap<String, String>();
				m.put("address", addrs+detailed);
				m.put("key", "BJFBZ-GUTE6-MBVSJ-M2SOK-43WT5-JFFYQ");
				String result = HttpClientUtil.doGet(url, m);
				JSONObject jsonObject = JSONObject.parseObject(result);
				String object = jsonObject.get("result").toString();
				JSONObject jsonObject1 = JSONObject.parseObject(object);
				String location = jsonObject1.get("location").toString();
				JSONObject jsonObject2 = JSONObject.parseObject(location);
				 //纬度
		        String lat = jsonObject2.get("lat").toString();
		        //经度
		        String lng = jsonObject2.get("lng").toString();
		        ary[i]=lat+","+lng;
			}
			//拼接数组
	        StringBuffer sb = new StringBuffer();
	       //如果终点经纬度只有一个值就不做 ; 区分
	        if(ary.length > 0) {
	        	String[] arr = new String[ary.length];
	        	if(ary.length==1) {
	        		StringBuffer loc = sb.append(ary[0]);
        			Map<String,String> maps = new HashMap<String, String>();
        			maps.put("mode","driving");
        			maps.put("from", teacher.getLatlng());
        			maps.put("to", loc.toString());
        			maps.put("key", "BJFBZ-GUTE6-MBVSJ-M2SOK-43WT5-JFFYQ");
        			String result = HttpClientUtil.doGet("https://apis.map.qq.com/ws/distance/v1/", maps);
        			JSONObject jsonObject = JSONObject.parseObject(result);
    				String object = jsonObject.get("result").toString();
    				JSONObject jsonObject1 = JSONObject.parseObject(object);
    				String elements = jsonObject1.get("elements").toString();
    				JSONArray json = JSONArray.fromObject(elements);
    				for(int y=0;y<json.size();y++) {
    					net.sf.json.JSONObject js = (net.sf.json.JSONObject) json.get(y);
    					arr[y] = js.getString("duration");
    				}
    				Map<Object, Object> map = new HashMap<Object, Object>();
					map.put("level", teacher.getLevel());
					map.put("list", list);
					map.put("duration",arr);
					return JsonResult.ok(map);
	        	}else {
	        		for (int i = 0; i < ary.length; i++) {
	        			loc = sb.append(ary[i].trim()+";").substring(0, sb.length()-1);
	        		}
        			Map<String,String> maps = new HashMap<String, String>();
        			maps.put("mode","driving");
        			maps.put("from", teacher.getLatlng());
        			maps.put("to", loc);
        			maps.put("key", "BJFBZ-GUTE6-MBVSJ-M2SOK-43WT5-JFFYQ");
        			String result = HttpClientUtil.doGet("https://apis.map.qq.com/ws/distance/v1/", maps);
        			JSONObject jsonObject = JSONObject.parseObject(result);
    				String object = jsonObject.get("result").toString();
    				JSONObject jsonObject1 = JSONObject.parseObject(object);
    				String elements = jsonObject1.get("elements").toString();
    				JSONArray json = JSONArray.fromObject(elements);
    				for(int y=0;y<json.size();y++) {
    					net.sf.json.JSONObject js = (net.sf.json.JSONObject) json.get(y);
    					arr[y] = js.getString("duration");
    				}
    				Map<Object, Object> map = new HashMap<Object, Object>();
					map.put("level", teacher.getLevel());
					map.put("list", list);
					map.put("duration",arr);
					return JsonResult.ok(map);
	        	}
	        }
		}
		return JsonResult.ok("无数据");
	}
	
	
	
	@GetMapping("selectall")
	public JsonResult selectall(SelectTea selectTea,int pageId,int pageSize) {
		PageHelper.startPage(pageId, pageSize);
		List<SelectTea> list = SelectteaService.selectall(selectTea);
		PageInfo<SelectTea> page = new PageInfo<SelectTea>(list,pageSize);
		page.setPageNum(pageId);
		page.setPageSize(pageSize);
		page.setSize(list.size());
		page.setList(list);
		return JsonResult.ok(page);
	}
	
	
	@GetMapping("selectBystatus")
	public JsonResult selectBystatus(int sid) {
		List<SelectTea> list = SelectteaService.selectBystatus(sid);
		return JsonResult.ok(list);
	}
	
	
	
	
	
}
