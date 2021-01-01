package com.song.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class SyncController {
	
	@Autowired
	private PushController pushController;
	
	/**
	 * 异步发送订单消息
	 * @param teacherOpenIds
	 * @param subject
	 * @param Trial
	 * @param adress
	 * @param grade
	 */
	@Async
	public void sentmsg(List<String> teacherOpenIds,String subject,String Trial,String adress,String grade) {
		for(String teacherOpenId : teacherOpenIds) {
			pushController.sendMessage(teacherOpenId, subject, Trial, adress, grade);
		}				
	}
	
	/**
	 * 家长拒绝订单消息
	 * @param teacherOpenIds
	 * @param subject
	 * @param Trial
	 * @param adress
	 * @param grade
	 */
	@Async
	public void sentMsgFalse(List<String> openIds,String grade,String subject,String adress) {
		for(String tOpenId : openIds) {
			pushController.parentPass("您应聘的单子已被接走,请应聘其他的单子",tOpenId,grade+subject,"详细时间请查看",adress, "家长拒绝");
		}			
	}

}
