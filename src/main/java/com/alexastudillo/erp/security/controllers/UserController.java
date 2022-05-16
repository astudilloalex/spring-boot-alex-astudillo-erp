package com.alexastudillo.erp.security.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alexastudillo.erp.handlers.ResponseHandler;
import com.alexastudillo.erp.security.entities.User;
import com.alexastudillo.erp.security.repositories.UserRepository;
import com.alexastudillo.erp.utilities.APIConstants;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {
	private final UserRepository userRepository;

	@PreAuthorize("hasRole('ROLE_SUPER')")
	@GetMapping(APIConstants.API_V1_URL + "/users/all")
	public ResponseEntity<Object> getAllUsers() {
		final List<User> data = userRepository.findAll();
		return new ResponseHandler().generateResponse("successful", HttpStatus.OK, data);
	}

}
