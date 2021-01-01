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
		System.out.println("222");	
		pushController.sendMessage("oY2Uc0SYqUuomMkdkhEiVdBzMUow", "22", "22", "22", "22");
		System.out.println("结束");
	}
	
	

}
