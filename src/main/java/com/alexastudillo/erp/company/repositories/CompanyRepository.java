package com.alexastudillo.erp.company.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alexastudillo.erp.company.entities.Company;

public interface CompanyRepository extends JpaRepository<Company, Long>{

}
