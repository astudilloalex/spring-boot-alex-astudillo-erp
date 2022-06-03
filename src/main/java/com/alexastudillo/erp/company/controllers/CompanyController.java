package com.alexastudillo.erp.company.controllers;

import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alexastudillo.erp.company.entities.Company;
import com.alexastudillo.erp.company.repositories.CompanyRepository;
import com.alexastudillo.erp.company.repositories.PersonRepository;
import com.alexastudillo.erp.handlers.ResponseHandler;
import com.alexastudillo.erp.security.entities.User;
import com.alexastudillo.erp.utilities.APIPath;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(APIPath.API_V1_URL + APIPath.COMPANIES)
public class CompanyController {
	private final CompanyRepository repository;
	private final PersonRepository personRepository;

	private final ResponseHandler<Company> handler = new ResponseHandler<Company>();

	@PreAuthorize("hasRole('ROLE_SUPER')")
	@PostMapping(value = APIPath.SAVE)
	public ResponseEntity<Object> create(@RequestBody final Company company) {
		return handler.generateResponse(repository.save(company));
	}

	@PreAuthorize("hasRole('ROLE_SUPER')")
	@GetMapping(value = APIPath.FIND_ALL_BY_PAGE)
	public ResponseEntity<Object> read(@RequestParam(value = "page") Optional<Integer> page,
			@RequestParam(value = "size") Optional<Integer> size) {
		return handler.generateResponse(repository.findAll(PageRequest.of(page.orElse(1) - 1, size.orElse(10))));
	}

	@PreAuthorize("hasRole('ROLE_SUPER')")
	@PutMapping(value = APIPath.UPDATE)
	public ResponseEntity<Object> update(@RequestBody final Company company) {
		return handler.generateResponse(repository.save(company));
	}

	@PreAuthorize("hasRole('ROLE_SUPER')")
	@DeleteMapping(value = APIPath.DELETE)
	public ResponseEntity<Object> delete(@RequestParam("id") final Long id) {
		repository.deleteById(id);
		return handler.generateResponse();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> read(@PathVariable("id") final Optional<Long> id) {
		final User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (!id.isPresent() || id.get() == 0) {
			return handler.generateResponse(repository.findFirstByPerson(personRepository.findByUserId(user.getId())));
		}
		return handler
				.generateResponse(repository.findByPersonAndId(personRepository.findByUserId(user.getId()), id.get()));
	}

	@GetMapping(value = APIPath.MY_DATA)
	public ResponseEntity<Object> getMyCompanies(@RequestParam(value = "page") Optional<Integer> page,
			@RequestParam(value = "size") Optional<Integer> size) {
		final User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return handler.generateResponse(repository.findByPerson(personRepository.findByUserId(user.getId()),
				PageRequest.of(page.orElse(1) - 1, size.orElse(10))));
	}
}
