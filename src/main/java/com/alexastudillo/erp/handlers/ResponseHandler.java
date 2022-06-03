package com.alexastudillo.erp.handlers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseHandler<T> {

	public ResponseEntity<Object> generateResponse() {
		final HttpStatus status = HttpStatus.OK;
		final Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", "successful");
		map.put("statusCode", status.value());
		return new ResponseEntity<Object>(map, status);
	}

	public ResponseEntity<Object> generateResponse(final T entity) {
		final HttpStatus status = HttpStatus.OK;
		final Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", "successful");
		map.put("statusCode", status.value());
		map.put("data", entity);
		return new ResponseEntity<Object>(map, status);
	}

	public ResponseEntity<Object> generateResponse(final List<T> entities) {
		final HttpStatus status = HttpStatus.OK;
		final Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", "successful");
		map.put("statusCode", status.value());
		map.put("data", entities);
		return new ResponseEntity<Object>(map, status);
	}

	public ResponseEntity<Object> generateResponse(final Map<String, T> map, final HttpStatus status) {
		return new ResponseEntity<Object>(map, status);
	}

	public ResponseEntity<Object> generateResponse(final String message, final HttpStatus status) {
		final Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", message);
		map.put("statusCode", status.value());
		return new ResponseEntity<Object>(map, status);
	}

	public ResponseEntity<Object> generateResponse(final String message, final HttpStatus status, final Page<T> page) {
		final Map<String, Object> data = new HashMap<String, Object>();
		data.put("statusCode", status.value());
		data.put("message", message);
		data.put("totalPages", page.getTotalPages());
		data.put("numberOfElements", page.getNumberOfElements());
		data.put("pageNumber", page.getNumber() + 1);
		data.put("totalElements", page.getTotalElements());
		data.put("last", page.isLast());
		data.put("first", page.isFirst());
		data.put("offset", page.getPageable().getOffset() + 1);
		data.put("data", page.toList());
		return new ResponseEntity<Object>(data, status);
	}

	public ResponseEntity<Object> generateResponse(final HttpStatus status, final Page<T> page) {
		final Map<String, Object> data = new HashMap<String, Object>();
		data.put("statusCode", status.value());
		data.put("message", "successful");
		data.put("totalPages", page.getTotalPages());
		data.put("numberOfElements", page.getNumberOfElements());
		data.put("pageNumber", page.getNumber() + 1);
		data.put("totalElements", page.getTotalElements());
		data.put("last", page.isLast());
		data.put("first", page.isFirst());
		data.put("offset", page.getPageable().getOffset() + 1);
		data.put("data", page.toList());
		return new ResponseEntity<Object>(data, status);
	}

	public ResponseEntity<Object> generateResponse(final Page<T> page) {
		final HttpStatus status = HttpStatus.OK;
		final Map<String, Object> data = new HashMap<String, Object>();
		data.put("statusCode", status.value());
		data.put("message", "successful");
		data.put("totalPages", page.getTotalPages());
		data.put("numberOfElements", page.getNumberOfElements());
		data.put("pageNumber", page.getNumber() + 1);
		data.put("totalElements", page.getTotalElements());
		data.put("last", page.isLast());
		data.put("first", page.isFirst());
		data.put("offset", page.getPageable().getOffset() + 1);
		data.put("data", page.toList());

		return new ResponseEntity<Object>(data, status);
	}
}
