package com.jzx.config.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {
	@RequestMapping("index.html")
	public String index() {
		return "index";
	}

	@RequestMapping("/index/query.html")
	public String query() {
		return "query";
	}
}
