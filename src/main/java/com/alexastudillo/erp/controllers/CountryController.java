package com.alexastudillo.erp.controllers;

import java.util.Optional;

import org.springframework.data.domain.PageRequest;
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

import com.alexastudillo.erp.entities.Country;
import com.alexastudillo.erp.handlers.ResponseHandler;
import com.alexastudillo.erp.repositories.CountryRepository;
import com.alexastudillo.erp.utilities.APIPath;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = APIPath.API_V1_URL + APIPath.COUNTRIES)
@RequiredArgsConstructor
public class CountryController {
	private final CountryRepository repository;

	private final ResponseHandler<Country> handler = new ResponseHandler<Country>();

	@PreAuthorize("hasRole('ROLE_SUPER')")
	@PostMapping(value = APIPath.API_V1_URL + APIPath.COUNTRIES + APIPath.SAVE)
	public ResponseEntity<Object> create(@RequestBody final Country country) {
		return handler.generateResponse(repository.save(country));
	}

	@PreAuthorize("hasRole('ROLE_SUPER')")
	@GetMapping(value = APIPath.API_V1_URL + APIPath.COUNTRIES + APIPath.FIND_ALL_BY_PAGE)
	public ResponseEntity<Object> read(@RequestParam(value = "page") Optional<Integer> page,
			@RequestParam(value = "size") Optional<Integer> size) {
		return handler.generateResponse(repository.findAll(PageRequest.of(page.orElse(1) - 1, size.orElse(10))));
	}

	@PreAuthorize("hasRole('ROLE_SUPER')")
	@PutMapping(value = APIPath.API_V1_URL + APIPath.COUNTRIES + APIPath.UPDATE)
	public ResponseEntity<Object> update(@RequestBody final Country country) {
		return handler.generateResponse(repository.save(country));
	}

	@PreAuthorize("hasRole('ROLE_SUPER')")
	@DeleteMapping(value = APIPath.API_V1_URL + APIPath.COUNTRIES + APIPath.DELETE)
	public ResponseEntity<Object> delete(@RequestParam("id") final Short id) {
		repository.deleteById(id);
		return handler.generateResponse();
	}
}
