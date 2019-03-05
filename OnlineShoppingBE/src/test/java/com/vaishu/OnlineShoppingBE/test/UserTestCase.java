package com.vaishu.OnlineShoppingBE.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import model.dao.IUserDAO;
import model.entity.Address;
import model.entity.Cart;
import model.entity.User;

public class UserTestCase {

	private static AnnotationConfigApplicationContext context;
	private static IUserDAO userDAO;
	private User user = null;
	private Address address = null;
	private Cart cart = null;
	
	@BeforeClass
	public static void init()
	{
		context = new AnnotationConfigApplicationContext();
		context.scan("model");
		context.refresh();
		
		userDAO = (IUserDAO)context.getBean("userDAO");
	}
	
/*	@Test
	public void testAdd()
	{
		user = new User();
		user.setFirstname("Deepika");
		user.setLastname("Padukone");
		user.setEmail("dprs@gmail.com");
		user.setContactNumber("1234567890");
		user.setPassword("abcde");
		user.setRole("USER");
		//enable field is by default true
		
		//add user in db
		assertEquals("Failed to add user",true,userDAO.addUser(user));
		
		address = new Address();
		address.setAddressLineOne("101/B Jadoo Society, Krissh nagar");
		address.setAddressLineTwo("Near Kaabil Store");
		address.setCity("Mumbai");
		address.setState("Maharashtra");
		address.setCountry("India");
		address.setPostalCode("400001");
		address.setBilling(true);//setBilling is true nd setShipping is false while adding residing address to user 

		//link the user table with address table using user_id
		address.setUserId(user.getId());
	
		//add address in db
		assertEquals("Failed to add address",true,userDAO.addAddress(address));
		
		//create cart only for "USER"
		if(user.getRole().equals("USER"))
		{
			
			cart = new Cart();
			//link cart table with user table
			cart.setUser(user);
			
			//add cart details to db
			assertEquals("Failed to add cart",true,userDAO.addCart(cart));
			
			//add shipping address to the user
			address = new Address();
			address.setAddressLineOne("201/B Jadoo Society, Kishan kannaiyah nagar");
			address.setAddressLineTwo("Near Kudrat Store");
			address.setCity("Mumbai");
			address.setState("Maharashtra");
			address.setCountry("India");
			address.setPostalCode("400001");
			//set shipping should be true for adding shipping address to the user nd setBilling in will be false
			address.setShipping(true);			
		
			// link address table with user since we are adding different address to the same user
			address.setUserId(user.getId());

			//add shipping address to db
			assertEquals("Failed to add in db",true,userDAO.addAddress(address));
		}
		
	}
*/	
	
/*	@Test
	public void testAdd()
	{
		user = new User();
		user.setFirstname("Deepika");
		user.setLastname("Padukone");
		user.setEmail("dprs@gmail.com");
		user.setContactNumber("1234567890");
		user.setPassword("abcde");
		user.setRole("USER");
		//enable field is by default true
						
		//create cart only for "USER"
		if(user.getRole().equals("USER"))
		{
			
			cart = new Cart();
			//link cart table with user table
			cart.setUser(user);

			//attach cart with user
			user.setCart(cart);
		}

		//add user in db
		assertEquals("Failed to add user",true,userDAO.addUser(user));

	}*/
/*
	@Test
	public void testAddAddress()
	{
		//add user
		user = new User();
		user.setFirstname("Deepika");
		user.setLastname("Padukone");
		user.setEmail("dprs@gmail.com");
		user.setContactNumber("1234567890");
		user.setPassword("abcde");
		user.setRole("USER");
		//enable field is by default true
		
		//add user in db
		assertEquals("Failed to add user",true,userDAO.addUser(user));
		
		address = new Address();
		address.setAddressLineOne("101/B Jadoo Society, Krissh nagar");
		address.setAddressLineTwo("Near Kaabil Store");
		address.setCity("Mumbai");
		address.setState("Maharashtra");
		address.setCountry("India");
		address.setPostalCode("400001");
		address.setBilling(true);//setBilling is true nd setShipping is false while adding residing address to user 

		//link the user table with address table using user_id
		address.setUser(user);
	
		//add address in db
		assertEquals("Failed to add address",true,userDAO.addAddress(address));
		
		//add shipping address to the user
		address = new Address();
		address.setAddressLineOne("201/B Jadoo Society, Kishan kannaiyah nagar");
		address.setAddressLineTwo("Near Kudrat Store");
		address.setCity("Mumbai");
		address.setState("Maharashtra");
		address.setCountry("India");
		address.setPostalCode("400001");
		//set shipping should be true for adding shipping address to the user nd setBilling in will be false
		address.setShipping(true);			
	
		// link address table with user since we are adding different address to the same user
		address.setUser(user);

		//add shipping address to db
		assertEquals("Failed to add in db",true,userDAO.addAddress(address));

	}

*/

	@Test
	public void testGetAddresses()
	{
		user = userDAO.getByEmail("dprs@gmail.com");
		assertEquals("Failed to get billing addresses","Mumbai",userDAO.getBillingAddress(user).getCity());
		assertEquals("Failed to get the shipping addresses",2,userDAO.getShippingAddresses(user).size());
	}

}
