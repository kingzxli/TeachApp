package com.song.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.song.pojo.Lesson;
import com.song.pojo.Prove;
import com.song.pojo.Teacher;
import com.song.service.LessonService;
import com.song.service.ProveService;
import com.song.service.TeacherService;
import com.song.util.JsonResult;


/**
 * 
 * @author Song
 *
 */

@RestController
@RequestMapping("prove")
public class ProveController {
	
	@Autowired
	private ProveService proveService;
	@Autowired
	private LessonService lessonService;
	@Autowired
	private TeacherService teacherService;
	
	//通过type，用户id查询
	@PostMapping("select")
	public JsonResult select(int type,int tid) {
		try {
			if(type==1) {
				Prove p = proveService.selectByIdcard(tid);
				return JsonResult.ok(p);
			}else if (type==2) {
				Prove p = proveService.selectByTeach(tid);
				return JsonResult.ok(p);
			}else if(type==3) {
				Prove p = proveService.selectByStudent(tid);
				return JsonResult.ok(p);
			}else if(type==4) {
				Lesson l = lessonService.selectById(tid);
				if(l==null) {
					return JsonResult.ok("");
				}else {
					net.sf.json.JSONObject json = net.sf.json.JSONObject.fromObject(l);
					net.sf.json.JSONArray arr = net.sf.json.JSONArray.fromObject(json.get("times"));
					System.out.println(arr);
					Map<Object,Object> map = new HashMap<Object, Object>();
					map.put("lesson", l);
					map.put("times", arr);
					return JsonResult.ok(map);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return JsonResult.errorMap("请求失败");
	}
	
	//上传数据到数据库
//	@PostMapping("add")
//	public JsonResult add(Prove prove) {
//		System.out.println(prove.toString());
//		Teacher teacher = new Teacher();
//		prove.setId(prove.getTid());
//		proveService.update(prove);
//		teacherService.updateActivate(0, prove.getTid());
//		proveService.updateStatus(prove.getId());
//		if(prove.getSex().equals("男")) {
//			teacher.setSex("1");
//			teacher.setPhone(prove.getPhone());
//			teacher.setId(prove.getTid());
//			teacherService.update(teacher);
//		}else {
//			teacher.setSex("0");
//			teacher.setPhone(prove.getPhone());
//			teacher.setId(prove.getTid());
//			teacherService.update(teacher);
//		}
//		return JsonResult.ok();
//	}
	@PostMapping("add")
	public JsonResult add(Prove prove) {
		System.out.println(prove.toString());
		Teacher teacher = new Teacher();
		prove.setId(prove.getTid());
		System.out.println("-------------------------------"+prove.toString());
		proveService.update(prove);
		teacherService.updateActivate(0, prove.getTid());
		proveService.updateStatus(prove.getId());
		if(prove.getSex().equals("男")) {
			teacher.setSex("1");
			teacher.setPhone(prove.getPhone());
			teacher.setId(prove.getTid());
			teacherService.update(teacher);
		}else {
			teacher.setSex("0");
			teacher.setPhone(prove.getPhone());
			teacher.setId(prove.getTid());
			teacherService.update(teacher);
		}
		return JsonResult.ok();
	}
	
	//添加教师资格证或者毕业证
	@PostMapping("/update")
	public JsonResult update(Prove prove) throws Exception {
		System.out.println(prove.toString());
		proveService.update(prove);
		teacherService.updateActivate(0, prove.getId());
		if(prove.getTeachimage()!=null && prove.getTeachimage()!="") {
			proveService.updateStatus1(prove.getId());
		}
		if(prove.getGraduateimage()!=null && prove.getGraduateimage()!="") {
			proveService.updateStatus2(prove.getId());
		}
		return JsonResult.ok();
	}
	
	
	//小程序图片上传
	@PostMapping("/uploadimg")
	public JsonResult uploadimg(MultipartFile file){
		if (file.isEmpty()) {
            return JsonResult.errorMap("上传失败");
        }
		 String fileName = file.getOriginalFilename();
	        String filePath = "D:\\phpStudy\\WWW\\image\\";
	        File dest = new File(filePath + fileName);
	        try {
	            file.transferTo(dest);
	            System.out.println("上传成功");
	            String path = "http://www.xurijiajiao.cn/image/"+fileName;
 	            return JsonResult.ok(path);
	        } catch (IOException e) {
	            System.out.println("上传失败");
	            System.out.println(e.getMessage());
	        }
	        return JsonResult.errorMap("上传失败");
	}
	
	
	
}
