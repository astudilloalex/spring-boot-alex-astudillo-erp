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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "addresses")
@NoArgsConstructor
public class Address implements Serializable {
	private static final long serialVersionUID = 1820960484299690983L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	private Long id;

	@Column(name = "postal_code", length = 7)
	@Getter
	@Setter
	private String postalCode;

	@Column(name = "street_number", length = 10)
	@Getter
	@Setter
	private String streetNumber;

	@Column(name = "main_street", nullable = false, length = 45)
	@Getter
	@Setter
	private String mainStreet;

	@Column(name = "secondary_street", length = 45)
	@Getter
	@Setter
	private String secondaryStreet;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "person_id")
	@Getter
	@Setter
	private Person person;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "city_id", referencedColumnName = "id", nullable = false)
	@Getter
	@Setter
	private City city;

	@Column(name = "active", nullable = false)
	@Getter
	@Setter
	private boolean active;

	@Column(name = "creation_date", columnDefinition = "TIMESTAMP WITH TIME ZONE NOT NULL", updatable = false)
	@CreationTimestamp
	@Getter
	private Timestamp creationDate;

	@Column(name = "update_date", columnDefinition = "TIMESTAMP WITH TIME ZONE NOT NULL")
	@UpdateTimestamp
	@Getter
	private Timestamp updateDate;

}
