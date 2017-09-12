/*package com.cgm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.cgm.db.repo.DBUserDAO;
import com.cgm.db.repository.contract.UserDataStore;
import com.cgm.domain.UserLogin;
import com.cgm.domain.UserStatus;

import java.util.List;

@Controller
public class FollowController {

	@Autowired
    private DBUserDAO userDAO;//JPA
	
	@SuppressWarnings("unused")
	@Autowired
	private UserDataStore userDataStore; // JDBC

    @SuppressWarnings("rawtypes")
	@RequestMapping(value = "/users/follow", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity followUser(@ModelAttribute("followForm") UserStatus user) {
        int result = userDAO.follow(user.getUsername());
        if (result == 1) {
            return ResponseEntity.ok("{\"message\": \"Success!\"}");
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"Error!\"}");
        }
    }

    @SuppressWarnings("rawtypes")
	@RequestMapping(value = "/users/unfollow", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity unfollowUser(@ModelAttribute("unfollowForm") UserStatus user) {
        int result = userDAO.unfollow(user.getUsername());
        if (result == 1) {
            return ResponseEntity.ok("{\"message\": \"Success!\"}");
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"Error!\"}");
        }
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView followPage(ModelAndView model) {
        UserStatus followForm = new UserStatus();
        UserStatus unfollowForm = new UserStatus();
        model.addObject("followForm", followForm);
        model.addObject("unfollowForm", unfollowForm);

        List<UserStatus> listUsers =userDAO.listUsers();
        model.addObject("listUsers", listUsers);

        model.setViewName("follows/followPage");
        return model;
    }

    @RequestMapping(value = "/following/formatted", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView following(ModelAndView model) {
        List<UserLogin> listFollowing = userDAO.listFollowing();
        model.addObject("listFollowing", listFollowing);
        model.setViewName("follows/followingPage");
        return model;
    }

    @RequestMapping(value = "/followers/formatted", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView followers(ModelAndView model) {
        List<UserLogin> listFollowers = userDAO.listFollowers();
        model.addObject("listFollowers", listFollowers);
        model.setViewName("follows/followersPage");
        return model;
    }

    @RequestMapping(value = "/following", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<UserLogin> following_JSON() {
        List<UserLogin> listFollowing = userDAO.listFollowing();
        return listFollowing;
    }

    @RequestMapping(value = "/followers", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<UserLogin> followers_JSON() {
        List<UserLogin> listFollowers = userDAO.listFollowers();
        return listFollowers;
    }
	
}

*/