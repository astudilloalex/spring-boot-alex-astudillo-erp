package com.alexastudillo.erp.hr.entities;

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

import com.alexastudillo.erp.company.entities.Company;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "jobs")
@NoArgsConstructor
public class Job implements Serializable {
	private static final long serialVersionUID = 62494874518260543L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	private Long id;

	@Column(name = "code", unique = true, nullable = false, length = 10)
	@Getter
	@Setter
	private String code;

	@Column(name = "title", unique = true, nullable = false)
	@Getter
	@Setter
	private String title;

	@Column(name = "description")
	@Getter
	@Setter
	private String description;

	@Column(name = "min_salary", columnDefinition = "NUMERIC(19,5) NOT NULL")
	@Getter
	@Setter
	private Double minSalary;

	@Column(name = "max_salary", columnDefinition = "NUMERIC(19,5) NOT NULL")
	@Getter
	@Setter
	private Double maxSalary;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id", nullable = false)
	@Getter
	@Setter
	private Company company;

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
