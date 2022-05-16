package com.alexastudillo.erp.init;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alexastudillo.erp.company.entities.Company;
import com.alexastudillo.erp.company.entities.Person;
import com.alexastudillo.erp.company.repositories.CompanyRepository;
import com.alexastudillo.erp.company.repositories.PersonRepository;
import com.alexastudillo.erp.entities.PersonDocumentType;
import com.alexastudillo.erp.repositories.PersonDocumentTypeRepository;
import com.alexastudillo.erp.security.entities.Privilege;
import com.alexastudillo.erp.security.entities.Role;
import com.alexastudillo.erp.security.entities.User;
import com.alexastudillo.erp.security.repositories.PrivilegeRepository;
import com.alexastudillo.erp.security.repositories.RoleRepository;
import com.alexastudillo.erp.security.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {
	private boolean alreadySetup = false;

	private final PrivilegeRepository privilegeRepository;
	private final RoleRepository roleRepository;
	private final UserRepository userRepository;
	private final PersonRepository personRepository;
	private final CompanyRepository companyRepository;
	private final PersonDocumentTypeRepository documentTypeRepository;
	private final PasswordEncoder passwordEncoder;

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
			user.setCompany(createCompany());
			user.setPassword(passwordEncoder.encode("admin"));
			user.setRoles(new HashSet<Role>(Arrays.asList(createRole("SUPER", privileges))));
			user.setPerson(createPerson());
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
	
	@Transactional
	private Person createPerson() {
		Person person=personRepository.findByIdCard("0123456789001");
		if(person==null) {
			person=new Person();
			person.setActive(true);
			person.setIdCard("0123456789001");
			person.setSocialReason("ALEX ASTUDILLO");
			person.setJuridicalPerson(true);
			person.setDocumentType(createDocumentType());
			personRepository.save(person);
		}
		return person;
	}
	
	@Transactional
	private PersonDocumentType createDocumentType() {
		PersonDocumentType type=documentTypeRepository.findByName("RUC");
		if(type==null) {
			type=new PersonDocumentType();
			type.setActive(true);
			type.setName("RUC");
			documentTypeRepository.save(type);
		}
		return type;
	}
	
	@Transactional
	private Company createCompany() {
		Company company=companyRepository.findById(1L).orElse(null);
		if(company==null) {
			company=new Company();
			company.setActive(true);
			company.setKeepAccounts(true);
			company.setPerson(createPerson());
			company.setSpecialTaxpayer(false);
			company.setTradename("ALEX ASTUDILLO");
			companyRepository.save(company);
		}
		return company;
	}
}
