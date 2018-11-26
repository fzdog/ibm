package com.ibm.test.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "client")
@NamedQueries({
	@NamedQuery(name="Client.findAll", query="SELECT c FROM Client c"),
	@NamedQuery(name="Client.findById", query="SELECT c FROM Client c where c.id = :id"),
})
public class Client {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer id;
	
	//@Size(max = 50)
	@Column(name = "name", length = 50)
	private String name;
	
//	@Size(max = 100)
	@Column(name = "address", length = 100)
	private String address;
	
	//@Size(max = 30)
	@Column(name = "city", length = 30)
	private String city;
	
	//@Size(max = 20)
	@Column(name = "phone", length = 20)
	private String phone;
		
	@OneToMany(mappedBy="client",orphanRemoval = true, cascade = CascadeType.PERSIST)
    private List<Card> cardList= new ArrayList<Card>();


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


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public List<Card> getCardList() {
		return cardList;
	}


	public void setCardList(List<Card> cardList) {
		this.cardList = cardList;
	}


}
