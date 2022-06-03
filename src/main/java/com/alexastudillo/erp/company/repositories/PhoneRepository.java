package com.alexastudillo.erp.company.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alexastudillo.erp.company.entities.Person;
import com.alexastudillo.erp.company.entities.Phone;

public interface PhoneRepository extends JpaRepository<Phone, Long> {
	public List<Phone> findByPerson(final Person person);

	@Query(value = "SELECT p FROM Phone p WHERE p.person.id = :personId")
	public List<Phone> findByPersonId(@Param("personId") final Long personId);
}
