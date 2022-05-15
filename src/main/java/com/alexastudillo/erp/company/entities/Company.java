package com.alexastudillo.erp.company.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "companies")
public class Company implements Serializable {
	private static final long serialVersionUID = 1986673262635332986L;

}
