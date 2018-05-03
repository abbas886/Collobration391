package com.niit.colloboration;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.colloboration.dao.JobDAO;
import com.niit.colloboration.dao.UserDAO;
import com.niit.colloboration.model.Job;
import com.niit.colloboration.model.JobApplication;
import com.niit.colloboration.model.User;

import junit.framework.Assert;

public class JobDAOTestCase {

private static AnnotationConfigApplicationContext context;
	
	@Autowired
	private static JobDAO jobDAO;
	
	@Autowired
	private static Job job;
	
	@Autowired
	private static JobApplication jobApplication;
	
	
	//we need create instance of AnnotationConfigApplicationContext
	//only once
	@BeforeClass
	public static void init()
	{
		context= new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		
		jobDAO = (JobDAO) context.getBean("jobDAO");
		
		job = (Job) context.getBean("job");
		jobApplication = (JobApplication) context.getBean("jobApplication");
		
	}
	
	
	
	@Test
	public void saveJobTestCase()
	{
		
		//job.setDescription("");
		job.setNo_of_openings(10);
		job.setQualification("B.E");
		job.setSalary(20-000);
		job.setTitle("Programmer");
		
		Assert.assertEquals(true,    jobDAO.save(job));
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
