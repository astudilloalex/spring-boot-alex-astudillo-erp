package com.alexastudillo.erp.company.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.alexastudillo.erp.entities.Gender;
import com.alexastudillo.erp.entities.PersonDocumentType;
import com.alexastudillo.erp.security.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "persons")
@Getter
@Setter
public class Person implements Serializable {
	private static final long serialVersionUID = -5164233838150608535L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "id_card", unique = true, nullable = false, length = 20)
	private String idCard;

	@Column(name = "social_reason", unique = true)
	private String socialReason;

	@Column(name = "first_name", length = 50)
	private String firstName;

	@Column(name = "last_name", length = 50)
	private String lastName;

	@Column(name = "birthdate")
	private Date birthdate;

	@Column(name = "juridical_person", nullable = false)
	private boolean juridicalPerson;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gender_id")
	private Gender gender;

	@ManyToOne
	@JoinColumn(name = "document_type_id", nullable = false)
	private PersonDocumentType documentType;

	@Column(name = "email", unique = true, length = 60)
	private String email;

	@OneToOne(mappedBy = "person", fetch = FetchType.LAZY)
	@JsonIgnoreProperties({ "person" })
	private User user;

	@OneToMany(mappedBy = "person")
	private List<Phone> phones = new ArrayList<Phone>();

	@Column(name = "active", nullable = false)
	private boolean active;

	@Column(name = "creation_date", updatable = false)
	@CreationTimestamp
	private Timestamp creationDate;

	@Column(name = "update_date")
	@UpdateTimestamp
	private Timestamp updateDate;

	public void addPhone(final Phone phone) {
		this.phones.add(phone);
		phone.setPerson(this);
	}

	public void removePhone(final Phone phone) {
		this.phones.remove(phone);
		phone.setPerson(null);
	}
}
