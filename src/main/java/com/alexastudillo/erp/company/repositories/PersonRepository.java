package com.alexastudillo.erp.company.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alexastudillo.erp.company.entities.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
	public Person findByIdCard(final String idCard);
}
