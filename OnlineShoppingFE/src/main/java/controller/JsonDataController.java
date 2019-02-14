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
	
	@RequestMapping("/category/{id}/products")
	@ResponseBody
	public List<Product> getAllProductsByCategory(@PathVariable int id) 
	{
		return proDAO.listActiveProductsByCategory(id);
	}
}
