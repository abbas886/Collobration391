package com.niit.collaboration.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.niit.colloboration.dao.JobDAO;
import com.niit.colloboration.dao.UserDAO;
import com.niit.colloboration.model.JobApplication;
import com.niit.colloboration.model.User;

@RestController
public class UserController {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private User user;
	
	@Autowired
	private JobDAO jobDAO;
	
	@Autowired
	private JobApplication jobApplication;

	// http://localhost:8081/CollaborationRestService/

	@GetMapping("/")
	public String serverTest() {
		return "This is my first webservice";
	}

	// http://localhost:8081/CollaborationRestService/user/list

	@GetMapping("user/list")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userDAO.list();
		if (users.size() == 0) {
			user.setMessage("No users existed in the app");
		}

		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	//// http://localhost:8081/CollaborationRestService/user/get/jaya@gmail.com
	/**
	 * This method will return user object based on emailID if emailID exist, will
	 * return user object else empty
	 * 
	 * @param emailID
	 * @return
	 */
	@GetMapping("user/get/{emailID}")
	public ResponseEntity<User> getUser(@PathVariable String emailID) {
		user = userDAO.get(emailID);
		if (user == null) {
			user = new User();
			user.setMessage("No user exist with this id");
			return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
	}

	@PostMapping("/user/validate")
	public ResponseEntity<User> validateCredentials(@RequestBody User user) {
		if (userDAO.validate(user.getEmailID(), user.getPassword()) == null) {
			user.setMessage("Invalid credentials.  please try again");
			return new ResponseEntity<User>(user, HttpStatus.UNAUTHORIZED);
		} else {
			user.setMessage("You successfully logged in");
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}

	}

	// create new record
	@PostMapping("/user/create")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		// need to write one more extra condition

		if (userDAO.get(user.getEmailID()) != null) {
			// if the user already exist with this email id
			user.setMessage("User already exist with this email id");
			return new ResponseEntity<User>(user, HttpStatus.CONFLICT);
		}

		if (userDAO.save(user)) {
			user.setMessage("User created Successfully");
			return new ResponseEntity<User>(user, HttpStatus.OK);

		} else {
			user.setMessage("Internal server problem.  pleae contact admin");
			return new ResponseEntity<User>(user, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("user/update") // to update the date
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		// check whether the user exist or not

		if (userDAO.get(user.getEmailID()) == null) {
			user.setMessage("No record exist with this emaild id " + user.getEmailID());
			
			return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
		}
		
		//if it existed, call update method
		
	  if(	userDAO.update(user) )
	  {
		user.setMessage("Successfully updated the details ");
			
			return new ResponseEntity<User>(user, HttpStatus.OK);
	
	  }
		user.setMessage("Could not update the details. please try after some time");
		
		return new ResponseEntity<User>(user, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	
	@DeleteMapping("user/delete/{emailID}")
	public ResponseEntity<User>  deleteUser(@PathVariable String emailID)
	{
		//check whether the record exist or not.
		 if( userDAO.get(emailID) ==null)
		 {
			 user.setMessage("User does not exist with this email id : " +emailID);;
			 return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
		 }
		 
		 //whether this user applied for any job or not
		 if(!jobDAO.list(emailID).isEmpty())
		 {
			 user.setMessage("We should not delete as " + emailID +
					 " applied few jobs");
			 return new ResponseEntity<User>(user, HttpStatus.NOT_ACCEPTABLE);
	
		 }
		 
		if( userDAO.delete(emailID))
		{
			 user.setMessage("The record successfully deleted" );
			 return new ResponseEntity<User>(user, HttpStatus.OK);
	
		}
		else
		{
			 user.setMessage("Could not delete the record.  please contact admin ");
			 return new ResponseEntity<User>(user, HttpStatus.INTERNAL_SERVER_ERROR);
	
		}
	}
	
	
	/**
	 * Fetch list of jobs applied by a user.
	 * if the user does not applied any job
	 * it will give message " You have not applied for any job"
	 * @param emailID
	 * @return
	 */
	@GetMapping("user/job/list/{emailID}")
	public ResponseEntity<List<JobApplication>> getJobApplicationsAppliedByMe(
			                 @PathVariable String emailID)
	{
		
		
		List<JobApplication> jobApplications = jobDAO.list(emailID);
		
		if(jobApplications.isEmpty())
		{
			//JobApplication jobapplication;  - Autowired
			 jobApplication.setMessage("You have not applied for any job ");
			 jobApplications.add(jobApplication);
			 return new ResponseEntity<List<JobApplication>>(jobApplications, HttpStatus.NOT_FOUND);
	
		}
		
		 return new ResponseEntity<List<JobApplication>>(jobApplications, HttpStatus.NOT_FOUND);
			
		
	}
	
	
	
	
	
	
	
	
	
	

}
