package controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import model.dao.ICategoryDAO;
import model.dao.IProductDAO;
import model.entity.Category;
import model.entity.Product;

@Controller
@RequestMapping("/manage")
public class ManagementController {
	
	@Autowired
	private ICategoryDAO catDAO;
	
	@Autowired
	private IProductDAO proDAO;
	
	private static final Logger logger = LoggerFactory.getLogger(ManagementController.class);
	
	@RequestMapping(value = "/products" , method = RequestMethod.GET)
	public ModelAndView showManageProducts(@RequestParam(name = "operation" , required = false) String operation)
	{
		
		ModelAndView mv = new ModelAndView("page");
		
		mv.addObject("userClickManageProducts", true);
		mv.addObject("title", "Manage Items");
		
		Product nPro = new Product();
		nPro.setSupplierID(1);
		nPro.setActive(true);
		
		mv.addObject("product" , nPro);
		
		// to display success msg after submitting the product
		if(operation != null)
		{
			
			if(operation.equals("product"))
			{
				mv.addObject("message" , "Item submitted successfully");
			}
			
		}
		
		return mv;
	}
	
	//returning categories for the drop-down list in product manager spring form
	@ModelAttribute("Categories")
	public List<Category> getCategories()
	{
		return catDAO.list();
	}
	
	//handling product submission
	@RequestMapping(value = "/products" , method = RequestMethod.POST)
	public String handleProductSubmisssion(@ModelAttribute("products") Product mPro)
	{
		
		logger.info(mPro.toString());
		
		//create new record in database
		proDAO.add(mPro);
		
		return "redirect:/manage/products?operation=product";
		
	}

}

