package com.song.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.song.pojo.Comment;
import com.song.pojo.Parent;
import com.song.service.CommentService;
import com.song.service.OrderService;
import com.song.service.ParentService;
import com.song.util.JsonResult;

@RestController
@RequestMapping("comment")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private ParentService parentService;
	
	//添加评论
	@PostMapping("add")
	public JsonResult add(Comment comment) {
		String todays = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		comment.setAddtime(todays);
		Parent p = parentService.selectByid(comment.getPid());
		comment.setPimage(p.getImage());
		comment.setPname(p.getName());
		commentService.add(comment);
		//改评论状态
		orderService.updateComment(comment.getOrdernum());
		return JsonResult.ok();
	}

	//老师详情里面的评论
	@PostMapping("select")
	public JsonResult select(int tid,int pageId,int pageSize) {
		PageHelper.startPage(pageId,pageSize);
		List<Comment> c = commentService.select(tid);
		if(c!=null) {
			for(int i=0;i<c.size();i++) {
				String ordernum = c.get(i).getOrdernum();
				List<Comment> co = commentService.selectByOrder(tid, ordernum);
				PageInfo<Comment> page = new PageInfo<Comment>();
				page.setPageNum(pageId);
				page.setPageSize(pageSize);
				page.setSize(co.size());
				page.setList(co);
				return JsonResult.ok(page);
			}
		}
		return JsonResult.ok();
	}
	
}
