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
@Table(name = "person_document_types")
@NoArgsConstructor
public class PersonDocumentType implements Serializable{
	private static final long serialVersionUID = 992218705457176910L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	private Short id;
	
	@Column(name = "code", nullable = false, unique = true)
	@Getter
	@Setter
	private String code;
	
	@Column(name = "name", nullable = false, unique = true)
	@Getter
	@Setter
	private String name;
	
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
