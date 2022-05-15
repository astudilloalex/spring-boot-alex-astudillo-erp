package com.alexastudillo.erp.security.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alexastudillo.erp.security.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Short> {
	public Role findByName(final String name);

	@Query(value = "SELECT r.* FROM roles r JOIN user_roles ur ON ur.role_id=r.id JOIN users u ON u.id=ur.user_id WHERE ur.user_id=:uid", nativeQuery = true)
	public List<Role> findByUserId(@Param("uid") final Long uid);
}
