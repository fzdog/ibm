package com.ibm.test.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "bankingadvisor")
@NamedQueries({
	@NamedQuery(name="BankingAdvisor.findAll", query="SELECT b FROM BankingAdvisor b"),
	@NamedQuery(name="BankingAdvisor.findById", query="SELECT b FROM BankingAdvisor b where b.id = :id"),	
})
public class BankingAdvisor {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer id;
	
	//@Size(max = 50)
	@Column(name = "name", length = 50)
	private String name;
	
	//@Size(max = 50)
	@Column(name = "specialty", length = 50)
	private String specialty;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
		
}
