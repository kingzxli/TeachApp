package com.song.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.song.entity.Result;
import com.song.pojo.Lesson;
import com.song.pojo.Prove;
import com.song.pojo.Teacher;
import com.song.service.LessonService;
import com.song.service.ProveService;
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
@RequestMapping("teacher")
public class TeacherController {
	
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private ProveService proveService;
	@Autowired
	private PushController pushController;
	@Autowired
	private LessonService lessonService;
	@Autowired
	private FileUtil fileUtil;
	private String loc;
	
	//查询下架老师
	@PostMapping("selectByActivate")
	public JsonResult selectByActivate(@RequestBody Teacher t) {
		List<Teacher> T = teacherService.selectByActivate(t);
		return JsonResult.ok(T);
	}
	
	//首页查询
	@PostMapping("selectByCondition")
	public JsonResult selectByCondition(String project) {
		List<Teacher> T = teacherService.selectByCondition(project);
		return JsonResult.ok(T);
	}
	
	
	//查积分
	@PostMapping("selectjifen")
	public JsonResult update(String openid) {
		Teacher s = teacherService.selectByOpenid(openid);
		Map<Object, Object> map = new HashMap<Object, Object>();
		if(s.getJifen()!=null) {
			map.put("jifen", s.getJifen());
			map.put("level", s.getLevel());
			return JsonResult.ok(map);
		}
		map.put("jifen",0);
		map.put("level", s.getLevel());
		return JsonResult.ok();
	}
	
	//修改个人资料
	@PostMapping("update")
	public JsonResult update(@RequestBody Teacher teacher) {
//		String p = teacher.getProject().replace("\"", "");
//		teacher.setProject(p);
		teacherService.update(teacher);
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
		return JsonResult.ok();
	}
	
	//点击个人资料获取
	@GetMapping("selectbyid")
	public JsonResult selectById(int tid,String openid) {
		Teacher teacher = teacherService.selectById(tid,openid);
		return JsonResult.ok(teacher);
	}
	
