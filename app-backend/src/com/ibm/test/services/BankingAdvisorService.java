package com.ibm.test.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.ibm.database.ConnectionDB;
import com.ibm.test.entities.BankingAdvisor;

public class BankingAdvisorService {

	EntityManager em = ConnectionDB.getEntityManager();

	public void insertBankingAdvisor(BankingAdvisor bankingAdvisor) throws Exception {
		try {
			em = ConnectionDB.getEntityManager();
			em.getTransaction().begin();
			em.persist(bankingAdvisor);
			em.getTransaction().commit();
			System.out.println("BankingAdvisor created!");
			em.close();
		} catch (Exception e) {
			em.close();
			e.printStackTrace();
			throw new Exception("Error on saving bankingAdvisor");

		}

	}

	public void updateBankingAdvisor(BankingAdvisor bankingAdvisor) throws Exception {
		try {
			em = ConnectionDB.getEntityManager();
			em.getTransaction().begin();

			BankingAdvisor bankingAdvisorDB = em.find(BankingAdvisor.class, bankingAdvisor.getId());
			if (bankingAdvisorDB != null && bankingAdvisorDB.getId() != null && bankingAdvisorDB.getId().equals(bankingAdvisor.getId())) {

				bankingAdvisorDB.setName(bankingAdvisor.getName());
				bankingAdvisorDB.setSpecialty(bankingAdvisor.getSpecialty());				

				em.merge(bankingAdvisorDB);
				em.getTransaction().commit();
				em.close();

			}

		} catch (Exception e) {
			em.close();
			e.printStackTrace();
			throw new Exception("Error on saving");

		}

	}

	public void deleteBankingAdvisor(Integer id) throws Exception {
		try {
			em = ConnectionDB.getEntityManager();
			em.getTransaction().begin();

			BankingAdvisor bankingAdvisorDB = em.find(BankingAdvisor.class, id);
			if (bankingAdvisorDB != null && bankingAdvisorDB.getId() != null) {
				em.remove(bankingAdvisorDB);
				em.getTransaction().commit();
				em.close();
			}

		} catch (Exception e) {
			em.close();
			e.printStackTrace();
			throw new Exception("Error on saving");

		}
	}

	public List<BankingAdvisor> getAllBankingAdvisors() {
		Query query = em.createNamedQuery("BankingAdvisor.findAll");
		List<BankingAdvisor> bankingAdvisors = query.getResultList();
		return bankingAdvisors;
	}
	
	public BankingAdvisor getBankingAdvisorById(Integer id) {
		Query query = em.createNamedQuery("BankingAdvisor.findById");
		BankingAdvisor bankingAdvisor = (BankingAdvisor) query.setParameter("id", id).getSingleResult();
		
		return bankingAdvisor;
	}

	
}
