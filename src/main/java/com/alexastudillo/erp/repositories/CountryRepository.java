package com.alexastudillo.erp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alexastudillo.erp.entities.Country;

public interface CountryRepository extends JpaRepository<Country, Short> {
	public Country findByCode(final String code);

	public Country findByCodeOrName(final String code, final String name);
}
