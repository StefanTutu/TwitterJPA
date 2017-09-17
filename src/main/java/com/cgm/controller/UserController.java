/*package com.cgm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.intellitech.springlabs.model.User;
import com.intellitech.springlabs.service.UserService;
import com.intellitech.springlabs.util.Constants;
import com.intellitech.springlabs.util.CustomError;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@ApiOperation(value = "Get user by id")
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = Constants.SUCCESS, response = User.class),
			@ApiResponse(code = 403, message = Constants.FORBIDDEN),
			@ApiResponse(code = 422, message = Constants.USER_NOT_FOUND),
			@ApiResponse(code = 417, message = Constants.EXCEPTION_FAILED) })
	public ResponseEntity<?> getUserById(@PathVariable("userId") Long userId) {

		try {
			User user = userService.findById(userId);
			return new ResponseEntity<User>(user, HttpStatus.OK);

		} catch (ObjectNotFoundException onfe) {
			onfe.printStackTrace();
			CustomError error = new CustomError("User with id = " + userId + " is not found");
			return new ResponseEntity<CustomError>(error, HttpStatus.UNPROCESSABLE_ENTITY);

		} catch (Exception e) {
			e.printStackTrace();
			CustomError error = new CustomError("An error has occured");
			return new ResponseEntity<CustomError>(error, HttpStatus.EXPECTATION_FAILED);
		}

	}

}
*/