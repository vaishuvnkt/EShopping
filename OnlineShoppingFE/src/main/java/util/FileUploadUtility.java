package util;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtility {

	private static final String ABS_PATH = "C:\\Users\\sathish fc\\git\\EShopping\\OnlineShoppingFE\\src\\main\\webapp\\assets\\images\\";
	private static String REAL_PATH = "";
	
	private static final Logger logger = LoggerFactory.getLogger(FileUploadUtility.class);
	
	public static void uploadFile(HttpServletRequest request, MultipartFile file, String code)
	{
	
		//get real path
		REAL_PATH = request.getSession().getServletContext().getRealPath("/assets/images/");
		
		logger.info(REAL_PATH);
		
		//check whether all directories are present if not create it
		
		if(!new File(ABS_PATH).exists())
		{
			new File(ABS_PATH).mkdirs();
		}
    		
		if(!new File(REAL_PATH).exists())
		{
			new File(REAL_PATH).mkdirs();
		}
		
		try
		{
			
			//project directory upload
			System.out.println(ABS_PATH);
			file.transferTo(new File(ABS_PATH + code + ".jpg"));
			System.out.println("file added in abs path");
			
			//server upload 
			System.out.println(REAL_PATH);
			file.transferTo(new File(REAL_PATH + code + ".jpg"));
			System.out.println("file added to real path");
			
			}
		catch(IOException ex)
		{
			System.out.println(ABS_PATH);

			ex.printStackTrace();
		}
	}
}
