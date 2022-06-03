package com.alexastudillo.erp.company.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alexastudillo.erp.company.entities.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
	public Person findByIdCard(final String idCard);

	@Query(value = "SELECT p FROM Person p JOIN User u ON u.person.id=p.id WHERE u.id=:userId")
	public Person findByUserId(@Param("userId") final Long userId);
}
