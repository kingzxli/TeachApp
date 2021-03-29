package com.song.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.song.mapper.SelectTeaMapper;
import com.song.pojo.SelectTea;
import com.song.pojo.TeaSelect;
import com.song.service.SelectTeaService;

@Service
public class SelectTeaServiceImpl implements SelectTeaService{
	
	@Autowired
	private SelectTeaMapper selectTeaMapper;

	@Override
	public void add(SelectTea selectTea) {
		// TODO Auto-generated method stub
		selectTeaMapper.add(selectTea);
	}

	@Override
	public List<SelectTea> selectAll() {
		// TODO Auto-generated method stub
		return selectTeaMapper.selectAll();
	}

	@Override
	public List<SelectTea> selectById(int id) {
		// TODO Auto-generated method stub
		return selectTeaMapper.selectById(id);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		selectTeaMapper.delete(id);
	}

	@Override
	public SelectTea selectId(int id) {
		// TODO Auto-generated method stub
		return selectTeaMapper.selectId(id);
	}

	@Override
	public List<SelectTea> selectByType(String tea_type, String address, String project, String tea_sex) {
		// TODO Auto-generated method stub
		return selectTeaMapper.selectByType(tea_type, address, project, tea_sex);
	}

	@Override
	public List<TeaSelect> selectByPid(Integer pid) {
		// TODO Auto-generated method stub
		return selectTeaMapper.selectByPid(pid);
	}

	@Override
	public List<TeaSelect> selectByTopenid(String openid) {
		// TODO Auto-generated method stub
		return selectTeaMapper.selectByTopenid(openid);
	}

	@Override
	public void updateStatus(String id, int status) {
		// TODO Auto-generated method stub
		selectTeaMapper.updateStatus(id, status);
	}

	@Override
	public TeaSelect selectStuta(int sid, String openid) {
		// TODO Auto-generated method stub
		return selectTeaMapper.selectStuta(sid, openid);
	}

	@Override
	public void addTeaSel(String topenid, int sid, String addtime, Integer tid) {
		// TODO Auto-generated method stub
		selectTeaMapper.addTeaSel(topenid, sid, addtime, tid);
	}

	@Override
	public void updatePstatus(String id, int status) {
		// TODO Auto-generated method stub
		selectTeaMapper.updatePstatus(id, status);
	}


	@Override
	public List<SelectTea> selectBystatus(String sid) {
		return selectTeaMapper.selectBystatus(sid);
	}

	@Override
	public List<SelectTea> selectall(SelectTea selectTea) {
		return selectTeaMapper.selectall(selectTea);
	}






}
