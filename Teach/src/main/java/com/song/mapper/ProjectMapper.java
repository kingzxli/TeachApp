package com.song.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.song.pojo.Project;

@Mapper
public interface ProjectMapper {

	List<Project> selectAll();
	
}
