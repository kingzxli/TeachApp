package com.song.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.alibaba.fastjson.JSONObject;
import com.song.controller.PushController;
import com.song.controller.SyncController;
import com.song.entity.Assert;
import com.song.entity.Page;
import com.song.mapper.ParentMapper;
import com.song.mapper.SelectTeaMapper;
import com.song.mapper.TeacherMapper;
import com.song.pojo.Parent;
import com.song.pojo.SelectTea;
import com.song.pojo.SelectTeaVo;
import com.song.pojo.TeaSelect;
import com.song.pojo.Teacher;
import com.song.service.SelectTeaService;
import com.song.util.IdMaker;

@Service
public class SelectTeaServiceImpl implements SelectTeaService{
	private static String MRLIAO = "oY2Uc0f1SEvcTPN4GKOQYfuMuFP0";
	
	
	@Autowired
	private SelectTeaMapper selectTeaMapper;
	@Autowired
	private PushController pushController;
	@Autowired
	private ParentMapper parentMapper;
	@Autowired
	private TeacherMapper teacherMapper;
	@Autowired
	private SyncController syncController;
	

	@Override
	public void add(SelectTea selectTea) {		
		String todays = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		selectTea.setAddtime(todays);
		String s = selectTea.getSubject();
		String[] as = s.split("-");
		selectTea.setCulture(as[0]);
		selectTea.setGrade(as[1]);
		selectTea.setSubject(as[2]);
		

		String addredd = selectTea.getAddress();
		if(StringUtils.isNotEmpty(selectTea.getDetailed())) {
			String detail = selectTea.getDetailed().replace("#", "");

			addredd = addredd + detail;
		}
		
		String ll = this.getQQLngANdLat(addredd);
		String lat = ll.split(":")[0];
		String lng = ll.split(":")[1];
		selectTea.setLatitude(lat);
		selectTea.setLatlng(lng);
		selectTea.setId(IdMaker.get());
		selectTea.setSysCreated(new Date());
		selectTea.setSysCreatedBy(selectTea.getStuid().toString());
		selectTeaMapper.add(selectTea);
		
		Parent p = parentMapper.selectByid(selectTea.getStuid());
		pushController.sendMpMessage(MRLIAO, p.getName(), selectTea.getPhone(), selectTea.getAddress(), todays, selectTea.getTrial(),selectTea.getId(),selectTea.getSysCreatedBy());
		
		String[] arr = selectTea.getAddress().split("-");
		String city = arr[1];
		String area = arr[2];
		List<String> teacherOpenIds = teacherMapper.selectByProjectCity(city,area);
		if(teacherOpenIds != null && !teacherOpenIds.isEmpty()) {
			syncController.sentmsg(teacherOpenIds, as[2], selectTea.getTrial(), selectTea.getAddress(), selectTea.getGrade(),selectTea.getId(),selectTea.getSysCreatedBy());			
		}
		
		
	}

