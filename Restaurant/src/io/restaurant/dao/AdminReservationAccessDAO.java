package io.restaurant.dao;

import io.restaurant.exception.AppException;
import io.restaurant.model.guestModelData;
import io.restaurant.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.NotFoundException;

public class AdminReservationAccessDAO {

	//GET all reservations from database 
	public List<guestModelData> getAllReservationDetails() throws AppException {
		List<guestModelData> guestReservationList = new ArrayList<guestModelData>();
		Connection con = DBUtil.Connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			System.out.println("Conncetion success from DAO");
			//Connection to Database
				ps= con.prepareStatement("SELECT *FROM sh1.guestreservationtable;");
				rs = ps.executeQuery();
				System.out.println("Query Passed");
			// Getting the data from database and assigning to model data
				while(rs.next()){
					guestModelData data = new guestModelData();
					data.setGuest_Confirmationcode(rs.getInt("Guest_ID"));
					data.setGuest_FirstName(rs.getString("Guest_FName"));
					data.setGuest_LastName(rs.getString("Guest_LName"));
					data.setGuest_Email(rs.getString("Guest_Email"));
					data.setGuest_Mobile(rs.getString("Guest_Mobile"));
					data.setGuest_PartySize(rs.getInt("Guest_PartySize"));
					//data.setGuest_PartyDate(rs.getDate("Guest_PartyDate"));
					data.setGuest_PartyTime(rs.getTime("Guest_PartyTime"));
				//added to list and returned
					guestReservationList.add(data);
				}
				
		} catch (SQLException e) {
			System.out.println("Conncetion FAILED from DAO");
			System.out.println(e);
			e.printStackTrace();
			throw new AppException(e.getMessage());
		}
		finally{
					if(ps != null){
						try {
							ps.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					if(rs != null){
						try {
							rs.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					if(con != null){
						try {
							con.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
		}
	return guestReservationList;
	}

	//GET only one reservation by passing Confirmation ID 
	public List<guestModelData> getReservationDetails(int confirmationID) throws AppException {
		
		List<guestModelData> guestReservationList = new ArrayList<guestModelData>();
		Connection con = DBUtil.Connect();
		guestModelData data = new guestModelData();;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			System.out.println("Conncetion success from DAO");
			//Connection to Database
				ps= con.prepareStatement("SELECT * FROM sh1.guestreservationtable WHERE sh1.guestreservationtable.Guest_ID=?;");
				ps.setInt(1, confirmationID);
				rs = ps.executeQuery();
				System.out.println("Query Passed");
			// Getting the data from database and assigning to model data
				while(rs.next()){
				data.setGuest_Confirmationcode(rs.getInt("Guest_ID"));
				data.setGuest_FirstName(rs.getString("Guest_FName"));
				data.setGuest_LastName(rs.getString("Guest_LName"));
				data.setGuest_Email(rs.getString("Guest_Email"));
				data.setGuest_Mobile(rs.getString("Guest_Mobile"));
				data.setGuest_PartySize(rs.getInt("Guest_PartySize"));
				//data.setGuest_PartyDate(rs.getDate("Guest_PartyDate"));
				data.setGuest_PartyTime(rs.getTime("Guest_PartyTime"));
			//added to list and returned
				guestReservationList.add(data);
				}
		} catch (SQLException e) {
			System.out.println("Conncetion FAILED from DAO");
			System.out.println(e);
			e.printStackTrace();
			throw new AppException(e.getMessage());
		}
		finally{
					if(ps != null){
						try {
							ps.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					if(rs != null){
						try {
							rs.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					if(con != null){
						try {
							con.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
		}
	return guestReservationList;
	}

	//DELETE existing reservation from database
	public String adminDeleteThisReservationDetails(int confirmationID) throws AppException {

		String Smessage = "Reservation Canceled";
//		String Fmessage = "Failed to cancel Reservation";
		
		Connection con = DBUtil.Connect();
		PreparedStatement ps = null;
		try {
			System.out.println("Conncetion success from Delete DAO");
			//Connection to Database
				ps= con.prepareStatement("DELETE FROM sh1.guestreservationtable WHERE sh1.guestreservationtable.Guest_ID=?;");
				ps.setInt(1, confirmationID);
				ps.executeUpdate();
				System.out.println("Query Passed");
			System.out.println(Smessage);
			return Smessage;
		}
		catch (SQLException e){
			e.printStackTrace();
			System.out.println(e);
			throw new AppException(e.getMessage());
		}
		finally{
			if(ps != null){
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
	}
	}

	//POST new reservation to database
	public guestModelData adminCreateReservation(guestModelData formData) throws AppException {
		ResultSet rs = null;
		Connection con = DBUtil.Connect();
		PreparedStatement ps = null;
		try {
			System.out.println("Conncetion success from Delete DAO");
			//Connection to Database
				ps= con.prepareStatement("INSERT INTO sh1.guestreservationtable(Guest_FName,Guest_LName,Guest_Email,Guest_Mobile,Guest_PartySize,Guest_PartyDate,Guest_PartyTime) VALUES(?,?,?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
				ps.setString(1, formData.getGuest_FirstName());
				ps.setString(2, formData.getGuest_LastName());
				ps.setString(3, formData.getGuest_Email());
				ps.setString(4, formData.getGuest_Mobile());
				ps.setInt(5, formData.getGuest_PartySize());
				ps.setDate(6, formData.getGuest_PartyDate());
				ps.setTime(7, formData.getGuest_PartyTime());
				ps.executeUpdate();
				System.out.println("Query Passed");
				rs= ps.getGeneratedKeys();
			    if(rs.next()){
					formData.setGuest_Confirmationcode(rs.getInt(1));
				}
		}
		catch (SQLException e){
			System.out.println("Conncetion FAILED from DAO");
			e.printStackTrace();
			throw new AppException(e.getMessage());
		}
		finally{
			if(ps != null){
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return formData;
	}

	public guestModelData adminUpdateReservation(int confirmationID,
			guestModelData formData) {
		ResultSet rs = null;
		Connection con = DBUtil.Connect();
		PreparedStatement ps = null;
		try {
			System.out.println("Conncetion success from UPDATE DAO");
			//Connection to Database
				ps= con.prepareStatement("UPDATE sh1.guestreservationtable "
						+ "SET Guest_FName=?, Guest_LName=?, Guest_Email=?, "
						+ "Guest_Mobile=?, Guest_PartySize=?,Guest_PartyDate=?, Guest_PartyTime=? "
						+ "WHERE Guest_ID=?",PreparedStatement.RETURN_GENERATED_KEYS);
				ps.setString(1, formData.getGuest_FirstName());
				ps.setString(2, formData.getGuest_LastName());
				ps.setString(3, formData.getGuest_Email());
				ps.setString(4, formData.getGuest_Mobile());
				ps.setInt(5, formData.getGuest_PartySize());
				ps.setDate(6, formData.getGuest_PartyDate());
				ps.setTime(7, formData.getGuest_PartyTime());
				ps.setInt(8, formData.getGuest_Confirmationcode());
				ps.executeUpdate();
				System.out.println("Query Passed");
				rs= ps.getGeneratedKeys();
			    if(rs.next()){
					formData.setGuest_Confirmationcode(rs.getInt(1));
				}
		}
		catch (SQLException e){
			System.out.println("Conncetion FAILED from DAO");
			e.printStackTrace();
			throw new NotFoundException("reservation with confirmation ID:"+confirmationID+" doesn't exist");
		}
		finally{
			if(ps != null){
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return formData;
	}
}