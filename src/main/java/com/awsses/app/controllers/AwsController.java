package com.awsses.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.awsses.app.services.AwsSesService;

@RestController
@RequestMapping(value="/api")
public class AwsController {
	
	@Autowired
	private AwsSesService service;
	
	
	@GetMapping(value = "/sendmail")
	public ResponseEntity<?> sendMail(){
		String responseId = service.sendMailRaw();
		return ResponseEntity.ok(responseId);
	}
	
	@GetMapping(value = "/sendmailtemplate")
	public ResponseEntity<?> sendMailTemplate(){
		String responseId = service.sendEmailFromTemplate();
		return ResponseEntity.ok(responseId);
	}

	
}
