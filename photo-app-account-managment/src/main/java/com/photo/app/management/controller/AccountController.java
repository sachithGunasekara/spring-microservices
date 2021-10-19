package com.photo.app.management.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("account")
public class AccountController {

	@GetMapping(path = "/status/check")
	public String getStatus() {
		return "working";
	}
}
