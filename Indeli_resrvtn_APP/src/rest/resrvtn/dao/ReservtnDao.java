package rest.resrvtn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import rest.resrvtn.exception.AppException;
import rest.resrvtn.model.Reservation;
import rest.resrvtn.util.DBUtil;

public class ReservtnDao {
	
	public List<Reservation> getAllReservations () throws AppException {
		List<Reservation> reservationList = new ArrayList<Reservation>();
		
		Connection con = DBUtil.connectToDB();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("SELECT * FROM guest");
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Reservation reservation = new Reservation();
				
				reservation.setId(rs.getInt("ID"));
				reservation.setDate(rs.getString("DATE"));
				reservation.setTime(rs.getString("TIME"));
				reservation.setName(rs.getString("NAME"));
				reservation.setEmail(rs.getString("EMAIL"));
				reservation.setPhone(rs.getString("PHONE"));
				reservation.setPartySize(rs.getInt("PARTY_SIZE"));
				reservation.setPurpose(rs.getString("PURPOSE"));
				
				reservationList.add(reservation);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("Error in fetching records from database:  " +  e.getCause());
		}
		finally {
			DBUtil.closeResources(ps, rs, con);
		}
		
		return reservationList;
	}
	
	//Method to get a specific Reservation.
	public Reservation getReservation(int reservationId) throws AppException
	{
		Reservation reservation = new Reservation();
		
		Connection con = DBUtil.connectToDB();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("SELECT * FROM guest WHERE ID=?");
			ps.setInt(1, reservationId);
			
			rs = ps.executeQuery();
			
			if(rs.next())
			{
				reservation.setId(rs.getInt("ID"));
				reservation.setDate(rs.getString("DATE"));
				reservation.setTime(rs.getString("TIME"));
				reservation.setPhone(rs.getString("PHONE"));
				reservation.setName(rs.getString("NAME"));
				reservation.setEmail(rs.getString("EMAIL"));
				reservation.setPartySize(rs.getInt("PARTY_SIZE"));
				reservation.setPurpose(rs.getString("PURPOSE"));
			}
			else
			{
				throw new AppException("Error: ID " + reservationId + " is not available in the system.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			/*throw new AppException(e.getMessage(), e.getCause());*/
		}
		finally {
			DBUtil.closeResources(ps, rs, con);
		}
		
		return reservation;
	}
	
	//Method to insert a reservation
	/*public Reservation addReservation(Reservation reservation) throws AppException
	{
		Connection con = DBUtil.connectToDB();	
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = con.prepareStatement("INSERT INTO guest "
			+ "(Date, Time, Name, Phone, Email, "
			+ "PartySize, Purpose) VALUES (?, ?, ?, ?, ?, ?, ?)", 
			PreparedStatement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, reservation.getDate());
			ps.setString(2, reservation.getTime());
			ps.setString(3, reservation.getName());
			ps.setString(4, reservation.getPhone());
			ps.setString(5, reservation.getEmail());
			ps.setInt(6, reservation.getPartySize());
			ps.setString(7,reservation.getPurpose());			
			ps.executeUpdate();
			
			rs = ps.getGeneratedKeys();

			
			if(rs.next())
			{
				reservation.setId(rs.getInt(1));

			}
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
		
		return reservation;
		
	}*/
	
	
	public Reservation addReservation(Reservation reservation) throws AppException
	{
		Connection con = DBUtil.connectToDB();	
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = con.prepareStatement("INSERT INTO guest "
			+ "(Date, Time, Name, Phone, Email, "
			+ "Party_Size, Purpose) VALUES (?, ?, ?, ?, ?, ?, ?)", 
			PreparedStatement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, reservation.getDate());
			ps.setString(2, reservation.getTime());
			ps.setString(3, reservation.getName());
			ps.setString(4, reservation.getPhone());
			ps.setString(5, reservation.getEmail());
			ps.setInt(6, reservation.getPartySize());
			ps.setString(7,reservation.getPurpose());			
			ps.executeUpdate();
			
			rs = ps.getGeneratedKeys();

			
			if(rs.next())
			{
				reservation.setId(rs.getInt(1));

			}
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
		
		return reservation;
		
	}
	
	
	//Method to delete a specific reservation
	public void delReservation(int reservationId) throws AppException
	{	
		Connection con = DBUtil.connectToDB();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("DELETE from guest WHERE ID=?");
			ps.setInt(1, reservationId);
			ps.executeUpdate();
		} 
		catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage(), e.getCause());
		}
		finally {
			DBUtil.closeResources(ps, rs, con);
		}
	}
	
	//Method to edit a specific reservation
	public Reservation editReservation(Reservation reservation, 
			int reservationId) throws AppException
	{
		Connection con = DBUtil.connectToDB();	
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = con.prepareStatement("UPDATE guest SET "
					+ "Date=?, Time=?, Name=?, Phone=?, Email=?, "
			+ "PartySize=?, Purpose=? WHERE ID=?", 
					PreparedStatement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, reservation.getDate());
			ps.setString(2, reservation.getTime());
			ps.setString(3, reservation.getName());
			ps.setString(4, reservation.getPhone());
			ps.setString(5, reservation.getEmail());
			ps.setInt(6, reservation.getPartySize());
			ps.setString(7,reservation.getPurpose());
			ps.setInt(8, reservationId);
			ps.executeUpdate();
			
			rs = ps.getGeneratedKeys();
			
			reservation.setId(reservationId);
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
		
		return reservation;
		
	}
	
}
