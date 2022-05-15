package com.alexastudillo.erp.hr.entities;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.alexastudillo.erp.company.entities.Person;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "employees")
@NoArgsConstructor
public class Employee implements Serializable {
	private static final long serialVersionUID = -7617816370870842629L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	private Long id;

	@Column(name = "hire_date", nullable = false)
	@Getter
	@Setter
	private Date hireDate;

	@Column(name = "salary", columnDefinition = "NUMERIC(19,5) NOT NULL")
	@Getter
	@Setter
	private Double salary;

	@OneToOne
	@JoinColumn(name = "person_id", referencedColumnName = "id", nullable = false)
	@Getter
	@Setter
	private Person person;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "job_id", nullable = false)
	@Getter
	@Setter
	private Job job;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "department_id", nullable = false)
	@Getter
	@Setter
	private Department department;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "manager_id", referencedColumnName = "id", nullable = false)
	@Getter
	@Setter
	private Employee manager;

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
