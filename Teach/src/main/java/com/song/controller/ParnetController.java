package com.song.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.song.service.ParentService;

@RestController
@RequestMapping("parent")
public class ParnetController {

	@Autowired
	private ParentService parentService;
	
	
	
}
