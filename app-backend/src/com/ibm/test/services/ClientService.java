package com.ibm.test.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.ibm.database.ConnectionDB;
import com.ibm.test.entities.Client;

public class ClientService {

	EntityManager em = ConnectionDB.getEntityManager();

	public void insertClient(Client client) throws Exception {
		try {
			em = ConnectionDB.getEntityManager();
			em.getTransaction().begin();
			em.persist(client);
			em.getTransaction().commit();
			System.out.println("Client created!");
			em.close();
		} catch (Exception e) {
			em.close();
			e.printStackTrace();
			throw new Exception("Error on saving");

		}

	}

	public void updateClient(Client client) throws Exception {
		try {
			em = ConnectionDB.getEntityManager();
			em.getTransaction().begin();
			Client clientDB = em.find(Client.class, client.getId());
			if (clientDB != null && clientDB.getId() != null && clientDB.getId().equals(client.getId())) {

				clientDB.setName(client.getName());
				clientDB.setAddress(client.getAddress());
				clientDB.setCity(client.getCity());
				clientDB.setPhone(client.getPhone());
				clientDB.getCardList().clear();
				clientDB.getCardList().addAll(client.getCardList());

				em.merge(clientDB);
				em.getTransaction().commit();
				em.close();				

			}

		} catch (Exception e) {
			em.close();
			e.printStackTrace();
			throw new Exception("Error on saving");

		}

	}

	public void deleteClient(Integer clientId) throws Exception {
		try {
			em = ConnectionDB.getEntityManager();
			em.getTransaction().begin();

			Client clientDB = em.find(Client.class, clientId);
			if (clientDB != null && clientDB.getId() != null) {
				em.remove(clientDB);
				em.getTransaction().commit();
				em.close();
			}

		} catch (Exception e) {
			em.close();
			e.printStackTrace();
			throw new Exception("Error on saving");

		}
	}

	public List<Client> getAllClients() {
		Query query = em.createNamedQuery("Client.findAll");
		List<Client> clients = query.getResultList();
		return clients;
	}

	public Client getClientById(Integer id) {
		Query query = em.createNamedQuery("Client.findById");
		Client client = (Client) query.setParameter("id", id).getSingleResult();
		
		return client;
	}
}
