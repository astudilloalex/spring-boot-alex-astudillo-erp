package com.alexastudillo.erp.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class EntityExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(DataIntegrityViolationException.class)
	public final ResponseEntity<Object> handleUniqueConstraint(final DataIntegrityViolationException ex) {
		final HttpStatus status = HttpStatus.CONFLICT;
		final Map<String, Object> data = new HashMap<String, Object>();
		data.put("message", "similar-record-already-exists");
		data.put("status", status.value());
		return new ResponseEntity<Object>(data, status);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public final ResponseEntity<Object> handleAccessDenied(final AccessDeniedException ex) {
		final HttpStatus status = HttpStatus.FORBIDDEN;
		final Map<String, Object> data = new HashMap<String, Object>();
		data.put("message", "forbidden");
		data.put("statusCode", status.value());
		return new ResponseEntity<Object>(data, status);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleInternalError(final Exception ex) {
		final HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		final Map<String, Object> data = new HashMap<String, Object>();
		data.put("message", "internal-server-error");
		data.put("statusCode", status.value());
		return new ResponseEntity<Object>(data, status);
	}
}
