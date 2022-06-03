package com.alexastudillo.erp.security.services;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.alexastudillo.erp.handlers.ResponseHandler;
import com.alexastudillo.erp.security.entities.Role;
import com.alexastudillo.erp.security.entities.User;
import com.alexastudillo.erp.security.repositories.RoleRepository;

import lombok.RequiredArgsConstructor;

@Service("roleService")
@RequiredArgsConstructor
public class RoleService {
	private final RoleRepository repository;
	private final ResponseHandler<Role> handler;

	public final ResponseEntity<Object> deleteById(final Short id) {
		repository.deleteById(id);
		return handler.generateResponse();
	}

	public final ResponseEntity<Object> findAllByPage(final Integer page, final Integer size) {
		return handler.generateResponse(repository.findAll(PageRequest.of(page - 1, size)));

	}

	public final ResponseEntity<Object> save(final Role role) {
		return handler.generateResponse(repository.save(role));
	}

	public final ResponseEntity<Object> update(final Role role, final Short id) {
		return handler.generateResponse(repository.save(role));
	}

	public final ResponseEntity<Object> myData() {
		final User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return handler.generateResponse(repository.findByUserId(user.getId()));
	}
}
