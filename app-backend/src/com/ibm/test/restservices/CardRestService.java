package com.ibm.test.restservices;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;

/**
 * @author Ferneyder Cubides Suarez
 */

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.ibm.test.entities.Card;
import com.ibm.test.services.CardService;

@Path("/cardservice")
public class CardRestService {

	@GET
	@Path("/getAllCardsByClient/" + "{id}")
	@Produces("application/json")
	public Response getAllCardsByClient(@PathParam("id") int id) throws Exception {

		CardService cardService = new CardService();
		List<Card> cardsList = cardService.getCardsByClientId(id);

		for (Card card : cardsList) {
			card.setCardHistoryList(null);
			card.setClient(null);
		}

		return Response.status(200).entity(cardsList).build();
	}

	@GET
	@Path("/getCardById/" + "{id}")
	@Produces("application/json")
	public Response getCardById(@PathParam("id") int id) throws Exception {

		String message = "";
		try {

			CardService cardService = new CardService();
			Card card = cardService.getCardById(id);
			if (card != null) {
				card.setCardHistoryList(null);
				card.setClient(null);
			}
			String json = new Gson().toJson(card);

			return Response.status(200).entity(json).build();

		} catch (Exception e) {
			message = "Error on deleting";
			e.printStackTrace();
			return Response.status(500).entity(message).build();
		}
	}

	@POST
	@Path("/insertCard")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertCard(Card card) throws Exception {
		String message = "";
		try {
			CardService cardService = new CardService();
			cardService.insertCard(card);
			String json = new Gson().toJson("success");
			return Response.status(200).entity(json).build();

		} catch (Exception e) {
			message = "Error creating user";
			e.printStackTrace();
			return Response.status(500).entity(message).build();
		}
	}

	@PUT
	@Path("/updateCard")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateCard(Card card) throws Exception {
		String message = "";
		try {
			CardService cardService = new CardService();
			cardService.updateCard(card);
			String json = new Gson().toJson("success");
			return Response.status(200).entity(json).build();

		} catch (Exception e) {
			message = "Error creating user";
			e.printStackTrace();
			return Response.status(500).entity(message).build();
		}
	}

	@DELETE
	@Path("/deleteCard/" + "{id}")
	@Produces("application/json")
	public Response deleteClient(@PathParam("id") int id) throws Exception {

		String message = "";
		try {

			CardService cardService = new CardService();
			cardService.deleteCard(id);
			String json = new Gson().toJson("success");

			return Response.status(200).entity(json).build();

		} catch (Exception e) {
			message = "Error on deleting";
			e.printStackTrace();
			return Response.status(500).entity(message).build();
		}
	}
}