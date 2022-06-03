package com.alexastudillo.erp.company.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alexastudillo.erp.company.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
