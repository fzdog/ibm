package com.test.ibm.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ibm.test.entities.BankingAdvisor;
import com.ibm.test.entities.Card;
import com.ibm.test.entities.CardHistory;
import com.ibm.test.entities.Client;
import com.ibm.test.services.BankingAdvisorService;
import com.ibm.test.services.CardHistoryService;
import com.ibm.test.services.CardService;
import com.ibm.test.services.ClientService;

public class FillDB {

	ClientService clientService = new ClientService();
	CardService cardService = new CardService();
	CardHistoryService cardHistoryService = new CardHistoryService();
	BankingAdvisorService bankingAdvisorService = new BankingAdvisorService();

	Client client = new Client();
	Client client2 = new Client();
	Client client3 = new Client();

	Card card = new Card();
	Card card2 = new Card();
	Card card3 = new Card();

	CardHistory cardHistory = new CardHistory();
	CardHistory cardHistory2 = new CardHistory();
	CardHistory cardHistory3 = new CardHistory();
	CardHistory cardHistory4 = new CardHistory();

	BankingAdvisor bankingAdvisor = new BankingAdvisor();
	BankingAdvisor bankingAdvisor2 = new BankingAdvisor();

	List<Card> cardsList = new ArrayList<Card>();
	List<CardHistory> cardHistoryList = new ArrayList<CardHistory>();

	public void fillDB() {

	}

	public void fillClients() {

		try {
			// Fill Clients
			client.setName("John Smith");
			client.setAddress("KR 24 # 34-19");
			client.setCity("Armenia");
			client.setPhone("3127377909");

			client2.setName("Alex Band");
			client2.setAddress("Madison Av. 214");
			client2.setCity("New York");
			client2.setPhone("+1324546533");

			client3.setName("James Arau");
			client3.setAddress("CALLE 102#45-19");
			client3.setCity("Bogotá");
			client3.setPhone("3108403778");

			// Fill Cards
			card.setCcv("6543");
			card.setCardNumber("1234567890123456");
			card.setType("CREDITO");

			card2.setCcv("8474");
			card2.setCardNumber("7890123456789012");
			card2.setType("DEBITO");

			card3.setCcv("7364");
			card3.setCardNumber("3456789012345678");
			card3.setType("DEBITO");

			// Fill Card's History

			cardHistory.setDate(new Date());
			cardHistory.setDescription("TARJETA GOLD");
			cardHistory.setAmount(5422345d);

			cardHistory2.setDate(new Date());
			cardHistory2.setDescription("DEBITO PLUS");
			cardHistory2.setAmount(125000d);

			cardHistory3.setDate(new Date());
			cardHistory3.setDescription("DEBITO PLATINUM");
			cardHistory3.setAmount(400000d);

			cardHistory4.setDate(new Date());
			cardHistory4.setDescription("TARJETA GOLD");
			cardHistory4.setAmount(13500000d);

			// Fill Banking Advisors
			bankingAdvisor.setName("Camilo Cifuentes");
			bankingAdvisor.setSpecialty("Vehiculos");

			bankingAdvisor2.setName("Mauricio Restrepo");
			bankingAdvisor2.setSpecialty("Vivienda");

			bankingAdvisorService.insertBankingAdvisor(bankingAdvisor);
			bankingAdvisorService.insertBankingAdvisor(bankingAdvisor2);

			// Set the objects

			cardsList.add(card);
			cardHistoryList.add(cardHistory);
			client.setCardList(cardsList);

			card.setClient(client);
			card.setCardHistoryList(cardHistoryList);
			cardHistory.setCard(card);

			clientService.insertClient(client);

			cardsList.clear();
			cardHistoryList.clear();
			// -----------------

			cardsList.add(card2);
			cardHistoryList.add(cardHistory2);
			client2.setCardList(cardsList);

			card2.setClient(client2);
			card2.setCardHistoryList(cardHistoryList);
			cardHistory2.setCard(card2);

			clientService.insertClient(client2);

			cardsList.clear();
			cardHistoryList.clear();
			// ------------------

			cardsList.add(card3);
			cardHistoryList.add(cardHistory3);
			cardHistoryList.add(cardHistory4);
			client3.setCardList(cardsList);

			card3.setClient(client3);
			card3.setCardHistoryList(cardHistoryList);
			cardHistory3.setCard(card3);
			cardHistory4.setCard(card3);

			clientService.insertClient(client3);

			// Postule objects to garbage collector
			this.cleanObjects();

		} catch (Exception e) {
			System.out.println("Error filling clients");
			e.printStackTrace();
		}

	}

	public void cleanObjects() {
		clientService = null;
		cardService = null;
		cardHistoryService = null;
		bankingAdvisorService = null;

		client = null;
		client2 = null;
		client3 = null;

		card = null;
		card2 = null;
		card3 = null;

		cardHistoryList = null;
		cardsList = null;

		cardHistory = null;
		cardHistory2 = null;
		cardHistory3 = null;
		cardHistory4 = null;

		bankingAdvisor = null;
		bankingAdvisor2 = null;
	}

}
