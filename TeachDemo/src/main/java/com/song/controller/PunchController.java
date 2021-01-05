package com.song.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.song.pojo.Exam;
import com.song.pojo.Order;
import com.song.pojo.Punch;
import com.song.pojo.Reward;
import com.song.pojo.Teacher;
import com.song.pojo.Violations;
import com.song.service.ExamService;
import com.song.service.OrderService;
import com.song.service.ParentService;
import com.song.service.PunchService;
import com.song.service.RewardService;
import com.song.service.TeacherService;
import com.song.service.ViolationsService;
import com.song.util.JsonResult;


@RestController
@RequestMapping("punch")
public class PunchController {
	
	@Autowired
	private PunchService punService;
	@Autowired
	private ExamService examService; 
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private ParentService parentService;
	@Autowired
	private ViolationsService violationsService;
	@Autowired
	private PushController pushController;
	@Autowired
	private OrderService orderService;
	@Autowired
	private RewardService rewardServer;
	
	@PostMapping("add")
	public JsonResult add(@RequestBody Punch punch,String openid) {
		String dateString = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		System.out.println("HHHHHHHHHHHHHH"+punch.getOpenId());
		Order o = orderService.selectByOrder(punch.getOrdernum());
		Teacher t = teacherService.selectById(punch.getTid(),openid);
		punch.setPopenid(o.getOpenId());
		System.out.println(punch.getTypes());
		if(punch.getTypes().equals("false")) {
			punService.insert(punch);
			String ordernum = punch.getOrdernum();
			System.out.println(ordernum);
			Exam[] exam = punch.getExam();
			if(exam!=null) {
				for(Exam e:exam) {
					if(e.getGrade()!=0 && e.getProject()!="" &&e.getTypes()!="") {
						e.setOrdernum(ordernum);
						e.setEtimes(dateString);
						examService.insert(e);
					}
				}
			}
			//加分
			teacherService.updateJifen(t.getJifen()+1, t.getId());
			//加课时
			teacherService.updateNumber(String.valueOf(Integer.parseInt(t.getNumber())+1), t.getId());
			//获取这个单的应付款和课时数
			Order order = orderService.selectByOrderNum(ordernum);
			String totalfee = order.getTotalfee();
			String num = order.getNum();
			//找到推荐人
			Reward r = rewardServer.selectByOrdernum(ordernum);
			String ropenid = r.getOpenid();
			//修改推荐人的金钱
			//Integer.parseInt(totalfee)/Integer.parseInt(num)每个打卡获得的钱
			// Math.floor(Integer.parseInt(totalfee)/Integer.parseInt(num))向下取整
			String money = String.valueOf(Math.floor(Integer.parseInt(totalfee)/Integer.parseInt(num)));
			teacherService.updateOnId(money, ropenid);
			return JsonResult.ok();
		}else {
			examService.delete(punch.getOrdernum());
			punService.insert(punch);
			String ordernum = punch.getOrdernum();
			Exam[] exam = punch.getExam();
			if(exam!=null) {
				for(Exam e:exam) {
					if(e.getGrade()!=0 && e.getProject()!="" &&e.getTypes()!="") {
						e.setOrdernum(ordernum);
						e.setEtimes(dateString);
						examService.insert(e);
					}
				}
			}
			//扣分
			teacherService.updateJifen(t.getJifen()-3,t.getId());
			//加课时
			teacherService.updateNumber(String.valueOf(Integer.parseInt(t.getNumber())+1), t.getId());
			//获取这个单的应付款和课时数
			Order order = orderService.selectByOrderNum(ordernum);
			String totalfee = order.getTotalfee();
			String num = order.getNum();
			//找到推荐人
			Reward r = rewardServer.selectByOrdernum(ordernum);
			String ropenid = r.getOpenid();
			//修改推荐人的金钱
			//Integer.parseInt(totalfee)/Integer.parseInt(num)每个打卡获得的钱
			// Math.floor(Integer.parseInt(totalfee)/Integer.parseInt(num))向下取整
			String money = String.valueOf(Math.floor(Integer.parseInt(totalfee)/Integer.parseInt(num)));
			teacherService.updateOnId(money, ropenid);
			Violations v = new Violations();
			v.setContent("补卡扣3分");
			v.setTid(t.getId());
			v.setAddtime(punch.getTimes());
			//加违规
			violationsService.add(v);
			return JsonResult.ok();
		}
	}
	
	@PostMapping("selectAll")
	public Object selectAll(String month,String year,String ordernum) {
		Set<String> punch = punService.selectAll(month,year,ordernum);
		Map<Object,Object> map = new HashMap<Object, Object>();
		String todays = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
		map.put("times",punch);
		map.put("now", todays);
		map.put("status", 200);
		return map;
	}
	
	
	@PostMapping("select")
	public JsonResult select(String times,String ordernum,String status) {
		
		if(status.equals("true")) {
			Punch punch = punService.selectByOrderTime(times, ordernum);
			return JsonResult.ok(punch);
		}else {
			Punch punchs = punService.selectByOrderTimeF(times, ordernum);
			List<Exam> e = examService.selectById(ordernum);
			punchs.setExams(e);
			return JsonResult.ok(punchs);
		}
	}
	
	
	
	@PostMapping("delete")
	public JsonResult delete(String times) {
		punService.delete(times);
		return JsonResult.ok();
	}
	
	
	@PostMapping("update")
	public JsonResult update(@RequestBody Punch punch) {
		punService.update(punch);
		examService.delete(punch.getOrdernum());
		Exam[] exam = punch.getExam();
		for(Exam e:exam) {
			if(e!=null) {
				e.setOrdernum(punch.getOrdernum());
				e.setEtimes(punch.getTimes());
				examService.insert(e);
			}
		}
		return JsonResult.ok();
	}
	
	
	@PostMapping("selectime")
	public JsonResult slectime(String openid) {
		List<Punch> time = punService.selectTime(openid);
		return JsonResult.ok(time);
	}
	
	
	@PostMapping("selectById")
	public JsonResult selectById(int id) {
		Punch p = punService.selectById(id);
		Punch punch = punService.selectByOrderTime(p.getTimes(), p.getOrdernum());
		return JsonResult.ok(punch);
	}
	
	
	
	
}
