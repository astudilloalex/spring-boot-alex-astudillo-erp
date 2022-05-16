package com.alexastudillo.erp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alexastudillo.erp.entities.City;

public interface CityRepository extends JpaRepository<City, Integer> {

}
