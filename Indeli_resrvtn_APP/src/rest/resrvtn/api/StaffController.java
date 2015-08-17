package rest.resrvtn.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import rest.resrvtn.dao.StaffDao;
import rest.resrvtn.exception.AppException;
import rest.resrvtn.model.Auth;


@Path("/staff")
public class StaffController {
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public  AppResponse getAccount(Auth auth ){
		AppResponse resp = new AppResponse();
		
		try{
			StaffDao dao = new StaffDao();
			boolean isAuth = dao.getLogInfo(auth);
			if(isAuth){
			resp.setMessage("Login Successful");
			}
			else{
			resp.setMessage("Login Invalid");
			}
		}catch (AppException e){
			e.printStackTrace();
			resp.setStatus(AppResponse.ERROR);
			resp.setMessage(e.getMessage());
		}
		return resp;
		
	}
	
	
}
