package rest.resrvtn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import rest.resrvtn.exception.AppException;
import rest.resrvtn.model.Auth;
import rest.resrvtn.util.DBUtil;

public class StaffDao {

	public boolean getLogInfo(Auth staffInfo) throws AppException
	{
		boolean temp;
		Connection con = DBUtil.connectToDB();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("SELECT * FROM staff WHERE EMAIL=? AND PASSWORD=?");
			ps.setString(1, staffInfo.getEmail());
			ps.setString(2,staffInfo.getPassWord());
			
			rs = ps.executeQuery();
			
			if(rs.next())
			{
				
				 temp = true;
			}
			else
			{
				temp =  false;
				throw new AppException("Error: email or password is not valid.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage(), e.getCause());
		}
		finally {
			DBUtil.closeResources(ps, rs, con);
		}
		
		return temp;
	}
	
	
	public Auth editReservation(Auth staffInfo, String emailId, String passWord) throws AppException
	{
		Connection con = DBUtil.connectToDB();	
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = con.prepareStatement("UPDATE staffInfo SET "
					+ "PassWord=? WHERE EMAIL=?", 
					PreparedStatement.RETURN_GENERATED_KEYS);
			
			ps.setString(2, staffInfo.getPassWord());
			ps.executeUpdate();
			
			rs = ps.getGeneratedKeys();
			
			staffInfo.setEmail(emailId);
			staffInfo.setPassWord(passWord);
		}
		
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new AppException(e.getMessage(), e.getCause());
		}
		
		finally 
		{
			DBUtil.closeResources(ps, rs, con);
		}
		
		return staffInfo;
		
	}
	
}
