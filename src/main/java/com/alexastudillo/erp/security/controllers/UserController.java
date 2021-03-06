package com.alexastudillo.erp.security.controllers;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alexastudillo.erp.handlers.ResponseHandler;
import com.alexastudillo.erp.security.entities.User;
import com.alexastudillo.erp.security.repositories.UserRepository;
import com.alexastudillo.erp.security.services.UserService;
import com.alexastudillo.erp.utilities.APIPath;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(APIPath.API_V1_URL + APIPath.USERS)
@RequiredArgsConstructor
public class UserController {
	private final UserService service;

	private final UserRepository repository;

	private final ResponseHandler<User> handler = new ResponseHandler<User>();

	@PreAuthorize("hasRole('ROLE_SUPER')")
	@PostMapping(value = APIPath.SAVE)
	public ResponseEntity<Object> create(@RequestBody final User user) {
		return service.save(user);
	}

	@PreAuthorize("hasRole('ROLE_SUPER')")
	@GetMapping(value = APIPath.FIND_ALL_BY_PAGE)
	public ResponseEntity<Object> read(@RequestParam Optional<Integer> page, @RequestParam Optional<Integer> size) {
		return service.findAllByPage(page, size);
	}

	@PreAuthorize("hasRole('ROLE_SUPER')")
	@PutMapping(value = APIPath.UPDATE)
	public ResponseEntity<Object> update(@RequestBody final User user, final Long id) {
		return service.update(user, id);
	}

	@PreAuthorize("hasRole('ROLE_SUPER')")
	@DeleteMapping(value = APIPath.DELETE)
	public ResponseEntity<Object> delete(@RequestParam("id") final Long id) {
		repository.deleteById(id);
		return handler.generateResponse();
	}

	@GetMapping(value = APIPath.MY_DATA)
	public ResponseEntity<Object> getMyData() {
		return service.myData();
	}
}
