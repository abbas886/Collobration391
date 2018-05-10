package com.niit.collaboration.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niit.colloboration.dao.JobDAO;
import com.niit.colloboration.model.Job;



@RestController("/job/")
public class JobController {
	
	@Autowired
	private JobDAO jobDAO;
	
	
	@GetMapping("/list")
	public ResponseEntity<List<Job>>  getAlljobs()
	{
	 return	new ResponseEntity<List<Job>>(jobDAO.list(),HttpStatus.OK);
	}

}




