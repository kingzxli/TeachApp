package com.song.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.song.pojo.Lesson;
import com.song.service.LessonService;
import com.song.util.JsonResult;

@RestController
@RequestMapping("lesson")
public class LessonController {
	
	@Autowired
	private LessonService lessonService;
	
	/**
	 * 授课设置
	 */
	@PostMapping("insert")
	public JsonResult add(Lesson lesson) {
		Lesson l = lessonService.selectById(lesson.getTid());
		if(l==null) {
			lessonService.insert(lesson);
			return JsonResult.ok();
		}else {
			lessonService.update(lesson);
			return JsonResult.ok();
		}
	}

}
