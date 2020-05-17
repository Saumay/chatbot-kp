package com.trial.chatbotweb.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class ChatController {

	@Autowired
	ReplyGenerator replyGenerator;

	@RequestMapping("/chat")
	@ResponseBody
	public String receiveMessage(@RequestParam String message) {
		log.info("Received message: " + message);
		return replyGenerator.generateReply(message);
	}
}
