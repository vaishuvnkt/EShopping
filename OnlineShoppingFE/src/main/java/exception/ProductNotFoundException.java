package exception;

import java.io.Serializable;

public class ProductNotFoundException extends Exception implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msg;
	
	public ProductNotFoundException()
	{
		this("Product is not available");
	}

	public ProductNotFoundException(String msg) 
	{
		this.msg = System.currentTimeMillis()+" : "+msg;
		// TODO Auto-generated constructor stub
	}

	public String getMsg() {
		return msg;
	}

}
