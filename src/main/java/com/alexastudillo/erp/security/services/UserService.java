package com.alexastudillo.erp.security.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alexastudillo.erp.handlers.ResponseHandler;
import com.alexastudillo.erp.security.entities.Privilege;
import com.alexastudillo.erp.security.entities.Role;
import com.alexastudillo.erp.security.entities.User;
import com.alexastudillo.erp.security.repositories.PrivilegeRepository;
import com.alexastudillo.erp.security.repositories.RoleRepository;
import com.alexastudillo.erp.security.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service("userService")
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
	private final PrivilegeRepository privilegeRepository;
	private final RoleRepository roleRepository;
	private final UserRepository repository;

	private final ResponseHandler<User> handler;

	@Transactional
	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		final User user = repository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("username-does-not-exist");
		}
		final List<Role> roles = roleRepository.findByUserId(user.getId());
		roles.forEach(role -> {
			final List<Privilege> privileges = privilegeRepository.findByRoleId(role.getId());
			privileges.forEach(privilege -> role.addPrivilege(privilege));
			user.addRole(role);
		});
		return user;
	}

	public final ResponseEntity<Object> deleteById(final Long id) {
		repository.deleteById(id);
		return handler.generateResponse();
	}

	public final ResponseEntity<Object> findAllByPage(final Optional<Integer> page, final Optional<Integer> size) {
		if ((page.orElse(1) - 1) < 0 || size.orElse(10) < 1) {
			return handler.generateResponse(repository.findAll(PageRequest.of(page.orElse(1) - 1, size.orElse(10))));
		}
		return handler.generateResponse(repository.findAll(PageRequest.of(page.orElse(1) - 1, size.orElse(10))));

	}

	public final ResponseEntity<Object> save(final User entity) {
		return handler.generateResponse(repository.save(entity));
	}

	public final ResponseEntity<Object> update(final User entity, final Long id) {
		return handler.generateResponse(repository.save(entity));
	}

	public ResponseEntity<Object> myData() {
		final User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return handler.generateResponse(repository.findById(user.getId()).get());
	}
}
