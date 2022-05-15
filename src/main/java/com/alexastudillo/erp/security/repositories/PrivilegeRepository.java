package com.alexastudillo.erp.security.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alexastudillo.erp.security.entities.Privilege;

public interface PrivilegeRepository extends JpaRepository<Privilege, Short> {
	public Privilege findByName(String name);

	@Query(value = "SELECT p.* FROM privileges p JOIN role_privileges rp ON rp.privilege_id=p.id JOIN roles r ON r.id=rp.role_id WHERE r.id=:rid", nativeQuery = true)
	public List<Privilege> findByRoleId(@Param("rid") final Short roleId);
}
