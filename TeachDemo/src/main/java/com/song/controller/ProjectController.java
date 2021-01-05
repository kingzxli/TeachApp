package com.song.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.song.pojo.Project;
import com.song.service.ProjectService;
import com.song.util.JsonResult;

@RestController
@RequestMapping("project")
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
	
	@PostMapping("select")
	public JsonResult select() {
		List<Project> selectAll = projectService.selectAll();
		return JsonResult.ok(selectAll);
	}

}
