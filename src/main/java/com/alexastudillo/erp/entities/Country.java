package com.alexastudillo.erp.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "countries")
@NoArgsConstructor
public class Country implements Serializable {
	private static final long serialVersionUID = 5655902172750977837L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	private Short id;

	@Column(name = "code", columnDefinition = "CHAR(2) UNIQUE NOT NULL")
	@Getter
	@Setter
	private String code;

	@Column(name = "name", length = 150, unique = true, nullable = false)
	@Getter
	@Setter
	private String name;

	@Column(name = "active", nullable = false)
	@Getter
	@Setter
	private boolean active;

	@Column(name = "creation_date", updatable = false)
	@CreationTimestamp
	@Getter
	private Timestamp creationDate;

	@Column(name = "update_date")
	@UpdateTimestamp
	@Getter
	private Timestamp updateDate;

}
