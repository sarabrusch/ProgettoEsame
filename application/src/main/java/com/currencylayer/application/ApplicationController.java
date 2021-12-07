package com.currencylayer.application;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ApplicationController {

	@RequestMapping("/benvenuto")
	public @ResponseBody String hello() {
		return "Benvenuto";
	}
}
