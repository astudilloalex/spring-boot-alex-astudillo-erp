package com.alexastudillo.erp.company.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.alexastudillo.erp.company.entities.Company;
import com.alexastudillo.erp.company.entities.Person;

public interface CompanyRepository extends JpaRepository<Company, Long> {
	public List<Company> findByPerson(final Person person);

	public Page<Company> findByPerson(final Person person, final Pageable pageable);

	public Company findByPersonAndId(final Person person, final Long id);

	@Query(value = "SELECT * FROM companies WHERE person_id=?1 ORDER BY id LIMIT 1", nativeQuery = true)
	public Company findFirstByPerson(final Person person);
}
