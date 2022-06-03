package com.alexastudillo.erp.security.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alexastudillo.erp.security.entities.Role;
import com.alexastudillo.erp.security.entities.User;

public interface RoleRepository extends JpaRepository<Role, Short> {
	public Role findByName(final String name);

	@Query(value = "SELECT r.* FROM roles r LEFT OUTER JOIN user_roles ur ON ur.role_id=r.id LEFT OUTER JOIN users u ON u.id=ur.user_id WHERE ur.user_id=:uid", nativeQuery = true)
	public List<Role> findByUserId(@Param("uid") final Long uid);

	List<Role> findByUsersIn(final Set<User> users);
}
