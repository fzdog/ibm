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
import com.ibm.test.entities.BankingAdvisor;
import com.ibm.test.services.BankingAdvisorService;

@Path("/bankingadvisorservice")
public class BankingAdvisorRestService {

	
	@GET
	@Path("/getAllBankingAdvisor")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllBankingAdvisor() throws Exception {
	

		BankingAdvisorService bankingAdvisorService = new BankingAdvisorService();
		List<BankingAdvisor> bankingAdvisorList = bankingAdvisorService.getAllBankingAdvisors();	

		return Response.status(200).entity(bankingAdvisorList).build();
	}

	@GET
	@Path("/getBankingAdvisorById/" + "{id}")
	@Produces("application/json")
	public Response getBankingAdvisorById(@PathParam("id") int id) throws Exception {

		String message = "";
		try {

			BankingAdvisorService bankingAdvisorService = new BankingAdvisorService();
			BankingAdvisor bankingAdvisor = bankingAdvisorService.getBankingAdvisorById(id);
			
			String json = new Gson().toJson(bankingAdvisor);

			return Response.status(200).entity(json).build();

		} catch (Exception e) {
			message = "Error on deleting";
			e.printStackTrace();
			return Response.status(500).entity(message).build();
		}
	}

	@POST
	@Path("/insertBankingAdvisor")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertBankingAdvisor(BankingAdvisor bankingAdvisor) throws Exception {
		String message = "";
		try {
			BankingAdvisorService bankingAdvisorService = new BankingAdvisorService();
			bankingAdvisorService.insertBankingAdvisor(bankingAdvisor);
			String json = new Gson().toJson("success");
			return Response.status(200).entity(json).build();

		} catch (Exception e) {
			message = "Error creating user";
			e.printStackTrace();
			return Response.status(500).entity(message).build();
		}
	}

	@PUT
	@Path("/updateBankingAdvisor")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateBankingAdvisor(BankingAdvisor bankingAdvisor) throws Exception {
		String message = "";
		try {
			BankingAdvisorService bankingAdvisorService = new BankingAdvisorService();
			bankingAdvisorService.updateBankingAdvisor(bankingAdvisor);
			String json = new Gson().toJson("success");
			return Response.status(200).entity(json).build();

		} catch (Exception e) {
			message = "Error creating user";
			e.printStackTrace();
			return Response.status(500).entity(message).build();
		}
	}

	@DELETE
	@Path("/deleteBankingAdvisor/" + "{id}")
	@Produces("application/json")
	public Response deleteBankingAdvisor(@PathParam("id") int id) throws Exception {

		String message = "";
		try {

			BankingAdvisorService bankingAdvisorService = new BankingAdvisorService();
			bankingAdvisorService.deleteBankingAdvisor(id);
			String json = new Gson().toJson("success");

			return Response.status(200).entity(json).build();

		} catch (Exception e) {
			message = "Error on deleting";
			e.printStackTrace();
			return Response.status(500).entity(message).build();
		}
	}
}