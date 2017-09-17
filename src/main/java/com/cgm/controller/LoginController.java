package com.cgm.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cgm.domain.User;
import com.cgm.dto.ServiceResponse;
import com.cgm.repository.UserDAO;

@Controller
@RequestMapping(value = "account")
public class LoginController {

	@Autowired
	UserDAO userDao;

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public ModelAndView login() {
		return new ModelAndView("loginPage");
	}
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public @ResponseBody ServiceResponse login(@RequestBody User user, HttpServletRequest request) {
		User UserLogin = userDao.findByName(user.getUsername());
		ServiceResponse serviceResponse = new ServiceResponse();
		if (UserLogin != null) {
			if (UserLogin.getPassword().equals(user.getPassword())) {
				request.getSession().setAttribute("username", UserLogin.getUsername());
				
				return serviceResponse;
			}
		}
		serviceResponse.setCode(403);
		serviceResponse.setMessage("Wrong Username or password ! ");
		return serviceResponse;

	}
	
	@RequestMapping(value="register", method=RequestMethod.POST)
	public @ResponseBody ServiceResponse register(@RequestBody User user, HttpServletRequest request, String username, String password) {
		
		ServiceResponse serviceResponse = new ServiceResponse();
		user.setUsername(username);
		user.setStatus(true);
		user.setPassword(password);
		userDao.update(user);
		return serviceResponse;
	}

	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		session.removeAttribute("username");

		for (Cookie ck : request.getCookies()) {
			if (ck.getName().equalsIgnoreCase("username")) {
				ck.setMaxAge(0);
				response.addCookie(ck);
			}
			if (ck.getName().equalsIgnoreCase("password")) {
				ck.setMaxAge(0);
				response.addCookie(ck);
			}
		}
		return "redirect:login";
	}

}