	//找老师列表
	@GetMapping("select")
	public JsonResult select(String latlng,String grade,String project,String sex,String types) {
		System.out.println(latlng+"--"+grade+"---"+project+"---"+sex+"----"+types);
		List<Teacher> teacher = teacherService.selectAll(grade,project,sex,types);
		String[] ary = new String[teacher.size()];
		for(int i=0;i<teacher.size();i++) {
			String[] s = teacher.get(i).getCharacter().split(",");
			teacher.get(i).setChara(s);
			ary[i] = teacher.get(i).getLatlng();
			System.out.println(ary[i]);
		}
		//拼接数组
        StringBuffer sb = new StringBuffer();
       //如果终点经纬度只有一个值就不做 ; 区分
        System.out.println(ary.length);
        if(ary.length > 0) {
        	String[] arr = new String[ary.length];
        	if(ary.length==1) {
        		System.out.println("走一个参数");
    			StringBuffer loc = sb.append(ary[0]);
    			System.out.println("KKKKKK"+loc);
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
					arr[y] = js.getString("duration");
					teacher.get(y).setDuration(arr[y]);
				}
				return JsonResult.ok(teacher);
        	}else {
        		for (int i = 0; i < ary.length; i++) {
        			System.out.println("走多个参数");
        			loc = sb.append(ary[i].trim()+";").substring(0, sb.length()-1);
        		}
        			System.out.println(loc);
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
    					arr[y] = js.getString("duration");
    					teacher.get(y).setDuration(arr[y]);
    				}
					return JsonResult.ok(teacher);
        	}
        }
		return JsonResult.ok();
	}
	
	
	//首页老师查询
	@PostMapping("selectindex")
	public JsonResult selectindex(String latlng) {
		List<Teacher> teacher = teacherService.selectindex();
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
        		System.out.println("走一个参数");
        		StringBuffer loc = sb.append(ary[0]);
        		System.out.println(loc);
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
					arr[y] = js.getString("duration");
					teacher.get(y).setDuration(arr[y]);
				}
				return JsonResult.ok(teacher);
        	}else {
        		System.out.println("走多个参数");
        		for (int i = 0; i < ary.length; i++) {
        			loc = sb.append(ary[i].trim()+";").substring(0, sb.length()-1);
        			System.out.println(loc);
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
    					arr[y] = js.getString("duration");
    					teacher.get(y).setDuration(arr[y]);
    				}
					return JsonResult.ok(teacher);
        	}
        }
        
		return JsonResult.ok();
	}
	
	@PostMapping("updateactivate")
	public JsonResult updateactivate(@RequestBody Teacher t) {
		String todays = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		Teacher tea = teacherService.selectById(t.getId(), "");
		System.out.println(t);
		if(t.getActivate() ==1 ) {
			try {
				pushController.audit("老师注册审核结果通知", tea.getOpenid(), tea.getName(), todays, "通过","恭喜您成功通过注册申请");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			teacherService.updateActivate(t.getPrice(),t.getOtherpri(),t.getActivate(),t.getId());
			return JsonResult.ok();
		}else if(t.getActivate() == 4) {
			teacherService.updateActivate(t.getPrice(),t.getOtherpri(),t.getActivate(),t.getId());	
			return JsonResult.ok();
		}else {
			teacherService.updateActivate(t.getPrice(),t.getOtherpri(),t.getActivate(),t.getId());
			return JsonResult.ok();
		}
	}
	
	
	
	@GetMapping("selectByZero")
	public JsonResult selectByZero(Teacher teacher,int pageId,int pageSize) {
		PageHelper.startPage(pageId,pageSize);
		List<Teacher> list = teacherService.selectByZero(teacher);
		for (int i=0;i<list.size();i++) {
			if(list.get(i).getId()!=null) {
				Prove p = proveService.selectByTid(list.get(i).getId());
				if(p!=null) {
					list.get(i).setNames(p.getNames());
					list.get(i).setFront(p.getFront());
					list.get(i).setBack(p.getBack());
					list.get(i).setFace(p.getFace());
					list.get(i).setIdcard(p.getIdcard());
					list.get(i).setGraduatenum(p.getGraduatenum());
					list.get(i).setGraduateimage(p.getGraduateimage());
					list.get(i).setTeachnum(p.getTeachnum());
					list.get(i).setTeachimage(p.getTeachimage());
					list.get(i).setPhone(p.getPhone());
					list.get(i).setWeChat(p.getWeChat());
				}
			}
		}
		PageInfo<Teacher> page = new PageInfo<Teacher>(list,pageSize);
		page.setPageNum(pageId);
		page.setPageSize(pageSize);
		page.setSize(list.size());
		page.setList(list);
		return JsonResult.ok(page);
	}
	
	@GetMapping("selectByNamePro")
	public JsonResult selectByNamePro(Teacher teacher,int pageId,int pageSize) {
		PageHelper.startPage(pageId, pageSize);
		List<Teacher> t = teacherService.selectByNamePro(teacher);
		for (int i=0;i<t.size();i++) {
			if(t.get(i).getId()!=null) {
				Prove p = proveService.selectByTid(t.get(i).getId());
				Lesson l = lessonService.selectById(t.get(i).getId());
				if(p!=null) {
					t.get(i).setFront(p.getFront());
					t.get(i).setBack(p.getBack());
					t.get(i).setFace(p.getFace());
					t.get(i).setIdcard(p.getIdcard());
					t.get(i).setGraduatenum(p.getGraduatenum());
					t.get(i).setGraduateimage(p.getGraduateimage());
					t.get(i).setTeachnum(p.getTeachnum());
					t.get(i).setTeachimage(p.getTeachimage());
					t.get(i).setNames(p.getNames());
					t.get(i).setWeChat(p.getWeChat());
				}
				if(l!=null) {
					t.get(i).setLessontime(l.getTimes());
				}
			}
		}
		PageInfo<Teacher> page = new PageInfo<Teacher>(t,pageSize);
		page.setPageNum(pageId);
		page.setPageSize(pageSize);
		page.setSize(t.size());
		page.setList(t);
		return JsonResult.ok(page);
	}
	
	
	//拒绝老师申请
	@GetMapping("no")
	public JsonResult No(Integer tid,String other) {
		String todays = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		Prove p = new Prove();
		Teacher t = teacherService.selectById(tid, "");
		p.setTid(tid);
		p.setStatus(0);
		p.setStatus1(0);
		p.setStatus2(0);
		proveService.update(p);
		teacherService.updateActivate(0,"",2,tid);
		teacherService.updateOther(other, tid);
		pushController.audit("老师注册审核结果通知", t.getOpenid(), t.getName(), todays, "暂不通过",other);
		return JsonResult.ok();
	}
	
	@GetMapping("export")
	public Result<Map<String, String>> exportTeacher(Teacher teacher) {
		List<Teacher> list = teacherService.selectByNamePro2(teacher);
		 Workbook workbook = new SXSSFWorkbook();
		 //设置sheet名称
	     Sheet sheet = workbook.createSheet("教师管理");
	     //设置样式     
	     CellStyle style = fileUtil.createStyle(workbook);
	     
	     /**
	      * 设置表头
	      */
	     Row row0 = sheet.createRow(0);
	     String[] colNames = new String[]{
	    		 "教师名称","联系号码","微信号","主教","价格","教龄","教师类型","教师性别","学历","毕业学校","专业","所在区域",
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
	    	   String sex = "女";
	    	   if("1".equals(list.get(j).getSex())) {
	    		   sex = "男";
	    	   }
	    	   fileUtil.cellSetUp(row,0,list.get(j).getName(), style);
	    	   fileUtil.cellSetUp(row,1,list.get(j).getPhone(), style);
	    	   fileUtil.cellSetUp(row,2,list.get(j).getWeChat(), style);
	    	   fileUtil.cellSetUp(row,3,list.get(j).getGrade(), style);
	    	   fileUtil.cellSetUp(row,4,list.get(j).getPrice().toString(), style);
	    	   fileUtil.cellSetUp(row,5,list.get(j).getSeniority(), style);
	    	   fileUtil.cellSetUp(row,6,list.get(j).getTypes(), style);    	    
	    	   fileUtil.cellSetUp(row,7,sex, style);
	    	   fileUtil.cellSetUp(row,8,list.get(j).getEducation(), style);
	    	   fileUtil.cellSetUp(row,9,list.get(j).getSchool(), style);
	    	   fileUtil.cellSetUp(row,10,list.get(j).getProfession(), style);
	    	   fileUtil.cellSetUp(row,11,list.get(j).getLocation(), style);
	       }
	         
	         
	       /**
			 * 3.将文件写入服务器目录
			 */
			String fileName = fileUtil.writeExcel(workbook,"教师管理");
			/**
			 * 4.返回文件下载地址
			 */
			Map<String, String> map = new HashMap<>();
			map.put("url", fileName);
			return new Result<>(map);	
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
