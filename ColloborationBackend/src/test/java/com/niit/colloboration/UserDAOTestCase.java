package com.niit.colloboration;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.niit.colloboration.dao.UserDAO;
import com.niit.colloboration.model.User;





//@ComponentScan(basePackageClasses= "com.niit")
public class UserDAOTestCase {


	private static AnnotationConfigApplicationContext context;
	
	@Autowired
	private static UserDAO userDAO;
	
	@Autowired
	private static User user;
	
	//we need create instance of AnnotationConfigApplicationContext
	//only once
	@BeforeClass
	public static void init()
	{
		context= new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		
		userDAO = (UserDAO) context.getBean("userDAO");
		
		user = (User) context.getBean("user");
		
	}
	
	@Test
	public void addUserTestCase()
	{
		user.setEmailID("jivan@gmail.com");
		user.setName("Jivanjot Singh ");
		user.setDetails(" M Vasi");
		user.setPassword("ivan@123");
		//boolean actual = userDAO.save(user);
		Assert.assertEquals("Add User Test Case" , true  , userDAO.save(user));
		
	}
	
	@Test
	public void updateUserTestCase()
	{
		//user = new User();
		//user.setEmailID("jivan@gmail.com");
		
		
		user = userDAO.get("koffi@gmail.com");
		
		user.setMobile("77777777");
		
		boolean actual = userDAO.update(user);
		
	    Assert.assertEquals("Update User", true, actual );
		
		
		
		
		
		
	}
	
	
	
	@Test
	public void getUserTestCase()
	{
		
		Assert.assertNotNull("Get User Test Cases", userDAO.get("jivan@gmail.com"));
	}
	
	//delete test case
	
	//get all user test cases
	
	//validate test
	
	
	
	
	@Test
	public void validateUserTestCase()
	{
	 Assert.assertNotNull("Validate Testcase",userDAO.validate("jaya@gmail.com", "jaya@1234"));
	}
	
	@Test
	public void deleteUserTestCase()
	{
	boolean actual=	   userDAO.delete("jivan@gmail.com");
	Assert.assertEquals(true, actual);
	}
	@Test
	public void getAllUsers()
	{
		int actualSize = userDAO.list().size();
		Assert.assertEquals(6, actualSize);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
