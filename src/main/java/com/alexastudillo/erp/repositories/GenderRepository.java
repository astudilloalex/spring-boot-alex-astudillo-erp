package com.alexastudillo.erp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alexastudillo.erp.entities.Gender;

public interface GenderRepository extends JpaRepository<Gender, Short> {

}
