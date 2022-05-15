package com.alexastudillo.erp.init;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alexastudillo.erp.security.entities.Privilege;
import com.alexastudillo.erp.security.entities.Role;
import com.alexastudillo.erp.security.entities.User;
import com.alexastudillo.erp.security.repositories.PrivilegeRepository;
import com.alexastudillo.erp.security.repositories.RoleRepository;
import com.alexastudillo.erp.security.repositories.UserRepository;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {
	private boolean alreadySetup = false;

	private final PrivilegeRepository privilegeRepository;
	private final RoleRepository roleRepository;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public SetupDataLoader(final PrivilegeRepository privilegeRepository, final RoleRepository roleRepository,
			final UserRepository userRepository, final PasswordEncoder passwordEncoder) {
		super();
		this.privilegeRepository = privilegeRepository;
		this.roleRepository = roleRepository;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public void onApplicationEvent(final ContextRefreshedEvent event) {
		if (alreadySetup)
			return;
		final Set<Privilege> privileges = new HashSet<Privilege>();
		privileges.add(createPrivilege("CREATE"));
		privileges.add(createPrivilege("READ"));
		privileges.add(createPrivilege("UPDATE"));
		privileges.add(createPrivilege("DELETE"));
		User user = userRepository.findByUsername("superuser");
		if (user == null) {
			user = new User();
			user.setAccountNonExpired(true);
			user.setAccountNonLocked(true);
			user.setCredentialsNonExpired(true);
			user.setEnabled(true);
			user.setPassword(passwordEncoder.encode("admin"));
			user.setRoles(new HashSet<Role>(Arrays.asList(createRole("ADMIN", privileges))));
			user.setUsername("superuser");
			userRepository.save(user);
		}
		alreadySetup = true;
	}

	@Transactional
	private Privilege createPrivilege(final String name) {
		Privilege privilege = privilegeRepository.findByName(name);
		if (privilege == null) {
			privilege = new Privilege();
			privilege.setName(name);
			privilegeRepository.save(privilege);
		}
		return privilege;
	}

	@Transactional
	private Role createRole(final String name, final Set<Privilege> privileges) {
		Role role = roleRepository.findByName(name);
		if (role == null) {
			role = new Role();
			role.setName(name);
			role.setPrivileges(privileges);
			roleRepository.save(role);
		}
		return role;
	}
}
