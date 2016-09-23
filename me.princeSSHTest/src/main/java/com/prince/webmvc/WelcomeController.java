package com.prince.webmvc;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
	
	@RequestMapping("/api/hello")
	public String index(){
		return "hello";
	}
}
