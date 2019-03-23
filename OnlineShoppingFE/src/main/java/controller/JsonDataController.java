package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import model.dao.IProductDAO;
import model.entity.Product;

@Controller
@RequestMapping("/json/data")
public class JsonDataController {

	@Autowired
	private IProductDAO proDAO;
	
	@RequestMapping("/all/products")
	@ResponseBody
	public List<Product> getAllProducts() 
	{
		return proDAO.listActiveProducts();
	}
	
	@RequestMapping("admin/all/products")
	@ResponseBody
	public List<Product> getAllProductsForAdmin() 
	{
		return proDAO.list();
	}

	
	@RequestMapping("/category/{id}/products")
	@ResponseBody
	public List<Product> getAllProductsByCategory(@PathVariable int id) 
	{
		return proDAO.listActiveProductsByCategory(id);
	}
	
	
	@RequestMapping("/mv/products")
	@ResponseBody
	public List<Product> getMostViewedProducts() {		
		return proDAO.getProductsByParam("views", 5);				
	}
		
	@RequestMapping("/mp/products")
	@ResponseBody
	public List<Product> getMostPurchasedProducts() {		
		return proDAO.getProductsByParam("purchases", 5);				
	}
	
}
