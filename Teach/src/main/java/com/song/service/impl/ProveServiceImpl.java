package com.song.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.song.mapper.ProveMapper;
import com.song.mapper.TeacherMapper;
import com.song.pojo.Prove;
import com.song.pojo.Teacher;
import com.song.service.ProveService;

@Service
public class ProveServiceImpl implements ProveService{
	
	@Autowired
	private ProveMapper proveMapper;
	@Autowired
	private TeacherMapper teacherMapper;

	@Override
	public int add(Prove prove) {
		// TODO Auto-generated method stub
		return proveMapper.add(prove);
	}
	
	@Override
	public void addNew(Prove prove) {
		prove.setId(prove.getTid());
		prove.setStatus(1);
		proveMapper.update(prove);
		
		Teacher teacher = new Teacher();
		if("男".equals(prove.getSex())) {
			teacher.setSex("1");
		}else if("女".equals(prove.getSex())) {
			teacher.setSex("0");
		}
		teacher.setPhone(prove.getPhone());
		teacher.setId(prove.getTid());
		teacher.setActivate(prove.getActivate());
		teacherMapper.update(teacher);
	}

	@Override
	public void update(Prove prove) {
		proveMapper.update(prove); 
	}

	@Override
	public Prove selectByIdcard(int tid) {
		// TODO Auto-generated method stub
		return proveMapper.selectByIdcard(tid);
	}

	@Override
	public Prove selectByTeach(int tid) {
		// TODO Auto-generated method stub
		return proveMapper.selectByTeach(tid);
	}

	@Override
	public Prove selectByStudent(int tid) {
		// TODO Auto-generated method stub
		return proveMapper.selectByStudent(tid);
	}

	@Override
	public void updateStatus(int tid) {
		// TODO Auto-generated method stub
		proveMapper.updateStatus(tid);
	}

	@Override
	public void updateStatus1(int tid) {
		// TODO Auto-generated method stub
		proveMapper.updateStatus1(tid);
	}

	@Override
	public void updateStatus2(int tid) {
		// TODO Auto-generated method stub
		proveMapper.updateStatus2(tid);
	}

}
