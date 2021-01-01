package com.song.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.song.pojo.Collect;
import com.song.pojo.Exam;
import com.song.pojo.Teacher;
import com.song.service.CollectService;
import com.song.service.ExamService;
import com.song.service.TeacherService;
import com.song.util.JsonResult;

@RestController
@RequestMapping("exam")
public class ExamController {
	
	@Autowired
	private ExamService examService;
	
	@PostMapping("update")
	public JsonResult update(Exam e) {
		examService.update(e);
		return JsonResult.ok();
	}
	
}
