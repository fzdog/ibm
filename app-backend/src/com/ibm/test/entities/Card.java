package com.ibm.test.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "card")
@NamedQueries({
	@NamedQuery(name="Card.findAll", query="SELECT c FROM Card c"),
	@NamedQuery(name="Card.findById", query="SELECT c FROM Card c where c.id = :id"),
	@NamedQuery(name="Card.findByClientId", query="SELECT c FROM Card c where c.client.id = :id"),
})
public class Card {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id	
	private Integer id;
	
	//@Size(max = 19)
	@Column(name = "cardNumber", length = 19)
	private String cardNumber;
	
	//@Size(max = 4)
	@Column(name = "ccv", length = 4)
	private String ccv;
	
	@Column(name = "type", length = 50)
	private String type;
	
	@OneToMany(mappedBy="card",cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
    private List<CardHistory> cardHistoryList= new ArrayList<CardHistory>();
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
	private Client client;
	
	@Transient
	private Integer client_id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCcv() {
		return ccv;
	}

	public void setCcv(String ccv) {
		this.ccv = ccv;
	}

	public List<CardHistory> getCardHistoryList() {
		return cardHistoryList;
	}

	public void setCardHistoryList(List<CardHistory> cardHistoryList) {
		this.cardHistoryList = cardHistoryList;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getClient_id() {
		return client_id;
	}

	public void setClient_id(Integer client_id) {
		this.client_id = client_id;
	}
		
	
}
