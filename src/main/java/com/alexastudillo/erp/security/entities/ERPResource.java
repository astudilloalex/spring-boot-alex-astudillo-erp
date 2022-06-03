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

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "erp_resources")
@Getter
@Setter
public class ERPResource implements Serializable {
	private static final long serialVersionUID = -1758523994216688070L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Short id;

	@Column(name = "name", nullable = false, unique = true, length = 150)
	private String name;

	@Column(name = "description")
	private String description;

	@ManyToMany(mappedBy = "erpResources")
	private Set<Role> roles = new HashSet<Role>();

	@Column(name = "active", nullable = false)
	private boolean active;

	@Column(name = "creation_date", updatable = false)
	@CreationTimestamp
	private Timestamp creationDate;

	@Column(name = "update_date")
	@UpdateTimestamp
	private Timestamp updateDate;

	public void addRole(final Role role) {
		this.roles.add(role);
		role.addResource(this);
	}

	public void removeRole(final Role role) {
		this.roles.remove(role);
		role.removeResource(this);
	}
}
