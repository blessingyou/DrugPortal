package com.versionsystem.web.controller.role;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.versionsystem.common.ResponseMap;
import com.versionsystem.web.model.menu.MenuUI;
import com.versionsystem.web.model.role.ExtJSFormResult;
import com.versionsystem.web.model.role.FileUploadBean;
import com.versionsystem.web.model.role.MultiFileUploadBean;


@RestController
@RequestMapping(value = "/upload")
public class FileUploadController {
	
	@Autowired
	private ResponseMap<MenuUI> responseMap;

	@RequestMapping(value="/role",method = RequestMethod.POST)
	public Map<String, ? extends Object> create(FileUploadBean uploadItem, BindingResult result){

		ExtJSFormResult extjsFormResult = new ExtJSFormResult();
		
		if (result.hasErrors()){
			for(ObjectError error : result.getAllErrors()){
				System.err.println("Error: " + error.getCode() +  " - " + error.getDefaultMessage());
			}
			
			//set extjs return - error
			extjsFormResult.setSuccess(false);
			
			return responseMap.mapError("File not uploaded!");
		}

		// Some type of file processing...
		System.err.println("-------------------------------------------");
		System.err.println("file name: " + uploadItem.getFile().getOriginalFilename() +" size:"+uploadItem.getFile().getSize() +" role desc:"+uploadItem.getRoleDesc());
		System.err.println("-------------------------------------------");
		try {
			byte[] bytes = uploadItem.getFile().getBytes();
            BufferedOutputStream stream =
                    new BufferedOutputStream(new FileOutputStream(new File("")));
            stream.write(bytes);
            stream.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		//set extjs return - sucsess
		extjsFormResult.setSuccess(true);
		
		return responseMap.mapOK("File uploaded!");
	}
	
	@RequestMapping(value="/multirole",method = RequestMethod.POST)
	public Map<String, ? extends Object> create2(MultiFileUploadBean uploadItem, BindingResult result){

		ExtJSFormResult extjsFormResult = new ExtJSFormResult();
		
		if (result.hasErrors()){
			for(ObjectError error : result.getAllErrors()){
				System.err.println("Error: " + error.getCode() +  " - " + error.getDefaultMessage());
			}
			
			//set extjs return - error
			extjsFormResult.setSuccess(false);
			
			return responseMap.mapError("File not uploaded!");
		}

		// Some type of file processing...
		System.err.println("-------------------------------------------");
		System.err.println("file name: " + uploadItem.getFile()[0].getOriginalFilename() +" size:"+uploadItem.getFile()[0].getSize() +" role desc:"+uploadItem.getRoleDesc());
		System.err.println("-------------------------------------------");
		System.err.println("file name: " + uploadItem.getFile()[1].getOriginalFilename() +" size:"+uploadItem.getFile()[1].getSize() +" role desc:"+uploadItem.getRoleDesc());
		
		
		extjsFormResult.setSuccess(true);
		
		return responseMap.mapOK("File uploaded!");
	}

}
