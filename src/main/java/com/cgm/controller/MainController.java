package com.cgm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcomePage(Model model) {
		return "welcomePage";
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String indexPage(Model model) {
		return "indexPage";
	}

	@RequestMapping(value = "/news/login", method = RequestMethod.GET)
	public String loginPage(Model model) {
		return "login";
	}

	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public String AboutPage(Model model) {
		return "about";
	}
}