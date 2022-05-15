package com.alexastudillo.erp.company.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

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

import com.alexastudillo.erp.entities.Gender;
import com.alexastudillo.erp.entities.PersonDocumentType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "persons")
@NoArgsConstructor
public class Person implements Serializable {
	private static final long serialVersionUID = -5164233838150608535L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	private Long id;

	@Column(name = "id_card", unique = true, nullable = false, length = 20)
	@Getter
	@Setter
	private String idCard;

	@Column(name = "social_reason", unique = true)
	@Getter
	@Setter
	private String socialReason;

	@Column(name = "first_name", length = 50)
	@Getter
	@Setter
	private String firstName;

	@Column(name = "last_name", length = 50)
	@Getter
	@Setter
	private String lastName;

	@Column(name = "date_birth")
	@Getter
	@Setter
	private Date birthdate;

	@Column(name = "juridical_person", nullable = false)
	@Getter
	@Setter
	private boolean juridicalPerson;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gender_id")
	@Getter
	@Setter
	private Gender gender;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "document_type_id", nullable = false)
	@Getter
	@Setter
	private PersonDocumentType documentType;

	@Column(name = "email", unique = true, length = 60)
	private String email;

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
