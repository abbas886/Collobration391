package com.niit.colloboration.model;

import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Table
public class User {
	
	@Id
	private String emailID;
	
	private String name;
	
	private String password;
	
	private String details;
	
	private char role;
	
	private char status;
	
	private String reason;
	

}
