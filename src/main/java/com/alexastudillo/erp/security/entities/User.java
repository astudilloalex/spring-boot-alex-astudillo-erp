package com.alexastudillo.erp.security.entities;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.alexastudillo.erp.company.entities.Person;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
public class User implements UserDetails {
	private static final long serialVersionUID = 3247384593821070681L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter
	@Getter
	private Long id;

	@Column(name = "username", unique = true, length = 50, nullable = false)
	private String username;

	@Column(name = "password", nullable = false, length = 128)
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;

	@Column(name = "account_non_locked", nullable = false)
	private boolean accountNonLocked;

	@Column(name = "account_non_expired", nullable = false)
	private boolean accountNonExpired;

	@Column(name = "credentials_non_expired", nullable = false)
	private boolean credentialsNonExpired;

	@Column(name = "enabled", nullable = false)
	private boolean enabled;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "person_id", referencedColumnName = "id", nullable = false)
	@JsonIgnore
	@Getter
	@Setter
	private Person person;

	@Column(name = "creation_date", updatable = false)
	@CreationTimestamp
	@Getter
	private Timestamp creationDate;

	@Column(name = "update_date")
	@UpdateTimestamp
	@Getter
	private Timestamp updateDate;

	@ManyToMany
	@JoinTable(name = "user_roles", joinColumns = {
			@JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "role_id", referencedColumnName = "id") })
	@Getter
	@Setter
	private Set<Role> roles = new HashSet<Role>();

	public User() {
		// TODO Auto-generated constructor stub
	}

	public void addRole(final Role role) {
		this.roles.add(role);
		role.getUsers().add(this);
	}

	public void removeRole(final Role role) {
		this.roles.remove(role);
		role.getUsers().remove(this);
	}

	@Override
	@JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() {
		final List<GrantedAuthority> authorities = new ArrayList<>();
		for (final Role role : roles) {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
			role.getPrivileges().stream().map(privilege -> new SimpleGrantedAuthority(privilege.getName()))
					.forEach(authorities::add);
		}
		return authorities;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
