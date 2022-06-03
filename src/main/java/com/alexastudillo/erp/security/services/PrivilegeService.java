package com.alexastudillo.erp.security.services;

import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alexastudillo.erp.handlers.ResponseHandler;
import com.alexastudillo.erp.security.entities.Privilege;
import com.alexastudillo.erp.security.repositories.PrivilegeRepository;
import lombok.RequiredArgsConstructor;

@Service("privilegeService")
@RequiredArgsConstructor
public class PrivilegeService {
	private final PrivilegeRepository repository;
	private final ResponseHandler<Privilege> handler;

	public final ResponseEntity<Object> delete(final Privilege entity) {
		repository.delete(entity);
		return handler.generateResponse();
	}

	public final ResponseEntity<Object> deleteById(final Short id) {
		repository.deleteById(id);
		return handler.generateResponse();
	}

	public final ResponseEntity<Object> findAllByPage(final Optional<Integer> page, final Optional<Integer> size) {
		if ((page.orElse(1) - 1) < 0 || size.orElse(10) < 1) {
			return handler.generateResponse(repository.findAll(PageRequest.of(page.orElse(1) - 1, size.orElse(10))));
		}
		return handler.generateResponse(repository.findAll(PageRequest.of(page.orElse(1) - 1, size.orElse(10))));
	}

	public final ResponseEntity<Object> save(final Privilege privilege) {
		return handler.generateResponse(repository.save(privilege));
	}

	public final ResponseEntity<Object> update(final Privilege privilege, final Short id) {
		return handler.generateResponse(repository.save(privilege));
	}
}
