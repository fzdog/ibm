package com.ibm.test.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.ibm.database.ConnectionDB;
import com.ibm.test.entities.Card;
import com.ibm.test.entities.CardHistory;

public class CardHistoryService {

	EntityManager em = ConnectionDB.getEntityManager();

	public void insertCardHistory(CardHistory cardHistory) throws Exception {
		try {
			em = ConnectionDB.getEntityManager();
			em.getTransaction().begin();
			em.persist(cardHistory);
			em.getTransaction().commit();
			System.out.println("Card History created!");
			em.close();
		} catch (Exception e) {
			em.close();
			e.printStackTrace();
			throw new Exception("Error on saving card history");

		}

	}

	public void updateCardHistory(CardHistory cardHistory) throws Exception {
		try {
			em = ConnectionDB.getEntityManager();
			em.getTransaction().begin();

			CardHistory cardHistoryDB = em.find(CardHistory.class, cardHistory.getId());
			if (cardHistoryDB != null && cardHistoryDB.getId() != null && cardHistoryDB.getId().equals(cardHistory.getId())) {

				cardHistoryDB.setDate(cardHistory.getDate());
				cardHistoryDB.setDescription(cardHistory.getDescription());
				cardHistoryDB.setAmount(cardHistoryDB.getAmount());
				
				em.merge(cardHistoryDB);
				em.getTransaction().commit();
				em.close();

			}

		} catch (Exception e) {
			em.close();
			e.printStackTrace();
			throw new Exception("Error on updating card");

		}

	}

	public void deleteCardHistory(CardHistory cardHistory) throws Exception {
		try {
			em = ConnectionDB.getEntityManager();
			em.getTransaction().begin();

			Card cardHistoryDB = em.find(Card.class, cardHistory.getId());
			if (cardHistoryDB != null && cardHistoryDB.getId() != null ) {
				em.remove(cardHistoryDB);
				em.getTransaction().commit();
				em.close();

			}

		} catch (Exception e) {
			em.close();
			e.printStackTrace();
			throw new Exception("Error on deleting card");

		}
	}

	public List<CardHistory> getAllHistory() {
				 
		Query query = em.createNamedQuery("CardHistory.findAll");
         List<CardHistory> cardHistories = query.getResultList();
         return cardHistories;
	}
	
	public List<CardHistory> getCardHistoryByCardId(int cardId) {
		Query query = em.createNamedQuery("CardHistory.findByCardId");
		 List<CardHistory>  cardHistories = query.setParameter("id", cardId).getResultList();
		return cardHistories;
	}
}

