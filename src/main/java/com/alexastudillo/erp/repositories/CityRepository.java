package com.alexastudillo.erp.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alexastudillo.erp.entities.City;
import com.alexastudillo.erp.entities.Country;

public interface CityRepository extends JpaRepository<City, Integer> {
	public List<City> findByCountry(final Country country, final Pageable pageable);

	@Query(value = "SELECT * FROM cities WHERE country_id = :countryId ORDER BY id", countQuery = "SELECT COUNT(id) FROM cities", nativeQuery = true)
	public Page<City> findByCountryId(@Param("countryId") final Short countryId, final Pageable pageable);
}
