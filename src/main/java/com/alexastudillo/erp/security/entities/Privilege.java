package com.alexastudillo.erp.security.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "privileges")
public class Privilege implements Serializable {
	private static final long serialVersionUID = -4432961731038818370L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	private Short id;

	@Column(name = "name", nullable = false, unique = true, length = 10)
	@Getter
	@Setter
	private String name;

	@Column(name = "creation_date", updatable = false)
	@Getter
	@CreationTimestamp
	private Timestamp creationDate;

	@Column(name = "update_date")
	@Getter
	@UpdateTimestamp
	private Timestamp updateDate;

	@ManyToMany(mappedBy = "privileges")
	@JsonIgnore
	@Getter
	@Setter
	private Set<Role> roles = new HashSet<Role>();

	public void addRole(final Role role) {
		this.roles.add(role);
		role.getPrivileges().add(this);
	}

	public void removeRole(final Role role) {
		this.roles.remove(role);
		role.getPrivileges().remove(this);
	}
}
