package com.alexastudillo.erp.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "cities")
@NoArgsConstructor
public class City implements Serializable{
	private static final long serialVersionUID = 6905818544827164977L;

	@Id
	@Column(name = "id", columnDefinition = "SMALLINT")
	@Getter
	private Short id;
	
	@Column(name = "code")
	@Getter
	@Setter
	private String code;
	
	@Column(name = "name", nullable = false)
	@Getter
	@Setter
	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "country_id", referencedColumnName = "id", nullable = false)
	@Getter
	@Setter
	private Country country;
	
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
