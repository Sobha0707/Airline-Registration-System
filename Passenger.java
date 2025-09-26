package com.pace.airline;
import java.sql.Date;

public class Passenger {
	 private int id;
	    private String name;
	    private String email;
	    private String from;
	    private String to;
	    private Date departureDate;
	    private String departureTime;
	    private int age;
	    private Date returnDate;
	    private String message;
	    private String phoneNumber;
	    // getters and setters
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getFrom() {
			return from;
		}
		public void setFrom(String from) {
			this.from = from;
		}
		public String getTo() {
			return to;
		}
		public void setTo(String to) {
			this.to = to;
		}
		public Date getDepartureDate() {
			return departureDate;
		}
		public void setDepartureDate(Date departureDate) {
			this.departureDate = departureDate;
		}
		public String getDepartureTime() {
			return departureTime;
		}
		public void setDepartureTime(String departureTime) {
			this.departureTime = departureTime;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public Date getReturnDate() {
			return returnDate;
		}
		public void setReturnDate(Date returnDate) {
			this.returnDate = returnDate;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public String getPhoneNumber() {
			return phoneNumber;
		}
		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}
	    

}
