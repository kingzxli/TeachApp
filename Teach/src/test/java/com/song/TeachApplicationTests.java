package com.song;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TeachApplicationTests {

	
	@Test
	public void contextLoads() {
		System.out.println("222");	
		String[] arr = "河南省-安阳市-文峰区".split("-");
		String city = arr[1];
		System.out.println("结束");
	}
	
	

}
