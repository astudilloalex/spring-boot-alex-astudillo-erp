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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class Role implements Serializable {
	private static final long serialVersionUID = 1817959705417509031L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Short id;

	@Column(name = "name", nullable = false, unique = true, length = 15)
	private String name;

	@ManyToMany
	@JoinTable(name = "role_resources", joinColumns = {
			@JoinColumn(name = "role_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "resource_id", referencedColumnName = "id") })
	private Set<ERPResource> erpResources = new HashSet<ERPResource>();

	@Column(name = "active", nullable = false)
	private boolean active;

	@Column(name = "creation_date", updatable = false)
	@CreationTimestamp
	private Timestamp creationDate;

	@Column(name = "update_date")
	@UpdateTimestamp
	private Timestamp updateDate;

	@ManyToMany(mappedBy = "roles")
	@JsonIgnore
	private Set<User> users;

	@ManyToMany
	@JoinTable(name = "role_privileges", joinColumns = {
			@JoinColumn(name = "role_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "privilege_id", referencedColumnName = "id") })
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
	@JsonIdentityReference(alwaysAsId = true)
	private Set<Privilege> privileges = new HashSet<Privilege>();

	public void addPrivilege(final Privilege privilege) {
		this.privileges.add(privilege);
		privilege.getRoles().add(this);
	}

	public void removePrivilege(final Privilege privilege) {
		this.privileges.remove(privilege);
		privilege.getRoles().remove(this);
	}

	public void addResource(final ERPResource resource) {
		this.erpResources.add(resource);
		resource.getRoles().add(this);
	}

	public void removeResource(final ERPResource resource) {
		this.erpResources.remove(resource);
		resource.getRoles().remove(this);
	}

	public void addUser(final User user) {
		this.users.add(user);
		user.getRoles().add(this);
	}

	public void removeUser(final User user) {
		this.users.remove(user);
		user.getRoles().remove(this);
	}
}
