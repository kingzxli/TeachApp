package com.song.controller;

import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.github.pagehelper.PageInfo;
import com.song.entity.Page;
import com.song.entity.Result;
import com.song.pojo.Parent;
import com.song.pojo.SelectTea;
import com.song.pojo.SelectTeaVo;
import com.song.pojo.TeaSelect;
import com.song.pojo.Teacher;
import com.song.service.SelectTeaService;
import com.song.util.JsonResult;


@RestController
@RequestMapping("selecttea")
public class SelectTeaController {
	
	@Autowired
	private SelectTeaService SelectteaService;
		
	//查生源接单我的老师
	@PostMapping("selectTea")
	public JsonResult selectTea(Integer pid) {
		Set<Teacher> t = SelectteaService.SelectTea(pid);
		return JsonResult.ok(t);
	}
	
	//查生源接单我的学生
	@PostMapping("selectStu")
	public JsonResult selectStu(Integer tid) {
		Set<Parent> p = SelectteaService.SelectStu(tid);
		return JsonResult.ok(p);
	}
	
	
	/**
	 * 家长接受或者拒绝单
	 * @param id
	 * @param status
	 * @param sid
	 * @param topenid
	 * @param name
	 * @return
	 */
	@PostMapping("updatestatus")
	public JsonResult updateStutas(Integer id,Integer status,Integer sid,String topenid,String name){		
		SelectteaService.selectBySids(id,status,sid,topenid,name);
		return JsonResult.ok();
	}
	
	
	
	//生源接单查看是否接单
	@PostMapping("selectstatus")
	public JsonResult selectStatus(int sid,String openid) {
		List<TeaSelect> ts = SelectteaService.selectStuta(sid, openid);
		return JsonResult.ok(ts);
	}
	
	
	//老师查看关于自己的单
	@PostMapping("selectByTopenid")
	public JsonResult selectByTopenid(String topenid) {
		List<TeaSelect> ts = SelectteaService.selectByTopenid(topenid);
		return JsonResult.ok(ts);
	}
	
	
	//家长查看接单
	@PostMapping("selectByPid")
	public JsonResult selectByPid(String openid,int pid) {
		List<TeaSelect> ts = SelectteaService.selectByPid(pid);
		return JsonResult.ok(ts);
	}
	
	//老师接单
	@PostMapping("addTeaSel")
	public JsonResult addTeaSel(String topenid,Integer sid,Integer tid,Integer pid){
		SelectteaService.addTeaSel(topenid, sid,tid,pid);
		return JsonResult.ok();
	}
	
	
	//老师取消接单
	@PostMapping("cancel")
	public JsonResult cancel(int id,Integer tid) {
		System.out.println(id+"----"+tid);
		List<TeaSelect> ts = SelectteaService.selectSt(id);
		for(int i=0 ; i<ts.size(); i++ ) {
			if(ts.get(i).getStatus()==0||ts.get(i).getStatus()==2) {
				SelectteaService.deleteSt(id,tid);
				return JsonResult.ok();
			}else {
				return JsonResult.build(180, "家长已同意无法删除", "");
			}
		}
		return null;
	}
	
	
	//通过数据id查详情
	@PostMapping("selectid")
	public JsonResult selectid(String id) {
		SelectTea st = SelectteaService.selectId(id);
		return JsonResult.ok(st);
	}
		
	
	//删除
	@PostMapping("delete")
	public JsonResult delete(String id) {
		SelectteaService.delete(id);
		SelectteaService.updatePstatus(id, 2);
		SelectteaService.updateStatus(id, 2);
		return JsonResult.ok();
	}
	
	
	//根据学生id查 
	@PostMapping("selectbyid")
	public JsonResult selectById(int id) {
		List<SelectTea> list = SelectteaService.selectById(id);
		return JsonResult.ok(list);
	}
	
	

	/**
	 * 帮我找老师数据添加
	 * @param selectTea
	 * @return
	 */
	@GetMapping("/add")
	public JsonResult add(SelectTea selectTea){
		SelectteaService.add(selectTea);	
		return JsonResult.ok();
	}
	
	/**
	 * 更新数据
	 */
	@GetMapping("select2")
	public void select2() {
		SelectteaService.updateDateNew();
	}


	/**
	 * 生源接单获取
	 * @param page
	 * @param teacher
	 * @return
	 */
	@GetMapping("selectNew")
	public Result<List<SelectTeaVo>> selectNew(Page page,Teacher teacher) {		
		List<SelectTeaVo> list = SelectteaService.selectByType2(page,teacher);
		return new Result<>(list).total(new PageInfo<SelectTeaVo>(list).getTotal());

	}

	
	
}

