package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import model.dao.ICategoryDAO;
import model.dao.IProductDAO;
import model.entity.Category;
import model.entity.Product;
import util.FileUploadUtility;
import validation.ProductValidator;

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
			else if(operation.equals("category"))
			{
				mv.addObject("message" , "Category submitted successfully");
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

	@ModelAttribute("category")
	public Category getCategory()
	{
		return new Category();
		
	}
	
	//handling product submission
	@RequestMapping(value = "/products" , method = RequestMethod.POST)
	public String handleProductSubmisssion(@Valid @ModelAttribute("product") Product mPro , BindingResult results , Model model , HttpServletRequest request)
	{
		
		if(mPro.getId() == 0)//validate since it is new product
		{
			new ProductValidator().validate(mPro, results);	
		}
		else
		{
			if(!mPro.getFile().getOriginalFilename().equals(""))//file name not empty means validate else no need to validate while editing
			{
				new ProductValidator().validate(mPro, results);
			}
		}
				
		if(results.hasErrors())
		{
			
			model.addAttribute("userClickManageProducts", true);
			model.addAttribute("title", "Manage Items");
			return "page";
			
		}
		
		logger.info(mPro.toString());
		
		//create new record in database
		if(mPro.getId() == 0)//id is 0 for new products
		{
			proDAO.add(mPro);
		}
		else
		{
			proDAO.update(mPro);
		}
		
		if(!mPro.getFile().getOriginalFilename().equals(""))
		{
			FileUploadUtility.uploadFile(request,mPro.getFile(),mPro.getCode());
			System.out.println("file added");
		}
		
		return "redirect:/manage/products?operation=product";
		
	}
	
	//add new category
	@RequestMapping(value = "/category", method = RequestMethod.POST)
	public String handleCategorySubmission(@ModelAttribute Category category)
	{
		//add new category to db
		catDAO.add(category);
		
		return "redirect:/manage/products?operation=category";
	}
	
	@RequestMapping(value = "/{id}/product", method = RequestMethod.GET)
	public ModelAndView showEditProducts(@PathVariable int id)
	{
		
		ModelAndView mv = new ModelAndView("page");
		
		mv.addObject("userClickManageProducts", true);
		mv.addObject("title", "Manage Items");
		
		//fetching product details from db
		Product nPro = proDAO.get(id);
		//set the product details fetched from db in the spring form
		mv.addObject("product" , nPro);
		
		return mv;
	}
	

	@RequestMapping(value = "/product/{id}/activation", method = RequestMethod.POST)
	@ResponseBody
	public String handleProductActivation(@PathVariable int id)
	{
		
		Product pro = proDAO.get(id);
		boolean isActive = pro.isActive();
		//activating and deactivating product
		pro.setActive(!pro.isActive());
		//updating product status in database
		proDAO.update(pro);
		return (isActive)?"Item deactivated":"Item activated"; // ternary operator
		
	}
	
}


