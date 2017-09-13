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
import com.cgm.repository.TweetDAO;
import com.cgm.repository.UserDAO;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

@RestController
public class TweetController {

	@Autowired
	UserDAO userDAO;

	@Autowired
	TweetDAO tweetDAO;

	@RequestMapping(value = "/tweet/{id}", method = RequestMethod.GET)
	public @ResponseBody User newUser(@PathVariable("id") Long id) {
		return userDAO.findById(id);
	}

	@RequestMapping(value = "/tweet/users", method = RequestMethod.GET)
	public @ResponseBody List<Tweet> tweetUser(User user, HttpServletRequest request) {
		String username = request.getSession().getAttribute("username").toString();
		return userDAO.findByName(username).getTweet();

	}

	@RequestMapping(value = "/mesaj/message", method = RequestMethod.PUT)
	public @ResponseBody Object ServiceResponse(@RequestBody Tweet tweet, HttpServletRequest request) {
		String username = request.getSession().getAttribute("username").toString();
		User user = userDAO.findByName(username);
		tweet.setUsername(username);
		tweet.setUser(user);
		user.getTweet().add(tweet);
		userDAO.update(user);
		return tweet;
	}

	@RequestMapping(value = "tweets/formatted/{username}", method = RequestMethod.GET)
	public ModelAndView getUsers(@PathVariable("username") String userName, Model model) {
		if (userName != null && userDAO.findByName(userName) != null) {
			User user = userDAO.findByName(userName);
			return new ModelAndView("tweets/tweetsPage", "userSearch", user.getUsername());
		} else {
			return new ModelAndView("tweets/tweetsPage", "userSearch", "not found");
		}
	}

	@RequestMapping(value = "tweets/formatted/new/{tweet}/", method = RequestMethod.GET)
	public ModelAndView getUsersTweet(@PathVariable("tweet") Tweet tweet, String search, Model model) {
		if (search != null) {
			tweet.getTweet().equalsIgnoreCase(search);
			return new ModelAndView("tweets/tweetsPage", "Search", tweet.getTweet());
		} else {
			return new ModelAndView("tweets/tweetsPage", "Search", "not found");
		}
	}
	
	@RequestMapping(value="/users/follow/add", method = RequestMethod.POST)
	public @ResponseBody  User getUser(@RequestBody HttpServletRequest request, Long id) throws Exception{
		System.out.println("------------------------------------------");
		User loggedUser = userDAO.findByName(((User) request.getSession().getAttribute("LOGGEDIN_USER")).getUsername());
		User followedUser = userDAO.findById(id);
		List<User> friendsList = (List<User>) loggedUser.getFollows();
		friendsList.add(followedUser);
		loggedUser.setFollows(friendsList);
		userDAO.update(loggedUser);
		loggedUser.setStatus(true);
		return loggedUser;
	}

	@RequestMapping(value = "/users/follow/remove", method = RequestMethod.POST)
	public @ResponseBody  User removeUser(@RequestBody HttpServletRequest request, Long id) throws Exception{
		User loggedUser = userDAO.findByName(((User) request.getSession().getAttribute("LOGGEDIN_USER")).getUsername());
		User followedUser = userDAO.findById(id);
		List<User> friendsList = (List<User>) loggedUser.getFollows();
		friendsList.add(followedUser);
		loggedUser.setFollows(friendsList);
		userDAO.remove(loggedUser);
		loggedUser.setStatus(false);
		return loggedUser;
	}
}