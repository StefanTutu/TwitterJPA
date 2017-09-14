package com.cgm.exception;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.metamodel.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cgm.exception.response.ExceptionResponse;
import com.cgm.exception.types.TechnicalException;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(GlobalControllerExceptionHandler.class);
	private static final String DATABASE_ERROR = "Database error";

	@ExceptionHandler({ SQLException.class, DataAccessException.class })
	@ResponseBody
	public ResponseEntity<ExceptionResponse> handleDataBaseExceptions(
			Exception e) {
		LOGGER.error(DATABASE_ERROR, e);
		return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(DATABASE_ERROR),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ValidationException.class)
	@ResponseBody
	public ResponseEntity<ExceptionResponse> handleValidationExceptions(
			ValidationException e, HttpServletRequest request) {
		LOGGER.error(e.getLocalizedMessage(), e);
		return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(
				e.getLocalizedMessage()), HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(TechnicalException.class)
	@ResponseBody
	public ResponseEntity<ExceptionResponse> handleTechnicalException(
			ValidationException e, HttpServletRequest request) {
		return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(
				e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
