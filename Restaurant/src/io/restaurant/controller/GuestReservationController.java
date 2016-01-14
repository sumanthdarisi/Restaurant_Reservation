package io.restaurant.controller;
import io.restaurant.dao.GuestReservationDAO;
import io.restaurant.exception.AppException;
import io.restaurant.model.guestModelData;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;


@Path("/guestReservation")
public class GuestReservationController{

	// 1. Create a new reservation using POST & Insert query
	// 2. Update existing reservation using POST (confirmation code) & GET values from database and POST again using update
	// 3. Delete existing reservation using DELETE query
		
	@GET
	@Path("/{confirmationID}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<guestModelData> getReservationDetails(@PathParam ("confirmationID") int confirmationID) throws AppException {
		List<guestModelData> reservatedGuestDetails;
		try {
			GuestReservationDAO dao = new GuestReservationDAO();
			reservatedGuestDetails = dao.getReservationDetails(confirmationID);
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		return reservatedGuestDetails;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public guestModelData createReservation(guestModelData formData) throws AppException {
		GuestReservationDAO dao = new GuestReservationDAO();
		try {
			formData = dao.createReservation(formData);
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		return formData;
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public guestModelData updateReservation(@PathParam ("confirmationID") int confirmationID,guestModelData formData) throws AppException {
		GuestReservationDAO dao = new GuestReservationDAO();
		try {
			formData = dao.updateReservation(confirmationID, formData);
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		return formData;

	}
	
	@DELETE
	@Path("/{confirmationID}")
	public String cancelReservation(@PathParam ("confirmationID") int confirmationID) throws AppException {
			GuestReservationDAO dao = new GuestReservationDAO();
			String cancelThisReservation;
			try {
				cancelThisReservation = dao.deleteThisReservationDetails(confirmationID);
			} catch (AppException e) {
				e.printStackTrace();
				throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
			}
			System.out.println("Final Success message");
			return cancelThisReservation;
		}

	}