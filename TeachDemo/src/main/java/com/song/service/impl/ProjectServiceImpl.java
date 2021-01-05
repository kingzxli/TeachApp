package com.song.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.song.mapper.ProjectMapper;
import com.song.pojo.Project;
import com.song.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService{
	
	@Autowired
	private ProjectMapper projectMapper;

	@Override
	public List<Project> selectAll() {
		// TODO Auto-generated method stub
		return projectMapper.selectAll();
	}
	
}
