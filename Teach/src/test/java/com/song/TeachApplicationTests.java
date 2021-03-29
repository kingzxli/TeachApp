package com.song;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.song.controller.PushController;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TeachApplicationTests {
	@Autowired
	private PushController pushController;
	
	@Test
	public void contextLoads() {
		//pushController.sendMessage("oY2Uc0WW-LTavEV58eA6Jc0zoJHE", "23", "22", "22", "22","710","2727");
		pushController.yingpin("有老师应聘,请查看!(家长电话："+"13265737220"+")","oY2Uc0SOK24q0AoJsjxaYyryZYYc", "年级科目","类型", "广东省广州市", "待确认");
		//pushController.sendMpMessage("oY2Uc0SOK24q0AoJsjxaYyryZYYc", "家长姓名", "13233333333", "广东省广州市", "2021-03-08", "语文","23","323434");

	}
	
	

}
