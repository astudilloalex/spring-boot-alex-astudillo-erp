package com.alexastudillo.erp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alexastudillo.erp.entities.Country;
import com.alexastudillo.erp.handlers.ResponseHandler;
import com.alexastudillo.erp.repositories.CountryRepository;
import com.alexastudillo.erp.utilities.APIConstants;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CountryController {
	private final CountryRepository repository;

	@PreAuthorize("hasRole('ROLE_SUPER')")
	@PostMapping(value = APIConstants.API_V1_URL + "/add-country")
	public ResponseEntity<Object> addCountry(@RequestBody final Country country) {
		final Country c = repository.findByCodeOrName(country.getCode().toUpperCase(), country.getName().toUpperCase());
		final ResponseHandler response = new ResponseHandler();
		if (c != null) {
			return response.generateResponseWithoutData("record-already-exists", HttpStatus.OK);
		}
		country.setCode(country.getCode().toUpperCase());
		country.setName(country.getName().toUpperCase());
		return response.generateResponse("successful", HttpStatus.OK, repository.save(country));
	}

	@PreAuthorize("hasRole('ROLE_SUPER')")
	@PostMapping(value = APIConstants.API_V1_URL + "/update-country")
	public ResponseEntity<Object> updateCountry(@RequestBody final Country country) {
		country.setCode(country.getCode().toUpperCase());
		country.setName(country.getName().toUpperCase());
		return new ResponseHandler().generateResponse("successful", HttpStatus.OK, repository.save(country));
	}

	@PreAuthorize("hasRole('ROLE_SUPER')")
	@GetMapping(value = APIConstants.API_V1_URL + "/countries")
	public ResponseEntity<Object> getAllCountries() {
		return new ResponseHandler().generateResponse("successful", HttpStatus.OK, repository.findAll());
	}

}
