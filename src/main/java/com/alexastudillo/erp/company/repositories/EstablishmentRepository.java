package com.alexastudillo.erp.company.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alexastudillo.erp.company.entities.Company;
import com.alexastudillo.erp.company.entities.Establishment;

public interface EstablishmentRepository extends JpaRepository<Establishment, Long> {
	public List<Establishment> findByCompany(final Company company);

	public Establishment findByCompanyAndId(final Company company, final Long id);
}
