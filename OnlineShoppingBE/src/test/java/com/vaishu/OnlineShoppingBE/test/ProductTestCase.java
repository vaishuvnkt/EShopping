package com.vaishu.OnlineShoppingBE.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import model.dao.IProductDAO;
import model.entity.Product;

public class ProductTestCase
 {

private static AnnotationConfigApplicationContext context;
	
	@Autowired
	private static IProductDAO proDAO;
	
	
	private Product p=new Product();
	
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("model");//BASE PACKAGE OF IProductDAO
		context.refresh();
		proDAO = (IProductDAO)context.getBean(IProductDAO.class);
	}
	
	/*
	@Test
	public void testCRUDProduct() {
		
		// add operation
		
		
		
		p = new Product();
		
		p.setName("OPPO F9 pro");
		p.setBrand("OPPO");
		p.setCategoryID(3);
		p.setActive(true);
		p.setSupplierID(3);
		p.setUnitPrice(24000);
		p.setDescription("5 minutes charge 2 hours of talk!!!!");
		assertEquals("OOPS!Something went wrong",true,proDAO.add(p));
		
				
		// fetching and updating the category
		p = proDAO.get(1);
		
		p.setUnitPrice(80000);
		
		assertEquals("OOPS!Something went wrong",true,proDAO.update(p));
		
		// delete the category
		assertEquals("OOPS!Something went wrong",true,proDAO.delete(p));
		
		
		//fetching the list
		assertEquals("OOPS!Something went wrong",6,proDAO.list().size());		
				
		
	}*/
	
	@Test
	public void testListActiveProducts()
	{
		assertEquals("OOPS!Something went wrong",5,proDAO.listActiveProducts().size());
	}
	
	@Test
	public void testListActiveProductsByCategory()
	{
		assertEquals("OOPS!Something went wrong",3,proDAO.listActiveProductsByCategory(3).size());
		assertEquals("OOPS!Something went wrong",2,proDAO.listActiveProductsByCategory(1).size());
	}
	
	@Test
	public void testGetLatestActiveProduct()
	{
		assertEquals("OOPS!Something went wrong",3,proDAO.getLatestActiveProducts(3).size());
	}
}

