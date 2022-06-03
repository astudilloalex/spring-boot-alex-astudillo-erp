package com.alexastudillo.erp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.alexastudillo.erp.entities.Country;
import com.alexastudillo.erp.repositories.CountryRepository;

import lombok.RequiredArgsConstructor;

@Service("countryService")
@RequiredArgsConstructor
public class CountryService {
	private final CountryRepository repository;

	public final List<Country> findAll() {
		return repository.findAll();
	}

	public final Page<Country> findAll(final Optional<Integer> page, final Optional<Integer> size) {
		if ((page.orElse(1) - 1) < 0 || size.orElse(10) < 1) {
			return repository.findAll(PageRequest.of(page.orElse(1) - 1, size.orElse(10)));
		}
		return repository.findAll(PageRequest.of(page.orElse(1) - 1, size.orElse(10)));
	}

	public final Country findById(final Short id) {
		return repository.findById(id).orElse(null);
	}
}
