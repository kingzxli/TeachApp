package com.song.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.song.pojo.Teacher;
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
@RequestMapping("teacher")
public class TeacherController {
	
	@Autowired
	private TeacherService teacherService;
	
	private String loc;
	
	@PostMapping("updateActivate")
	public JsonResult updateActivate(Integer id) {
		teacherService.updateActivate(3, id);
		return JsonResult.ok();
	}
	
	//首页查询
	@PostMapping("selectByCondition")
	public JsonResult selectByCondition(String project,int pageId,int pageSize,String latlng) {
		PageHelper.startPage(pageId,pageSize);
		List<Teacher> T = teacherService.selectByCondition(project);
		List<Teacher> tea = new ArrayList<Teacher>();
		String[] ary = new String[T.size()];
		for(int i=0;i<T.size();i++) {
			String[] s = T.get(i).getCharacter().split(",");
			T.get(i).setChara(s);
			ary[i] = T.get(i).getLatlng();
		}
		//拼接数组
        StringBuffer sb = new StringBuffer();
       //如果终点经纬度只有一个值就不做 ; 区分
        System.out.println(ary.length);
        if(ary.length > 0) {
        	String[] arr = new String[ary.length];
        	if(ary.length==1) {
    			StringBuffer loc = sb.append(ary[0]);
    			Map<String,String> maps = new HashMap<String, String>();
    			maps.put("mode","driving");
    			maps.put("from",latlng);
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
					arr[y] = String.valueOf(Integer.parseInt(js.getString("distance"))/1000);
					T.get(y).setDuration(arr[y]);
				}
				for(int z=0;z<T.size();z++) {
					if(T.get(z).getDuration()!=null) {
						tea.add(T.get(z));
					}
				}
				PageInfo<Teacher> page = new PageInfo<Teacher>(T,pageSize);
		    	page.setPageNum(pageId);
				page.setPageSize(pageSize);
				page.setSize(tea.size());
				page.setList(tea);
				return JsonResult.ok(page);
        	}else {
        		for (int i = 0; i < ary.length; i++) {
        			loc = sb.append(ary[i].trim()+";").substring(0, sb.length()-1);
        		}
        			Map<String,String> maps = new HashMap<String, String>();
        			maps.put("mode","driving");
        			maps.put("from",latlng);
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
						arr[y] = String.valueOf(Integer.parseInt(js.getString("distance"))/1000);
    					T.get(y).setDuration(arr[y]);
    				}
    				for(int z=0;z<T.size();z++) {
    					if(T.get(z).getDuration()!=null) {
    						tea.add(T.get(z));
    					}
    				}
    				PageInfo<Teacher> page = new PageInfo<Teacher>(T,pageSize);
    		    	page.setPageNum(pageId);
    				page.setPageSize(pageSize);
    				page.setSize(tea.size());
    				page.setList(tea);
    				return JsonResult.ok(page);
        	}
        }
		return JsonResult.ok();
	}
	
	
	//查积分
	@PostMapping("selectjifen")
	public JsonResult update(String openid) {
		Teacher s = teacherService.selectByOpenid(openid);
		Map<Object, Object> map = new HashMap<Object, Object>();
		Integer jifen = 0;
		String level = "";
		if(s != null && s.getJifen() != null) {
			jifen = s.getJifen();
			level = s.getLevel();
		}
		
		map.put("jifen", jifen);
		map.put("level", level);
		return JsonResult.ok(map);
	}
	
	//修改个人资料
	@PostMapping("update")
	public JsonResult update(Teacher teacher) {
		String p = teacher.getProject().replace("\"", "");
		teacher.setProject(p);
		teacherService.update(teacher);
//		Teacher t = teacherService.selectById(teacher.getId(), teacher.getOpenid());
//		if(t.getActivate()==2) {
//			teacherService.updateActivate(3, teacher.getId());
//		}
		return JsonResult.ok();
	}
	
	//修改毕业院校
	@PostMapping("updateschool")
	public JsonResult updateschool(Teacher teacher) {
		teacherService.updateschool(teacher);
		return JsonResult.ok();
	}
	
	//修改职称
	@PostMapping("updatetitle")
	public JsonResult updatetitle(Teacher teacher) {
		teacherService.updatetitle(teacher);
		return JsonResult.ok();
	}
	
	//修改荣誉
	@PostMapping("updatehonor")
	public JsonResult updatehonor(Teacher teacher) {
		teacherService.updatehonor(teacher);
		return JsonResult.ok();
	}
	
	//修改教学经历
	@PostMapping("updatexperience")
	public JsonResult updatexperience(Teacher teacher) {
		System.out.println(teacher.toString());
		teacherService.updatexperience(teacher);
		return JsonResult.ok();
	}
	
	//修改成功案例
	@PostMapping("updatesuccess")
	public JsonResult updatesuccess(Teacher teacher) {
		teacherService.updatesuccess(teacher);
		return JsonResult.ok();
	}
	
	//修改个人标签
	@PostMapping("updatecharacter")
	public JsonResult updatecharacter(Teacher teacher) {
		teacherService.updatecharacter(teacher);
		return JsonResult.ok();
	}
	
	//修改老师生活照
	@PostMapping("updatephoto")
	public JsonResult updatephoto(Teacher teacher) {
		teacherService.updatephoto(teacher);
		Teacher t = teacherService.selectById(teacher.getId(), teacher.getOpenid());
		if(t.getActivate()==2) {
			teacherService.updateActivate(3, teacher.getId());
		}
		return JsonResult.ok();
	}
	
	//点击个人资料获取
	@GetMapping("selectbyid")
	public JsonResult selectById(Integer tid,String openid) {
		Teacher teacher = teacherService.selectById(tid,openid);
		return JsonResult.ok(teacher);
	}
	
	//找老师列表
	@GetMapping("select")
	public JsonResult select(String latlng,String grade,String project,String sex,String types,int pageId,int pageSize) {
		System.out.println("找老师列表当前位置"+latlng+"--"+grade+"---"+project+"---"+sex+"----"+types);
		PageHelper.startPage(pageId,pageSize);
		List<Teacher> teacher = teacherService.selectAll(grade,project,sex,types,"");
		List<Teacher> tea = new ArrayList<Teacher>();
		String[] ary = new String[teacher.size()];
		for(int i=0;i<teacher.size();i++) {
			String[] s = teacher.get(i).getCharacter().split(",");
			teacher.get(i).setChara(s);
			ary[i] = teacher.get(i).getLatlng();
		}
		//拼接数组
        StringBuffer sb = new StringBuffer();
       //如果终点经纬度只有一个值就不做 ; 区分
        System.out.println(ary.length);
        if(ary.length > 0) {
        	String[] arr = new String[ary.length];
        	if(ary.length==1) {
    			StringBuffer loc = sb.append(ary[0]);
    			Map<String,String> maps = new HashMap<String, String>();
    			maps.put("mode","driving");
    			maps.put("from",latlng);
    			maps.put("to", loc.toString());
    			maps.put("key", "BJFBZ-GUTE6-MBVSJ-M2SOK-43WT5-JFFYQ");
    			String result = HttpClientUtil.doGet("https://apis.map.qq.com/ws/distance/v1/", maps);
    			JSONObject jsonObject = JSONObject.parseObject(result);
				String object = jsonObject.get("result").toString();
				JSONObject jsonObject1 = JSONObject.parseObject(object);
				String elements = jsonObject1.get("elements").toString();
				JSONArray json = JSONArray.fromObject(elements);
				PageHelper.startPage(pageId,pageSize);
				for(int y=0;y<json.size();y++) {
					net.sf.json.JSONObject js = (net.sf.json.JSONObject) json.get(y);
					if(Integer.parseInt(js.getString("distance"))/1000<100) {
						arr[y] = String.valueOf(Integer.parseInt(js.getString("distance"))/1000);
    					teacher.get(y).setDuration(arr[y]);
    					tea.add(teacher.get(y));
					}
				}
				PageInfo<Teacher> page = new PageInfo<Teacher>(teacher,pageSize);
		    	page.setPageNum(pageId);
				page.setPageSize(pageSize);
				page.setSize(tea.size());
				page.setList(tea);
				return JsonResult.ok(page);
        	}else {
        		for (int i = 0; i < ary.length; i++) {
        			loc = sb.append(ary[i].trim()+";").substring(0, sb.length()-1);
        		}
    			Map<String,String> maps = new HashMap<String, String>();
    			maps.put("mode","driving");
    			maps.put("from",latlng);
    			maps.put("to", loc);
    			maps.put("key", "BJFBZ-GUTE6-MBVSJ-M2SOK-43WT5-JFFYQ");
    			String result = HttpClientUtil.doGet("https://apis.map.qq.com/ws/distance/v1/", maps);
    			JSONObject jsonObject = JSONObject.parseObject(result);
				String object = jsonObject.get("result").toString();
				JSONObject jsonObject1 = JSONObject.parseObject(object);
				String elements = jsonObject1.get("elements").toString();
				JSONArray json = JSONArray.fromObject(elements);
				System.out.println("json==="+json.size()+"arr==="+ary.length);
				for(int y=0;y<json.size();y++) {
					net.sf.json.JSONObject js = (net.sf.json.JSONObject) json.get(y);
					if(Integer.parseInt(js.getString("distance"))/1000<100) {
						arr[y] = String.valueOf(Integer.parseInt(js.getString("distance"))/1000);
    					teacher.get(y).setDuration(arr[y]);
    					tea.add(teacher.get(y));
					}
				}
				Collections.sort(tea, new Comparator<Teacher>() {
					@Override
					public int compare(Teacher o1, Teacher o2) {
						// TODO Auto-generated method stub
						return o1.getDuration().compareTo(o2.getDuration());
					}
				});
				PageInfo<Teacher> page = new PageInfo<Teacher>(tea,pageSize);
		    	page.setPageNum(pageId);
				page.setPageSize(pageSize);
				page.setSize(tea.size());
				page.setList(tea);
				return JsonResult.ok(page);
        	}
        }
		return JsonResult.ok();
	}
	
	
	//首页老师查询
	@PostMapping("selectindex")
	public JsonResult selectindex(String latlng) {
		System.out.println("dddd"+latlng);
		PageHelper.startPage(1,100);
		List<Teacher> teacher = teacherService.selectindex();
		List<Teacher> tea = new ArrayList<Teacher>();
		String[] ary = new String[teacher.size()];
		for(int i=0;i<teacher.size();i++) {
			String[] s = teacher.get(i).getCharacter().split(",");
			teacher.get(i).setChara(s);
			ary[i] = teacher.get(i).getLatlng();
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
    			maps.put("from",latlng);
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
					if(Integer.parseInt(js.getString("distance"))/1000<100) {
						arr[y] = String.valueOf(Integer.parseInt(js.getString("distance"))/1000);
    					teacher.get(y).setDuration(arr[y]);
    					tea.add(teacher.get(y));
					}
					if(tea.size()<=10) {
						return JsonResult.ok(tea);
					}else {
						tea.subList(0, 10);
						return JsonResult.ok(tea);
					}
				}
        	}else {
        		for (int i = 0; i < ary.length; i++) {
        			loc = sb.append(ary[i].trim()+";").substring(0, sb.length()-1);
        		}
        			Map<String,String> maps = new HashMap<String, String>();
        			maps.put("mode","driving");
        			maps.put("from",latlng);
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
    					if(Integer.parseInt(js.getString("distance"))/1000<100) {
    						arr[y] = String.valueOf(Integer.parseInt(js.getString("distance"))/1000);
        					teacher.get(y).setDuration(arr[y]);
        					tea.add(teacher.get(y));
    					}
    				}
    				if(tea.size()>10) {
    					List<Teacher> subList = tea.subList(0, 10);
    					Collections.sort(subList, new Comparator<Teacher>() {

    						@Override
    						public int compare(Teacher o1, Teacher o2) {
    							// TODO Auto-generated method stub
    							return o1.getDuration().compareTo(o2.getDuration());
    						}
    						
    					});
    					return JsonResult.ok(subList);
    				}else {
    					return JsonResult.ok(tea);
    				}
        	}
        }
        
		return JsonResult.ok();
	}
	
	
	@PostMapping("selectother")
	public JsonResult selectother(int id) {
		String other = teacherService.selectOther(id);
		return JsonResult.ok(other);
	}
	
	
	@PostMapping("updateLatlng")
	public JsonResult updateLatlng(String latlng,String openid) {
		teacherService.updateLatlng(latlng, openid);
		return JsonResult.ok();
	}
	
	//修改老师地址
	@PostMapping("updateLocation")
	public JsonResult updateLocation(Teacher teacher) {
		teacherService.update(teacher);
		return JsonResult.ok();
	}
	
	//编辑老师信息
	@PostMapping("update/info/{id}")
	public JsonResult updateInfo(@PathVariable("id") Integer id ,@RequestBody Teacher teacher) {
		teacher.setId(id);
		teacher.setModifiedTime(new Date());
		teacherService.updateInfo(teacher);
		return JsonResult.ok();
	}
	
	
}
