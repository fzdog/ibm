package com.ibm.test.restservices;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;

/**
 * @author Ferneyder Cubides Suarez
 * 
 * 
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
import com.ibm.test.entities.Client;
import com.ibm.test.services.ClientService;

@Path("/clientservice")
public class ClientRestService {

	@GET
	@Path("/getAllClients")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllClients() throws Exception {

		ClientService clientService = new ClientService();
		List<Client> clientsList = clientService.getAllClients();

		for (Client client : clientsList) {
			client.setCardList(null);
		}

		return Response.status(200).entity(clientsList).build();
	}

	@GET
	@Path("/getClient/" + "{id}")
	@Produces("application/json")
	public Response getClient(@PathParam("id") int id) throws Exception {

		String message = "";
		try {

			ClientService clientService = new ClientService();
			Client client = clientService.getClientById(id);
			if (client != null) {
				client.setCardList(null);
			}
			String json = new Gson().toJson(client);

			return Response.status(200).entity(json).build();

		} catch (Exception e) {
			message = "Error on deleting";
			e.printStackTrace();
			return Response.status(500).entity(message).build();
		}
	}

	@POST
	@Path("/insertClient")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertClient(Client client) throws Exception {
		String message = "";
		try {
			ClientService clientService = new ClientService();
			clientService.insertClient(client);
			String json = new Gson().toJson("success");
			return Response.status(200).entity(json).build();

		} catch (Exception e) {
			message = "Error creating user";
			e.printStackTrace();
			return Response.status(500).entity(message).build();
		}
	}

	@PUT
	@Path("/updateClient")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateClient(Client client) throws Exception {
		String message = "";
		try {
			ClientService clientService = new ClientService();
			clientService.updateClient(client);
			String json = new Gson().toJson("success");
			return Response.status(200).entity(json).build();

		} catch (Exception e) {
			message = "Error creating user";
			e.printStackTrace();
			return Response.status(500).entity(message).build();
		}
	}

	@DELETE
	@Path("/deleteClient/" + "{id}")
	@Produces("application/json")
	public Response deleteClient(@PathParam("id") int id) throws Exception {

		String message = "";
		try {
			System.out.println(id);

			ClientService clientService = new ClientService();
			clientService.deleteClient(id);
			String json = new Gson().toJson("success");

			return Response.status(200).entity(json).build();

		} catch (Exception e) {
			message = "Error on deleting";
			e.printStackTrace();
			return Response.status(500).entity(message).build();
		}
	}
}