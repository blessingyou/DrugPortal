package com.versionsystem.web.controller.drug;


import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.versionsystem.common.ApplicationParas;
import com.versionsystem.common.ServiceMd5;
import com.versionsystem.persistence.model.UserId;
import com.versionsystem.service.ConfigService;
import com.versionsystem.service.impl.UserService;
import com.versionsystem.service.impl.notification.PushMessageService;
import com.versionsystem.web.model.user.UserUI;
import com.versionsystem.web.model.user.UserUIForMobile;



@RestController
@RequestMapping("/mobile")
public class MobileController {
	@Autowired
	private ConfigService configService;
	@Autowired
	private UserService userService;
	
	
	@Autowired
	private PushMessageService pushMessageService;
	
	private Logger logger = LoggerFactory.getLogger(MobileController.class); 
	
	
	@RequestMapping(value="/checkValidateSecurityCode")
	public String CheckValidateSecurityCode(HttpServletRequest request){
		String securityLogin=configService.getProperty(ApplicationParas.APP_SECURITY_LOGIN);
		logger.info("security level:"+securityLogin);
		if("Y".equals(securityLogin)){
			String ip=request.getRemoteAddr();	
			String securityip=configService.getProperty(ApplicationParas.APP_SECURITY_IP_ADDRESS);
			logger.info("ip="+ip);
			logger.info("securityip="+securityip);
			if(ip.startsWith("192.168.") || securityip.indexOf(ip)!=-1){
				request.getSession(true).setAttribute("validateSecurityCode", false);
				
				return "false";
			}
			else{
				request.getSession(true).setAttribute("validateSecurityCode", true);
				
				return "true";
			}
		}
		else if("ALL".equals(securityLogin)){
			request.getSession(true).setAttribute("validateSecurityCode", true);
			return "true";
		}
		else{
			request.getSession(true).setAttribute("validateSecurityCode", false);
			return "false";
		}
		
	}
	
	@RequestMapping(value="/genSecurityCode/{userId}")
	public String genSecurityCode(@PathVariable String userId,HttpServletRequest request){
		
		if(this.userService.getUserByUserId(userId)==null)
			return "InvalidUser";
		else{
			if(!this.userService.registerAvailable(userId)){
				String code=this.pushMessageService.genSecurityCode(userId);
				logger.info("gen a code:"+code);
				return code;
			}
			else
				return "Unregistered";
		}
		
	}
	@RequestMapping(value="/getCreateSecurityCodeTime",method=RequestMethod.POST)
	public String getCreateSecurityCodeTime(@RequestParam("regIdUserId") String regIdUserId,HttpServletRequest request){
		System.out.println("ID:"+regIdUserId);
		if(regIdUserId!=null){
			String[] paras=regIdUserId.split(",");
			String regId=paras[0];
			String userId=paras[1];
			
			return this.userService.getCreateTimeForSecurityCode(regId,userId.equals("NULL")?null:userId);
		}
		return "";
		
	}
	@RequestMapping(value="/checkSecurityCodeValid/{userId}/{securityCode}")
	public String checkSecurityCodeValid(@PathVariable String userId,@PathVariable String securityCode,HttpServletRequest request){
		String temp=configService.getProperty(ApplicationParas.APP_SECURITYCODE_DURATION);
		long duration=Long.parseLong(temp);
		String result=this.userService.checkSecurityCodeValid(userId, securityCode, duration);
		return result;
		
	}
	@RequestMapping(value="/registerMobile",method=RequestMethod.POST)
	public String genSecurityCode(@RequestBody UserUIForMobile user){
		String password=ServiceMd5.md5(user.getPassword());
		boolean result=this.userService.validateUser(user.getUserId(), password);
		if(result){
			if(this.userService.registerAvailable(user.getUserId())){
				if(this.userService.registerMobile(user))
					return "Y";
				else
					return "N";
			}
			else
				return "Registered";
		}
		else
			return "InvalidPassword";
	}
	@RequestMapping(value="/loginByMobile",method=RequestMethod.POST)
	public String login(@RequestBody UserUIForMobile user){
		String password=ServiceMd5.md5(user.getPassword());
		boolean result=this.userService.validateUser(user.getUserId(), password);
		if(result)
			return "Y";
		else
			return "N";
	}
	
	@RequestMapping(value="/getUser/{userId}", method= RequestMethod.GET)
    public UserUI getUserById(@PathVariable String userId) throws Exception {
		
		UserUI uservo=new UserUI();
		UserId uid=this.userService.getUserByUserId(userId);
		if(uid!=null){
			uservo.setLocale(uid.getLocale());
		}
		else
			uservo.setLocale("zh-HK");
		return uservo;
    }

	
    
    

}
