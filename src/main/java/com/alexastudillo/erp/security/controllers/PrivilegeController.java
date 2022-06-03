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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alexastudillo.erp.security.entities.Privilege;
import com.alexastudillo.erp.security.services.PrivilegeService;
import com.alexastudillo.erp.utilities.APIPath;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = APIPath.API_V1_URL + APIPath.PRIVILEGES)
@RequiredArgsConstructor
public class PrivilegeController {
	private final PrivilegeService service;

	@PreAuthorize("hasRole('ROLE_SUPER')")
	@PostMapping(value = APIPath.SAVE)
	public ResponseEntity<Object> create(@RequestBody final Privilege privilege) {
		return service.save(privilege);
	}

	@PreAuthorize("hasRole('ROLE_SUPER')")
	@GetMapping(value = APIPath.FIND_ALL_BY_PAGE)
	public ResponseEntity<Object> read(@RequestParam final Optional<Integer> page,
			@RequestParam final Optional<Integer> size) {
		return service.findAllByPage(page, size);
	}

	@PreAuthorize("hasRole('ROLE_SUPER')")
	@PutMapping(value = APIPath.UPDATE + APIPath.ID)
	public ResponseEntity<Object> update(@RequestBody final Privilege privilege, @PathVariable final Short id) {
		return service.update(privilege, id);
	}

	@PreAuthorize("hasRole('ROLE_SUPER')")
	@DeleteMapping(value = APIPath.DELETE)
	public ResponseEntity<Object> delete(@RequestBody final Privilege entity) {
		return service.delete(entity);
	}
}
