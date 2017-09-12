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

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(Model model) {
		return "login";
	}

	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public String AboutPage(Model model) {
		return "about";
	}

	@RequestMapping(value = "/tweets/formatted", method = RequestMethod.GET)
	public String TweetPage(Model model) {
		return "tweets/tweetsPage";
	}
	
	@RequestMapping(value = "/tweet/new", method = RequestMethod.GET)
	public String TweetPageFormatted(Model model) {
		return "tweets/newTweetPage";
	}
	
	@RequestMapping(value = "/following/formatted", method = RequestMethod.GET)
	public String FollowingPage(Model model) {
		return "follows/followingPage";
	}
	
	@RequestMapping(value = "/followers/formatted", method = RequestMethod.GET)
	public String FollowersPage(Model model) {
		return "follows/followersPage";
	}
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String FollowePage(Model model) {
		return "follows/followPage";
	}
}
