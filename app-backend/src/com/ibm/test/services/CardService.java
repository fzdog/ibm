package com.ibm.test.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.ibm.database.ConnectionDB;
import com.ibm.test.entities.Card;
import com.ibm.test.entities.Client;

public class CardService {

	EntityManager em = ConnectionDB.getEntityManager();

	public void insertCard(Card card) throws Exception {
		try {

			card.setClient(getClient(card.getClient_id()));
			em = ConnectionDB.getEntityManager();
			em.getTransaction().begin();
			em.persist(card);
			em.getTransaction().commit();
			System.out.println("Card created!");
			em.close();
		} catch (Exception e) {
			em.close();
			e.printStackTrace();
			throw new Exception("Error on saving card");

		}

	}

	public Client getClient(Integer clientId) {
		ClientService cs = new ClientService();
		Client client = cs.getClientById(clientId);
		return client;
	}

	public void updateCard(Card card) throws Exception {
		try {
			em = ConnectionDB.getEntityManager();
			em.getTransaction().begin();

			Card cardDB = em.find(Card.class, card.getId());
			if (cardDB != null && cardDB.getId() != null && cardDB.getId().equals(card.getId())) {

				cardDB.setCardNumber(card.getCardNumber());
				cardDB.setCcv(card.getCcv());
				cardDB.setType(card.getType());
				cardDB.getCardHistoryList().clear();
				cardDB.getCardHistoryList().addAll(card.getCardHistoryList());

				em.merge(cardDB);
				em.getTransaction().commit();
				em.close();

			}

		} catch (Exception e) {
			em.close();
			e.printStackTrace();
			throw new Exception("Error on updating card");

		}

	}

	public void deleteCard(Integer id) throws Exception {
		try {
			em = ConnectionDB.getEntityManager();
			em.getTransaction().begin();

			Card cardDB = em.find(Card.class, id);
			if (cardDB != null && cardDB.getId() != null) {
				em.remove(cardDB);
				em.getTransaction().commit();
				em.close();

			}

		} catch (Exception e) {
			em.close();
			e.printStackTrace();
			throw new Exception("Error on deleting card");

		}
	}

	public List<Card> getAllCards() {
		Query query = em.createNamedQuery("Card.findAll");
		List<Card> cards = query.getResultList();
		return cards;
	}

	public Card getCardById(int id) {
		Query query = em.createNamedQuery("Card.findById");
		Card card = (Card) query.setParameter("id", id).getSingleResult();
		return card;
	}

	public List<Card> getCardsByClientId(int clientId) {
		Query query = em.createNamedQuery("Card.findByClientId");
		List<Card> cardList = query.setParameter("id", clientId).getResultList();
		return cardList;
	}
}
