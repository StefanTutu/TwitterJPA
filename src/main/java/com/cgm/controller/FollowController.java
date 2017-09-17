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
import com.cgm.repository.UserDAO;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

@Controller
public class FollowController {

	@Autowired
	private UserDAO userDAO;

	@RequestMapping(value = "/users/follow", method = RequestMethod.GET)
	public @ResponseBody Collection<User> followUser(User user, HttpServletRequest request) {
		String username = request.getSession().getAttribute("username").toString();
		return userDAO.findByName(username).getFollows();
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public @ResponseBody User newUser(@PathVariable("id") Long id) {
		return userDAO.findById(id);
	}

	@RequestMapping(value = "/users/following", method = RequestMethod.GET)
	public @ResponseBody Collection<User> followingUser(User user, HttpServletRequest request) {
		String username = request.getSession().getAttribute("username").toString();
		return userDAO.findByName(username).getFollows();
	}

	@RequestMapping(value = "/users/all", method = RequestMethod.GET)
	public @ResponseBody Collection<User> allUser(User user, HttpServletRequest request) {
		return userDAO.findAll();
	}



}
