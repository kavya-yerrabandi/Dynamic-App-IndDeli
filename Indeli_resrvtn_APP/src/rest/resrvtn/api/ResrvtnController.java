package rest.resrvtn.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import rest.resrvtn.dao.ReservtnDao;
import rest.resrvtn.exception.AppException;
import rest.resrvtn.model.Reservation;

@Path("/view-reservations")
public class ResrvtnController {
	
	//to retrieve the entire reservation info
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	
	public AppResponse getAll(){
		AppResponse resp = new AppResponse();
		
		try {
			ReservtnDao dao = new ReservtnDao();
			List<Reservation> reservtnList = dao.getAllReservations();
			resp.setPayload(reservtnList);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resp.setStatus(AppResponse.ERROR);
			resp.setMessage(e.getMessage());
		}
		return  resp;
	}
	
	@GET
	@Path("/num/{id}")
	public AppResponse viewReservation(@PathParam("id") int reservationId){
			
		AppResponse resp = new AppResponse();

		try {
			ReservtnDao dao = new ReservtnDao();
			Reservation reservation= dao.getReservation(reservationId);
			resp.setPayload(reservation);
			} 
		catch (AppException e) {
			resp.setStatus(AppResponse.ERROR);
			resp.setMessage(e.getMessage());
			}
			return resp;
		
	}
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse addReservation(Reservation reservation){
		AppResponse resp = new AppResponse();
		try {
			ReservtnDao dao = new ReservtnDao();
			reservation = dao.addReservation(reservation);
			resp.setPayload(reservation);
		} catch (AppException e) {
			resp.setStatus(AppResponse.ERROR);
			resp.setMessage(e.getMessage());
		}
		return resp;
	}
	

	@POST
	@Path("/delete/{id}")
	public String delReservation(){
		return "removed the res";
	}
	public AppResponse delReservation(@PathParam("id") int reservationId)
	{
		AppResponse resp = new AppResponse();

		try {
			ReservtnDao dao = new ReservtnDao();
			dao.delReservation(reservationId);
			resp.setPayload("Deleted succefully");
		} catch (AppException e) {
			resp.setStatus(AppResponse.ERROR);
			resp.setMessage(e.getMessage());
		}
		return resp;
	}
	
	@POST
	@Path("/edit/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse editReservation(@PathParam("id") int reservationId, Reservation reservation){
		AppResponse resp = new AppResponse();
		try {
			ReservtnDao dao = new ReservtnDao();
			reservation = dao.editReservation(reservation, reservationId);
			resp.setPayload(reservation);
		} catch (AppException e) {
			resp.setStatus(AppResponse.ERROR);
			resp.setMessage(e.getMessage());
		}
		return resp;
	}

}
