package com.alexastudillo.erp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alexastudillo.erp.entities.City;
import com.alexastudillo.erp.handlers.ResponseHandler;
import com.alexastudillo.erp.repositories.CityRepository;
import com.alexastudillo.erp.utilities.APIConstants;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CityController {
	private final CityRepository repository;

	@PreAuthorize("hasRole('ROLE_SUPER')")
	@PostMapping(value = APIConstants.API_V1_URL + "/add-city")
	public ResponseEntity<Object> updateCountry(@RequestBody final City city) {
		if (city.getCode() != null) {
			city.setCode(city.getCode().toUpperCase());
		}
		city.setName(city.getName().toUpperCase());
		return new ResponseHandler().generateResponse("successful", HttpStatus.OK, repository.save(city));
	}
}
