package io.restaurant.model;
import java.sql.Time;
import java.sql.Date;

import com.fasterxml.jackson.annotation.*;


public class guestModelData {
	@JsonProperty("guest_FirstName")
	private String guest_FirstName;
	
	@JsonProperty("guest_LastName")
	private String guest_LastName;
	
	@JsonProperty("guest_Email")
	private String guest_Email;
	
	@JsonProperty("guest_Mobile")
	private String guest_Mobile;
	
	@JsonProperty("guest_PartySize")
	private int guest_PartySize;
	
	@JsonProperty("guest_PartyDate")
	private Date guest_PartyDate;
	
	@JsonProperty("guest_PartyTime")
	private Time guest_PartyTime;
	
	@JsonProperty("guest_Confirmationcode")
	private int guest_Confirmationcode;
	
	public int getGuest_Confirmationcode() {
		return guest_Confirmationcode;
	}
	public void setGuest_Confirmationcode(int guest_Confirmationcode) {
		this.guest_Confirmationcode = guest_Confirmationcode;
	}
	
	public String getGuest_FirstName() {
		return guest_FirstName;
	}
	public void setGuest_FirstName(String guest_FirstName) {
		this.guest_FirstName = guest_FirstName;
	}
	public String getGuest_LastName() {
		return guest_LastName;
	}
	public void setGuest_LastName(String guest_LastName) {
		this.guest_LastName = guest_LastName;
	}
	public String getGuest_Email() {
		return guest_Email;
	}
	public void setGuest_Email(String guest_Email) {
		this.guest_Email = guest_Email;
	}
	public String getGuest_Mobile() {
		return guest_Mobile;
	}
	public void setGuest_Mobile(String guest_Mobile) {
		this.guest_Mobile = guest_Mobile;
	}
	public int getGuest_PartySize() {
		return guest_PartySize;
	}
	public void setGuest_PartySize(int guest_PartySize) {
		this.guest_PartySize = guest_PartySize;
	}
	public Date getGuest_PartyDate() {
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		//guest_PartyDate = sdf.parse(guest_PartyDate);
		return guest_PartyDate;
	}
	public void setGuest_PartyDate(Date guest_PartyDate) {
		this.guest_PartyDate = guest_PartyDate;
	}
	public Time getGuest_PartyTime() {
		return guest_PartyTime;
	}
	public void setGuest_PartyTime(Time guest_PartyTime) {
		this.guest_PartyTime = guest_PartyTime;
	}
}