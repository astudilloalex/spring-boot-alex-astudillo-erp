package com.alexastudillo.erp.security.controllers;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alexastudillo.erp.security.entities.Role;
import com.alexastudillo.erp.security.services.RoleService;
import com.alexastudillo.erp.utilities.APIPath;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RoleController {
	private final RoleService service;

	@PreAuthorize("hasRole('ROLE_SUPER')")
	@DeleteMapping(value = APIPath.API_V1_URL + APIPath.ROLES + APIPath.DELETE + APIPath.ID)
	public ResponseEntity<Object> deleteById(@PathVariable final Short id) {
		return service.deleteById(id);
	}

	@GetMapping(value = APIPath.API_V1_URL + APIPath.ROLES + APIPath.FIND_ALL_BY_PAGE)
	public ResponseEntity<Object> findAllByPage(@RequestParam Optional<Integer> page,
			@RequestParam Optional<Integer> size) {
		return service.findAllByPage(page.orElse(1), size.orElse(10));
	}

	@PostMapping(value = APIPath.API_V1_URL + APIPath.ROLES + APIPath.SAVE)
	public ResponseEntity<Object> save(@RequestBody final Role role) {
		return service.save(role);
	}

	@PutMapping(value = APIPath.API_V1_URL + APIPath.ROLES + APIPath.UPDATE + APIPath.ID)
	public ResponseEntity<Object> update(@RequestBody final Role role, @PathVariable final Short id) {
		return service.update(role, id);
	}

	@GetMapping(value = APIPath.API_V1_URL + APIPath.ROLES + APIPath.MY_DATA)
	public ResponseEntity<Object> myData() {
		return service.myData();
	}
}
