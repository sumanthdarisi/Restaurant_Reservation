package io.restaurant.controller;
import io.restaurant.dao.AdminReservationAccessDAO;
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
import javax.ws.rs.core.MediaType;

@Path("/adminReservationAccess")
public class AdminReservationAccessController {

	// 1. Create a new reservation using POST & Insert query
	// 2. Update existing reservation using POST (confirmation code) & GET values from database and POST again using update
	// 3. Delete existing reservation using DELETE query

	//GET all
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<guestModelData> getAllReservationDetails() throws AppException {
		AdminReservationAccessDAO dao = new AdminReservationAccessDAO();
		List<guestModelData> allReservationDetails;
		try {
			allReservationDetails = dao.getAllReservationDetails();
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage());
		}
		return allReservationDetails ;
	}

	//GET only one result
	@GET
	@Path("/{confirmationID}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<guestModelData> getReservationDetails(@PathParam ("confirmationID") int confirmationID) throws AppException {
		AdminReservationAccessDAO dao = new AdminReservationAccessDAO();
		List<guestModelData> reservatedGuestDetails;
		try {
			reservatedGuestDetails = dao.getReservationDetails(confirmationID);
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage());
		}
		return reservatedGuestDetails;
	}

	//POST
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public guestModelData createReservation(guestModelData formData) throws AppException {
		AdminReservationAccessDAO dao = new AdminReservationAccessDAO();
		try {
			formData = dao.adminCreateReservation(formData);
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage());
		}
		return formData;
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public guestModelData updateReservation(@PathParam ("confirmationID") int confirmationID,guestModelData formData) throws AppException {
			AdminReservationAccessDAO dao = new AdminReservationAccessDAO();
			formData = dao.adminUpdateReservation(confirmationID, formData);
			return formData;
	}

	//DELETE reservation
	@DELETE
	@Path("/{confirmationID}")
	public String cancelReservation(@PathParam ("confirmationID") int confirmationID) throws AppException {
			AdminReservationAccessDAO dao = new AdminReservationAccessDAO();
			String cancelThisReservation;
			try {
				cancelThisReservation = dao.adminDeleteThisReservationDetails(confirmationID);
			} catch (AppException e) {
				e.printStackTrace();
				throw new AppException(e.getMessage());
			}
			System.out.println("Final Success message");
			return cancelThisReservation;
		}

}
