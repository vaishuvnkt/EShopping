package validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import model.entity.Product;

public class ProductValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz)
	{
		// TODO Auto-generated method stub
		return Product.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		Product pro = (Product)target;
		
		//check whether file has been selected or not
		if(pro.getFile() == null || pro.getFile().getOriginalFilename().equals(""))
		{
			
			errors.rejectValue("file", null, "Please select an image" );
			return;
			
		}
		
		if(!( pro.getFile().getContentType().equals("image/jpeg") || pro.getFile().getContentType().equals("image/png")|| pro.getFile().getContentType().equals("image/gif") ))
		{
			
			errors.rejectValue("file", null, "Use only images to upload");
			return;
			
		}
		
	}

}
