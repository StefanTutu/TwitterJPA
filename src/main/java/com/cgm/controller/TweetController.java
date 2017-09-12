package com.cgm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.cgm.db.repo.DBTweetDAO;
import com.cgm.db.repository.contract.TweetDataStore;
import com.cgm.domain.Tweet;

import java.util.List;

@Controller
public class TweetController {

	@Autowired
	private DBTweetDAO tweetDAO; // JPA

	@Autowired
	private TweetDataStore tweetDataStore; // JDBC

	@RequestMapping(value = "/tweet/new", method = RequestMethod.GET)
	public ModelAndView newTweet(ModelAndView model) {
		Tweet newTweet = new Tweet();
		model.addObject("tweet", newTweet);
		model.setViewName("tweets/newTweetPage");
		return model;
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/tweet/create", method = RequestMethod.POST, produces = { "application/json",
			"application/xml" })
	@ResponseBody
	public ResponseEntity createTweet(@ModelAttribute("tweetForm") Tweet tweet) {
		int result = tweetDAO.add(tweet);
		if (result == 1) {
			return ResponseEntity.ok("{\"message\": \"Success!\"}");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"Error!\"}");
		}
	}

	@RequestMapping(value = "/tweets/formatted", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView tweets(ModelAndView model,
			@RequestParam(value = "search", defaultValue = "", required = false) String search) {
		List<Tweet> listTweets = tweetDAO.searchTweets(search);
		model.addObject("listTweets", listTweets);
		model.addObject("search", search);
		model.setViewName("tweets/tweetsPage");
		return model;
	}

	@RequestMapping(value = "/tweets/{username}/formatted", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView tweetsUser(ModelAndView model, @PathVariable String username,
			@RequestParam(value = "search", defaultValue = "", required = false) String search) {
		List<Tweet> listTweets = tweetDAO.searchUserTweets(username, search);
		model.addObject("listTweets", listTweets);
		model.addObject("username", username);
		model.addObject("search", search);
		model.setViewName("tweets/tweetsPage");
		return model;
	}

	@RequestMapping(value = "/tweets", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Tweet> tweets_JSON(@RequestParam(value = "search", defaultValue = "", required = false) String search) {
		List<Tweet> listTweets = tweetDAO.searchTweets(search);
		return listTweets;
	}

	@RequestMapping(value = "/tweets/{username}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Tweet> tweetsUser_JSON(@PathVariable String username,
			@RequestParam(value = "search", defaultValue = "", required = false) String search) {
		List<Tweet> listTweets = tweetDAO.searchUserTweets(username, search);
		return listTweets;
	}
}