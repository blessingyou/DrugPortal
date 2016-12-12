package com.versionsystem.web.controller.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.versionsystem.common.ObjectConverter;
import com.versionsystem.common.ResponseMap;
import com.versionsystem.persistence.model.UserId;
import com.versionsystem.service.impl.UserService;
import com.versionsystem.web.model.menu.MenuUI;
import com.versionsystem.web.model.user.UserUI;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService UserService;

	@Autowired
	private ResponseMap<MenuUI> responseMap;

	private Logger logger = Logger.getLogger(UserController.class);
	

	
	@RequestMapping(value="/read")
	public List<UserUI> list() throws Exception{
		try {								
				return UserService.findAllUsers();
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	@RequestMapping(value="/update", method= RequestMethod.POST)
    public UserUI update(@RequestBody UserUI uservo) throws RuntimeException {
		
        if( UserService.update(uservo))
        	return uservo;
        else
        	return null;
    }
	@RequestMapping(value="/changePassword", method= RequestMethod.POST)
    public String changePassword(@RequestBody Map<String,Object> model) throws RuntimeException {
		String userId=null,newPassword=null,confirmPassword=null,currentPassword=null;
							
			userId=(String)model.get("userId");
			currentPassword=(String)model.get("currentPassword");
			newPassword=(String)model.get("newPassword");
			
        return this.UserService.changePassword(userId,currentPassword,newPassword);
        	
    }
	@RequestMapping(value="/create",method= RequestMethod.POST)
	public UserUI create(@RequestBody UserUI uservo) throws RuntimeException{
		Long id= UserService.save(uservo);
		if(id>0){
			uservo.setId(id);
        	return uservo;
		}
        else
        	return null;
	}
	
	
	
	@RequestMapping(value="/destroy", method= RequestMethod.POST)
    public UserUI destroy(@RequestBody UserUI uservo) throws Exception {
		
		if( UserService.destroy(uservo))
        	return uservo;
        else
        	return null;
    }
	

}
