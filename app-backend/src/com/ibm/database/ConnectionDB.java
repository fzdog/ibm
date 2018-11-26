package com.ibm.database;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.ibm.test.entities.Client;
import com.ibm.test.services.ClientService;
import com.test.ibm.utils.FillDB;

public class ConnectionDB extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final EntityManagerFactory emFactoryObj;
	private static final String PERSISTENCE_UNIT_NAME = "ibmdb";

	static {
		emFactoryObj = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	}

	public static EntityManager getEntityManager() {
		return emFactoryObj.createEntityManager();
	}

	public void init() throws ServletException {
		getEntityManager();
		FillDB fillDB = new FillDB();
		fillDB.fillClients();
		ClientService cs = new ClientService();

		List<Client> res = cs.getAllClients();
		for (Client client : res) {
			System.out.println(client.getName());						
			System.out.println("---------------");
		}

	}

}
