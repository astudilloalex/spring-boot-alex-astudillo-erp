package com.alexastudillo.erp.hr.entities;

import java.io.Serializable;

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

import com.alexastudillo.erp.company.entities.Address;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "departments")
@NoArgsConstructor
public class Department implements Serializable {
	private static final long serialVersionUID = 1946422839856637143L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	private Integer id;

	@Column(name = "name", nullable = false, unique = true)
	@Getter
	@Setter
	private String name;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "manager_id")
	@Getter
	@Setter
	private Employee manager;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id", referencedColumnName = "id", nullable = false)
	@Getter
	@Setter
	private Address address;

}
