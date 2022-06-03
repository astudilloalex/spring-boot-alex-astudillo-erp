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
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "companies")
@NoArgsConstructor
public class Company implements Serializable {
	private static final long serialVersionUID = 1986673262635332986L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	private Long id;

	@Column(name = "tradename", nullable = false, unique = true)
	@Getter
	@Setter
	private String tradename;

	@Column(name = "special_taxpayer_code", unique = true)
	@Getter
	@Setter
	private String specialTaxpayerCode;

	@Column(name = "active", nullable = false)
	@Getter
	@Setter
	private boolean active;

	@Column(name = "special_taxpayer", nullable = false)
	@Getter
	@Setter
	private boolean specialTaxpayer;

	@Column(name = "keep_accounts", nullable = false)
	@Getter
	@Setter
	private boolean keepAccounts;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "person_id", nullable = false)
	@JsonIgnoreProperties({ "hibernateLazyInitializer" })
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
}
