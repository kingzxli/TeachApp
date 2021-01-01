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
import com.song.service.MoneyService;
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
	@Autowired
	private MoneyService moneyService;
	
	@PostMapping("add")
	public JsonResult add(@RequestBody Punch punch,String openid) {
		String dateString = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		System.out.println("HHHHHHHHHHHHHH"+punch.getOpenId());
		Order o = orderService.selectByOrder(punch.getOrdernum());
		Teacher t = teacherService.selectById(punch.getTid(),openid);
		punch.setPopenid(o.getOpenId());
		System.out.println(punch.getTypes());
		if(punch.getTypes().equals("false")) {
			examService.delete(punch.getOrdernum());
			punService.insert(punch);
			String ordernum = punch.getOrdernum();
			//判断更改订单状态
			Integer count = punService.selectCount(ordernum);
			if(count.toString().equals(o.getNum())) {
				orderService.updateTimeStatus("已结束", ordernum);
			}
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
			updateLevel(t.getOpenid(),t.getJifen()+1);
			//加课时
			teacherService.updateNumber(String.valueOf(Integer.parseInt(t.getNumber())+1), t.getId());
			//获取这个单的应付款和课时数
			Order order = orderService.selectByOrderNum(ordernum);
			String totalfee = order.getTotalfee();
			String num = order.getNum();
			//找到推荐人
			Reward r = rewardServer.selectByOrdernum(ordernum);
			if(r!=null) {
				String ropenid = r.getShareId();
				//修改推荐人的金钱
				//Integer.parseInt(totalfee)/Integer.parseInt(num)每个打卡获得的钱
				// Math.floor(Integer.parseInt(totalfee)/Integer.parseInt(num))向下取整
				String money = String.valueOf(Integer.parseInt(totalfee)*0.0005/Integer.parseInt(num));
				System.out.println(money);
//				teacherService.updateOnId(money, ropenid);
				moneyService.add(ropenid,money);
			}
			try {
//				pushController.pushPunch(punch.getPopenid(),t.getName(),punch.getPname(),punch.getTimes(),punch.getId());
				pushController.sendPunch(openid, punch.getContent(), punch.getTimes(), t.getName());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return JsonResult.ok();
		}else {
			examService.delete(punch.getOrdernum());
			punService.insert(punch);
			String ordernum = punch.getOrdernum();
			Integer count = punService.selectCount(ordernum);
			if(count.toString().equals(o.getNum())) {
				orderService.updateTimeStatus("已结束", ordernum);
			}
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
			//加课时
			teacherService.updateNumber(String.valueOf(Integer.parseInt(t.getNumber())+1), t.getId());
			//获取这个单的应付款和课时数
			Order order = orderService.selectByOrderNum(ordernum);
			String totalfee = order.getTotalfee();
			String num = order.getNum();
			//找到推荐人
			Reward r = rewardServer.selectByOrdernum(ordernum);
			if(r!=null) {
				String ropenid = r.getOpenid();
				//修改推荐人的金钱
				String money = String.valueOf(Integer.parseInt(totalfee)*0.0005/Integer.parseInt(num));
				moneyService.add(ropenid,money);
			}
			try {
//				pushController.pushPunch(punch.getPopenid(),t.getName(),punch.getPname(),punch.getTimes(),punch.getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
			List<Violations> vi = violationsService.selectByTime(punch.getTimes());
			if(vi.size()==0) {
				Violations v = new Violations();
				v.setContent("补卡扣3分");
				v.setTid(t.getId());
				v.setAddtime(punch.getTimes());
				//加违规
				violationsService.add(v);
				//扣分
				teacherService.updateJifen(t.getJifen()-3,t.getId());
				updateLevel(t.getOpenid(),t.getJifen()-3);
			}
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
			if(punchs!=null) {
				punchs.setExams(e);
			}
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
	
	
	
	
	public void updateLevel(String openid,Integer jifen) {
		Teacher t = teacherService.selectByOpenid(openid);
		if(t.getTypes().equals("大学生")) {
			if(jifen<= 400) {
				teacherService.updateLevel(openid,"培优大学生老师");
			}
			if(401<=jifen && jifen<=900) {
				teacherService.updateLevel(openid,"优秀大学生老师");
				teacherService.updatePrice((int)(t.getPrice()+(t.getPrice()*0.1)), openid);
			}
			if(901<=jifen && jifen<=2000) {
				teacherService.updateLevel(openid,"高级大学生老师");
				teacherService.updatePrice((int)(t.getPrice()+(t.getPrice()*0.2)), openid);
			}
		}else {
			if(jifen <= 2000) {
				teacherService.updateLevel(openid,"培优老师");
			}
			if(2001<= jifen && jifen<=5000) {
				teacherService.updateLevel(openid,"优秀老师");
				teacherService.updatePrice((int)(t.getPrice()+(t.getPrice()*0.1)), openid);
			}
			if(5001 <= jifen && jifen<=8000) {
				teacherService.updateLevel(openid,"高级老师");
				teacherService.updatePrice((int)(t.getPrice()+(t.getPrice()*0.25)), openid);
			}
			if(8000<jifen) {
				teacherService.updateLevel(openid,"特级老师");
				teacherService.updatePrice((int)(t.getPrice()+(t.getPrice()*0.4)), openid);
			}
		}
	}
	
	
	
	
}
