package com.versionsystem.web.controller.common;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.versionsystem.common.ApplicationParas;
import com.versionsystem.service.ConfigService;
import com.versionsystem.service.impl.ParameterService;
import com.versionsystem.service.impl.UserService;
import com.versionsystem.web.model.ClientParas;


@RestController
@RequestMapping("/parameters")
public class ParameterController {
	@Autowired
	private ParameterService service;
	@Autowired
	private ConfigService configService;
	@Autowired
	private UserService userService;


	private Logger logger = Logger.getLogger(ParameterController.class);
	

	
	@RequestMapping(value="/list/{moduleName}")
	public ClientParas listPara(@PathVariable String moduleName){
		return this.service.findByModule(moduleName);
	}
	
	@RequestMapping(value = "/readSystemParas", method = RequestMethod.GET)
    public @ResponseBody List<ClientParas> readSystemParas() {
		List<ClientParas> l=new ArrayList<ClientParas>();
		ClientParas cp=new ClientParas();
		//String jsonDateFormat=configService.getProperty("app.jsonDateFormat");
		cp.setCurrentUserName(userService.getCurrentUserName());
		cp.setCurrentUser(userService.getCurrentUser());
		cp.setUserRole(userService.getCurrentRole());
		
		cp.setAppDateFormat(configService.getProperty(ApplicationParas.APP_DATEFORMAT));
		cp.setAppDateTimeFormat(configService.getProperty(ApplicationParas.APP_DATETIMEFORMAT));
		String locale=this.userService.getCurrentLocale();
		if(locale!=null)
			cp.setAppLocale(locale);
		else
			cp.setAppLocale(configService.getProperty(ApplicationParas.APP_LOCALE));
		cp.setAppTimezone(configService.getProperty(ApplicationParas.APP_TIMEZONE));
		cp.setJsonDateTimeFormat(configService.getProperty(ApplicationParas.APP_JSONDATEFORMAT));
		l.add(cp);
		return l;
	}
	
	

}
