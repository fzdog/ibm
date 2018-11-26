package com.ibm.test.entities;

import java.util.Date;

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
import javax.persistence.Table;


@Entity
@Table(name = "cardhistory")
@NamedQueries({
	@NamedQuery(name="CardHistory.findAll", query="SELECT c FROM CardHistory c"),
	@NamedQuery(name="CardHistory.findById", query="SELECT c FROM CardHistory c where c.id = :id"),
	@NamedQuery(name="CardHistory.findByCardId", query="SELECT c FROM CardHistory c where c.card.id = :id"),
})
public class CardHistory {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer id;

	//@Size(max = 4)
	@Column(name = "date")
	private Date date;
	
	//@Size(max = 100)
	@Column(name = "description", length = 100)
	private String description;
	
	//@Size(max = 12)
	@Column(name = "amount")
	private Double amount;
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = { CascadeType.REMOVE })
	@JoinColumn(name = "card_id")
	private Card card;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}
	
}
