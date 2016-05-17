package com.versionsystem.web.controller.role;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.versionsystem.common.ResponseMap;
import com.versionsystem.web.model.menu.MenuUI;
import com.versionsystem.web.model.role.ExtJSFormResult;
import com.versionsystem.web.model.role.FileUploadBean;
import com.versionsystem.web.model.role.MultiFileUploadBean;


@RestController
@RequestMapping(value = "/download")
public class FileDownloadController {
	
	
	private Logger logger = Logger.getLogger(FileDownloadController.class);

	@RequestMapping(value="/role",method = RequestMethod.GET)
	public void downloadFile(@RequestParam String fileName, HttpServletResponse response){

		try {
		      
		      InputStream is = new FileInputStream(new File("C:\\software\\testing-tomcat\\webapps\\SignatureWeb\\123456.pdf"));	      
		      response.setContentType("application/pdf");
		      org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
		      response.flushBuffer();
		      
		      
		      
		    } catch (IOException ex) {
		      logger.info("Error writing file to output stream. Filename was '{}'");
		      throw new RuntimeException("IOError writing file to output stream");
		    }
		
		
	}

}