	@Override
	public List<SelectTea> selectAll(@Param("tea_type")String tea_type,@Param("address")String address,@Param("project")String project,@Param("tea_sex")String tea_sex) {
		// TODO Auto-generated method stub
		return selectTeaMapper.selectAll(tea_type, address, project, tea_sex);
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
	public SelectTea selectId(String id) {
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
		selectTeaMapper.updateStatus(id, status);
	}

	@Override
	public List<TeaSelect> selectStuta(int sid, String openid) {
		// TODO Auto-generated method stub
		return selectTeaMapper.selectStuta(sid, openid);
	}

	@Override
	public void updatePstatus(String id, int status) {
		// TODO Auto-generated method stub2
		selectTeaMapper.updatePstatus(id, status);
	}

	@Override
	public List<String> selectBySid(Integer sid,Integer id) {
		return selectTeaMapper.selectBySid(sid,id);
	}

	@Override
	public void addTeaSel(String topenid, int sid, Integer tid, Integer pid) {
		String addtime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		SelectTea st = selectTeaMapper.selectBySid2(sid);
		selectTeaMapper.addTeaSel(topenid, sid, addtime, tid, pid);
		pushController.yingpin("有老师应聘,请查看",st.getPopenid(), st.getGrade()+st.getSubject(),st.getTrial(), st.getAddress(), "待确认");
		pushController.yingpin("有老师应聘,请查看",MRLIAO, st.getGrade()+st.getSubject(),st.getTrial(), st.getAddress(), "待确认");
	}

	@Override
	public Set<Teacher> SelectTea(Integer pid) {
		// TODO Auto-generated method stub
		return selectTeaMapper.SelectTea(pid);
	}

	@Override
	public Set<Parent> SelectStu(Integer tid) {
		// TODO Auto-generated method stub
		return selectTeaMapper.SelectStu(tid);
	}

	@Override
	public void selectBySids(Integer id,Integer status,Integer sid,String topenid,String name) {
		SelectTea st = selectTeaMapper.selectBySids(sid);
		//更改是否同意接单状态(关系表)
		selectTeaMapper.updateStatus(id+"", status);
		//更改生源接单状态
		selectTeaMapper.updatePstatus(sid+"", status);
		if(status==1) {
			//查询待确认待关系表
			List<String> ts = selectTeaMapper.selectBySid(sid,id);
			//更改此单的其他老师显示被拒绝
			selectTeaMapper.updateStatusNo(id);		
			if(ts != null && !ts.isEmpty()) {
				syncController.sentMsgFalse(ts, st.getGrade(), st.getSubject(), st.getAddress());
			}

			pushController.parentPass("恭喜接单成功,请等待助教老师的通知",topenid,st.getGrade()+st.getSubject(),"详细时间请查看",st.getAddress(), "家长同意");
			pushController.parentPass("有家长同意接单请查看",MRLIAO,st.getGrade()+st.getSubject(),"详细时间请查看",st.getAddress(), "家长同意");
		}else {			
			pushController.parentPass("您应聘的单子已被接走,请应聘其他的单子",topenid,st.getGrade()+st.getSubject(),"详细时间请查看",st.getAddress(), "家长拒绝");
		}

	}

	@Override
	public void deleteSt(int id,int tid) {
		// TODO Auto-generated method stub
		selectTeaMapper.deleteSt(id,tid);
	}

	@Override
	public List<TeaSelect> selectSt(int id) {
		// TODO Auto-generated method stub
		return selectTeaMapper.selectSt(id);
	}

	@Override
	public List<SelectTea> selectAllDate() {
		return selectTeaMapper.selectAllDate();
	}

	@Override
	public void updateById(SelectTea selectTea) {
		selectTeaMapper.updateById(selectTea);
		
	}

	@Override
	public List<SelectTeaVo> selectByType2(Page page,Teacher teacher) {
		Teacher dbTeacher = teacherMapper.selectById2(teacher);		
		Assert.notNull(dbTeacher, "id传错,找不到老师");
		String type = dbTeacher.getTypes();
		if(!"大学生".equals(type)) {
			type = null;
		}
		//设置参数
		SelectTeaVo selectTea = new SelectTeaVo();
		selectTea.setAddress(teacher.getLocation());
		selectTea.setTeatype(type);
		selectTea.setSubject(teacher.getProject());
		selectTea.setStusex(teacher.getSex());

		Assert.isTrue(StringUtils.isNotEmpty(dbTeacher.getLatlng()), "老师的经纬度地址没有");
		selectTea.setLatlng(dbTeacher.getLatlng().split(",")[1]);
		selectTea.setLatitude(dbTeacher.getLatlng().split(",")[0]);
		
		page.paging();
		List<SelectTeaVo> list = selectTeaMapper.selectByType2(selectTea);
		if(list != null && !list.isEmpty()) {
			for(SelectTeaVo selectTeaVo :list) {
				selectTeaVo.setLevel(teacher.getLevel());
			}
		}
		
		return list;
	}

	@Override
	public void updateStatusNo(Integer id) {
		selectTeaMapper.updateStatusNo(id);
		
	}
	
	private  String getQQLngANdLat(String address) {
		RestTemplate restTemplate=new RestTemplate();
		String url="https://apis.map.qq.com/ws/geocoder/v1/?address="+address+"&region=北京"+
				"&key=BJFBZ-GUTE6-MBVSJ-M2SOK-43WT5-JFFYQ";
		JSONObject forObject = restTemplate.getForObject(url, JSONObject.class);
		System.out.println(forObject);     
		double lng = forObject.getJSONObject("result").getJSONObject("location").getDouble("lng");
		double lat = forObject.getJSONObject("result").getJSONObject("location").getDouble("lat");
		return lat +":"+ lng;
	}

	@Override
	public void updateDateNew() {
		List<SelectTea> list = selectTeaMapper.selectAllDate();
		for(SelectTea selectTea : list) {
			String addredd = selectTea.getAddress();
			if(StringUtils.isNotEmpty(selectTea.getDetailed())) {
				String detail = selectTea.getDetailed().replace("#", "");;

				addredd = addredd + detail;
			}
			String ll = this.getQQLngANdLat(addredd);
			String lat = ll.split(":")[0];
			String lng = ll.split(":")[1];
			selectTea.setLatitude(lat);
			selectTea.setLatlng(lng);
			selectTeaMapper.updateById(selectTea);			
		}
		
	}

}
