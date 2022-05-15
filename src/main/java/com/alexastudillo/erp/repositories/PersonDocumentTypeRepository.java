package com.alexastudillo.erp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alexastudillo.erp.entities.PersonDocumentType;

public interface PersonDocumentTypeRepository extends JpaRepository<PersonDocumentType, Short> {
	public PersonDocumentType findByName(final String name);
} 
