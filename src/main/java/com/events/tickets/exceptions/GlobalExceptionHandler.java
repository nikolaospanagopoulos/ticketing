package com.events.tickets.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.events.tickets.payload.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ResourceNotFound.class)
	public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFound exception) {
		ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(), 404);
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(TicketLimitReached.class)
	public ResponseEntity<ErrorResponse> ticketCapacityException(TicketLimitReached exception) {
		ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(), 400);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ResourceAlreadyExists.class)
	public ResponseEntity<ErrorResponse> handleResourceAlreadyExists(ResourceAlreadyExists exception) {
		ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(), 400);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex) {
		ErrorResponse errorResponse = new ErrorResponse(
				ex.getMessage() != null ? ex.getMessage() : "An unexpected error occurred", 500);
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
