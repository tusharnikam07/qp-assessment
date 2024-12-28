package com.questonpro.qp_assessment.exception;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<ErrorResponse> handleNoSuchElementException(NoSuchElementException ex, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex,
			WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<ErrorResponse> handleIllegalStateException(IllegalStateException ex, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ErrorResponse> handleMethodNotFound(HttpRequestMethodNotSupportedException ex,
			WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse("Requested Method Not Allowed : " + ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(errorResponse, HttpStatus.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse("An unexpected error occurred: " + ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
