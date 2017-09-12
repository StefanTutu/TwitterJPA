package com.cgm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.cgm.domain.Tweet;
import com.cgm.domain.User;
import com.cgm.dto.ServiceResponse;
import com.cgm.repository.UserDAO;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

@RestController
public class TweetController {

	@Autowired
	UserDAO userDAO;
	
	@RequestMapping(value="/tweet/{id}", method = RequestMethod.GET)
	public @ResponseBody User newUser(@PathVariable("id") Long id) {
		return userDAO.findById(id);
	}
	
	@RequestMapping(value="/tweet/users", method = RequestMethod.GET)
	public @ResponseBody List<Tweet> tweetUser( User user, HttpServletRequest request) {
		String username=request.getSession().getAttribute("username").toString();
		return userDAO.findByName(username).getTweet();
		
	}
	
	@RequestMapping(value="/mesaj/message", method=RequestMethod.PUT)
	public @ResponseBody  Object ServiceResponse(@RequestBody Tweet tweet, HttpServletRequest request){
		String username=request.getSession().getAttribute("username").toString();
		User user = userDAO.findByName(username);
		tweet.setUsername(username);
		tweet.setUser(user);
		user.getTweet().add(tweet);
		userDAO.update(user);
		return tweet;
	}
	

/*	@RequestMapping(value = "/tweet/new", method = RequestMethod.GET)
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
	}*/
}