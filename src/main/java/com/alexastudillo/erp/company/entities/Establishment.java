package com.alexastudillo.erp.company.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "establishments")
@NoArgsConstructor
public class Establishment implements Serializable {
	private static final long serialVersionUID = -978461967896538070L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	private Long id;

	@Column(name = "code", nullable = false)
	@Getter
	@Setter
	private String code;

	@Column(name = "name", nullable = false)
	@Getter
	@Setter
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id", nullable = false)
	@JsonIgnoreProperties({ "hibernateLazyInitializer" })
	@Getter
	@Setter
	private Company company;

	@OneToOne
	@JoinColumn(name = "address_id", nullable = false)
	@Getter
	@Setter
	private Address address;

	@OneToOne
	@JoinColumn(name = "phone_id")
	@Getter
	@Setter
	private Phone phone;

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
