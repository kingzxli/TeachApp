package com.song.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.song.entity.Result;
import com.song.pojo.SelectTea;
import com.song.pojo.TeaSelect;
import com.song.pojo.Teacher;
import com.song.service.SelectTeaService;
import com.song.service.TeacherService;
import com.song.util.HttpClientUtil;
import com.song.util.JsonResult;
import com.song.util.poi.FileUtil;

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
	
	@Autowired
	private FileUtil fileUtil;
	
	
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
		for(SelectTea dbSelectTea : list) {
			if(StringUtils.isEmpty(dbSelectTea.getName())) {
				dbSelectTea.setName(dbSelectTea.getParentName());
			}
		}
		PageInfo<SelectTea> page = new PageInfo<SelectTea>(list,pageSize);
		page.setPageNum(pageId);
		page.setPageSize(pageSize);
		page.setSize(list.size());
		page.setList(list);
		return JsonResult.ok(page);
	}
	
	
	@GetMapping("selectBystatus")
	public JsonResult selectBystatus(String sid) {
		List<SelectTea> list = SelectteaService.selectBystatus(sid);
		return JsonResult.ok(list);
	}
	
	@GetMapping("export")
	public Result<Map<String, String>> exportTeacher(SelectTea selectTea) {
		List<SelectTea> list = SelectteaService.selectall(selectTea);
		for(SelectTea dbSelectTea : list) {
			if(StringUtils.isEmpty(dbSelectTea.getName())) {
				dbSelectTea.setName(dbSelectTea.getParentName());
			}
		}
		
		 Workbook workbook = new SXSSFWorkbook();
		 //设置sheet名称
	     Sheet sheet = workbook.createSheet("生源接单");
	     //设置样式     
	     CellStyle style = fileUtil.createStyle(workbook);
	     /**
	      * 设置表头
	      */
	     Row row0 = sheet.createRow(0);
	     String[] colNames = new String[]{
	    		 "家长名称","家长联系电话","下单时间","状态","授课方式","教师授课地址","详细地址","在读学校","辅导年级","辅导年级","辅导科目",
	    		 "学生性别","试课时间","上课时间","学生基本情况","学生其他基本情况","希望老师性别","希望老师类型","老师的要求",
	    		 "老师的其他要求","了解机构途径"
	     };
	     for(int i = 0;i<colNames.length;i++) {
	    	 sheet.setColumnWidth(i, 20*256);
	    	 fileUtil.cellSetUp(row0,i, colNames[i], style);
	     }
	     
	     
	     /**
	      * 设置列表数据
	      */
	       for(int j= 0;j< list.size();j++) {
	    	   Row row = sheet.createRow(j+1);
	    	   Integer pstatus = list.get(j).getPstatus();
	    	  
	    	   fileUtil.cellSetUp(row,0,list.get(j).getParentName(), style);
	    	   fileUtil.cellSetUp(row,1,list.get(j).getPhone(), style);
	    	   fileUtil.cellSetUp(row,2,list.get(j).getAddtime(), style);
	    	   //0:匹配中,1:已匹配,2:取消订单
	    	   if(pstatus != null) {
	    		   if(pstatus == 0) {
	    			   fileUtil.cellSetUp(row,3,"匹配中", style);
	    		   }else if(pstatus == 1) {
	    			   fileUtil.cellSetUp(row,3,"已匹配", style);
	    		   }else if(pstatus == 2) {
	    			   fileUtil.cellSetUp(row,3,"取消订单", style);
	    		   }
	    	   }
	    	   fileUtil.cellSetUp(row,4,list.get(j).getTeachingType(), style);
	    	   fileUtil.cellSetUp(row,5,list.get(j).getAddress(), style);
	    	   fileUtil.cellSetUp(row,6,list.get(j).getDetailed(), style);    	    
	    	   fileUtil.cellSetUp(row,7,list.get(j).getSchool(), style);
	    	   fileUtil.cellSetUp(row,8,list.get(j).getCulture(), style);
	    	   fileUtil.cellSetUp(row,9,list.get(j).getGrade(), style);
	    	   fileUtil.cellSetUp(row,10,list.get(j).getSubject(), style);
	    	   fileUtil.cellSetUp(row,11,list.get(j).getStusex(), style);
	    	   fileUtil.cellSetUp(row,12,list.get(j).getTrial(), style);
	    	   fileUtil.cellSetUp(row,13,list.get(j).getGoclass(), style);
	    	   fileUtil.cellSetUp(row,14,list.get(j).getStusituation(), style);
	    	   fileUtil.cellSetUp(row,15,list.get(j).getStumessage(), style);
	    	   fileUtil.cellSetUp(row,16,list.get(j).getTeasex(), style);
	    	   fileUtil.cellSetUp(row,17,list.get(j).getTeatype(), style);
	    	   fileUtil.cellSetUp(row,18,list.get(j).getTearequire(), style);
	    	   fileUtil.cellSetUp(row,19,list.get(j).getOtherrequire(), style);
	    	   fileUtil.cellSetUp(row,20,list.get(j).getWay(), style);
	       }
	         
	         
	       /**
			 * 3.将文件写入服务器目录
			 */
			String fileName = fileUtil.writeExcel(workbook,"生源接单");
			/**
			 * 4.返回文件下载地址
			 */
			Map<String, String> map = new HashMap<>();
			map.put("url", fileName);
			return new Result<>(map);	
		
	}
	
	
	
}
