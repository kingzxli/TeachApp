package com.song.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.song.mapper.TeacherMapper;
import com.song.pojo.Teacher;
import com.song.service.TeacherService;

/**
 * 
 * @author Song
 *
 */
@Service
public class TeacherServiceImpl implements TeacherService{

	@Autowired
	private TeacherMapper teacherMapper;

	@Override
	public int add(Teacher teacher) {
		// TODO Auto-generated method stub
		return teacherMapper.add(teacher);
	}

	@Override
	public Teacher selectByOpenid(String openid) {
		// TODO Auto-generated method stub
		return teacherMapper.selectByOpenid(openid);
	}

	@Override
	public Teacher selectById(int id,String openid) {
		// TODO Auto-generated method stub
		return teacherMapper.selectById(id,openid);
	}

	@Override
	public List<Teacher> selectAll(String grade, String project, String sex, String types) {
		// TODO Auto-generated method stub
		return teacherMapper.selectAll(grade, project, sex, types);
	}

	@Override
	public void update(Teacher teacher) {
		teacherMapper.update(teacher);
	}

	@Override
	public void updateschool(Teacher teacher) {
		// TODO Auto-generated method stub
		teacherMapper.updateschool(teacher);
	}

	@Override
	public void updatetitle(Teacher teacher) {
		// TODO Auto-generated method stub
		teacherMapper.updatetitle(teacher);
	}

	@Override
	public void updatehonor(Teacher teacher) {
		// TODO Auto-generated method stub
		teacherMapper.updatehonor(teacher);
	}

	@Override
	public void updatexperience(Teacher teacher) {
		// TODO Auto-generated method stub
		teacherMapper.updatexperience(teacher);
	}

	@Override
	public void updatesuccess(Teacher teacher) {
		// TODO Auto-generated method stub
		teacherMapper.updatesuccess(teacher);
	}

	@Override
	public void updatecharacter(Teacher teacher) {
		// TODO Auto-generated method stub
		teacherMapper.updatecharacter(teacher);
	}

	@Override
	public void updatephoto(Teacher teacher) {
		// TODO Auto-generated method stub
		teacherMapper.updatephoto(teacher);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		teacherMapper.delete(id);
	}

	@Override
	public List<Teacher> selectindex() {
		// TODO Auto-generated method stub
		return teacherMapper.selectindex();
	}

	@Override
	public void updateJifen(int jifen, int id) {
		// TODO Auto-generated method stub
		teacherMapper.updateJifen(jifen, id);
	}

	@Override
	public void updateIdentity(String online, String openid) {
		// TODO Auto-generated method stub
		teacherMapper.updateIdentity(online, openid);
	}

	@Override
	public Teacher selectByOnId(String online, int id) {
		// TODO Auto-generated method stub
		return teacherMapper.selectByOnId(online, id);
	}

	@Override
	public Double selectMoney(String openid) {
		// TODO Auto-generated method stub
		return teacherMapper.selectMoney(openid);
	}

	@Override
	public List<Teacher> selectByCondition(String project) {
		// TODO Auto-generated method stub
		return teacherMapper.selectByCondition(project);
	}

	@Override
	public void updateNumber(String number, int id) {
		// TODO Auto-generated method stub
		teacherMapper.updateNumber(number, id);
	}

	@Override
	public void updateOnId(String money, String openid) {
		// TODO Auto-generated method stub
		teacherMapper.updateOnId(money, openid);
	}

	@Override
	public List<Teacher> selectByZero(Teacher teahcer) {
		return teacherMapper.selectByZero(teahcer);
	}

	@Override
	public List<Teacher> selectByNamePro(Teacher teacher) {
		return teacherMapper.selectByNamePro(teacher);
	}

	@Override
	public void updateProve(int status,int tid) {
		// TODO Auto-generated method stub
		teacherMapper.updateProve(status,tid);
	}


	@Override
	public void updateOther(String other, int tid) {
		// TODO Auto-generated method stub
		teacherMapper.updateOther(other, tid);
	}

	@Override
	public void updateActivate(Integer price, String otherpri, Integer activate, Integer id) {
		// TODO Auto-generated method stub
		teacherMapper.updateActivate(price, otherpri, activate, id);
	}

	@Override
	public List<Teacher> selectByActivate(Teacher teacher) {
		return teacherMapper.selectByActivate(teacher);
	}

	@Override
	public List<Teacher> selectByNamePro2(Teacher teacher) {
		
		return teacherMapper.selectByNamePro2(teacher);
	}



}
