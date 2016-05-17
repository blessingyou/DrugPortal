package com.versionsystem.service.impl;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.BooleanExpression;
import com.versionsystem.common.ApplicationParas;
import com.versionsystem.common.BeanUtils;
import com.versionsystem.common.FilterRequest;
import com.versionsystem.common.ObjectConverter;
import com.versionsystem.common.ServiceMd5;
//import com.versionsystem.persistence.model.QUserId;
import com.versionsystem.persistence.model.Role;
import com.versionsystem.persistence.model.UserId;
import com.versionsystem.service.ConfigService;
//import com.versionsystem.persistence.model.UserId.COLUMNS;
import com.versionsystem.service.repo.UserRepository;
import com.versionsystem.service.repo.role.RoleRepository;
import com.versionsystem.web.model.user.UserUI;
import com.versionsystem.web.model.user.UserUIForMobile;

@Service

public class UserService {
	
	@Autowired	
	private HttpSession httpSession;

	@Autowired
	private UserRepository repository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private ConfigService configService;
	private Logger logger = Logger.getLogger(UserService.class);
	
	@Transactional
	public boolean registerMobile(UserUIForMobile user){
		UserId u=this.repository.findByUserId(user.getUserId());
		u.setEmail(user.getEmail());
		u.setMobileDeviceId(user.getMobileDeviceId());
		u.setMobileDeviceType(user.getMobileDeviceType());
		u.setLastUpdateDate(new Timestamp(new Date().getTime()));
		return true;
	}
	public boolean registerAvailable(String userId){
		UserId u=this.repository.findByUserId(userId);
		
			if(u.getMobileDeviceId()!=null)
				return false;
			else 
				return true;
		
	}
	public String checkSecurityCodeValid(String userId,String securityCode,long duration){
		UserId u=this.repository.findByUserId(userId);
		if(u==null){
			return "InvalidUser";
		}
		else{
		if(u.getSecuritycode()==null)
			return "NUL";
		else {
			if(!u.getSecuritycode().equals(securityCode))
				return "INVALID";
			else{
			long minutes=ObjectConverter.getDurationTimeBetweenDates(u.getSecuritycodeGenTime(), new Date());
			if(minutes<duration)
				return "VALID";
			else
				return "EXPIRED";
			}
			
		}
		}
	}
	public UserId getUserByUserId(String userId){
		return this.repository.findByUserId(userId);
	}
	public List<UserId> findAll() {
		return repository.findAll();
	}
	
	public List<UserUI> findAllUsers() {
		UserUI vo = null;
		 List<UserUI> rl=new ArrayList<UserUI>();
		List<UserId> l= repository.findAll();
		for(UserId temp:l){
			vo = new UserUI();
			BeanUtils.copyProperties(temp, vo);
			vo.setSecuritycode(null);
			vo.setSecuritycodeGenTime(null);
			vo.setMobileDeviceId(null);
			vo.setMobileDeviceType(null);
			
			rl.add(vo);
		}
		return rl;
	}
	public Page<UserId> findAll(Pageable pageable, List<FilterRequest> filters) {

		Predicate predicate = toPredicate(filters);
		
		return repository.findAll(predicate, pageable);
				
	}
	
	public String getCreateTimeForSecurityCode(String regId,String userId){
		
		String genTimeString="";
		Date createTime=null;
		SimpleDateFormat df=new SimpleDateFormat(configService.getProperty(ApplicationParas.APP_DATETIMEFORMAT));
		if(userId!=null){
			UserId u=this.repository.findByUserId(userId);
			if(u!=null){
				if(u.getMobileDeviceId()!=null && u.getSecuritycode()!=null && u.getSecuritycodeGenTime()!=null){
					createTime=u.getSecuritycodeGenTime();
					
		    		 genTimeString=df.format(u.getSecuritycodeGenTime());
		    		
				}
			}
		}
		else{
			List<UserId> list=this.repository.findByMobileDeviceIdOrderBySecuritycodeGenTimeDesc(regId);
			
			if(list.size()>0){
				UserId u=list.get(0);
				if(u.getMobileDeviceId()!=null && u.getSecuritycode()!=null && u.getSecuritycodeGenTime()!=null){
					createTime=u.getSecuritycodeGenTime();
		    		 genTimeString=df.format(u.getSecuritycodeGenTime());
		    		
				}
			}
		}
		if(!"".equals(genTimeString) && createTime!=null){
			//add duration info
			String temp=configService.getProperty(ApplicationParas.APP_SECURITYCODE_DURATION);
			int duration=Integer.parseInt(temp);
			Calendar c=Calendar.getInstance();
			c.setTime(createTime);
			c.add(Calendar.MINUTE, duration);
			Date until=c.getTime();
			genTimeString+=".The Security code above is valid until "+df.format(until);
			
		}
		return genTimeString;
	}
	
	public String getCurrentUser(){
		String userid="test";
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication!=null && authentication.getName()!=null && !"anonymousUser".equals(authentication.getName()))
			userid=authentication.getName();
		return userid;
	}
	public String getCurrentUserName(){
		String userid="test";
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication!=null && authentication.getName()!=null && !"anonymousUser".equals(authentication.getName()))
			userid=authentication.getName();
		if("test".equals(userid))
			return "test";
		else{
			UserId uid=this.repository.findByUserId(userid);
			if(uid!=null){
				return uid.getUserName();
			}
		}
		return userid;
	}
	
	public String getCurrentLocale(){
		String locale="en-US";
		if(httpSession.getAttribute("locale")!=null)
		  locale=httpSession.getAttribute("locale").toString();
		return locale;
	}
	public String getCurrentClinic(){
		//logger.info("clinic="+httpSession.getAttribute("clinic"));
		String clinic="1";
		if(httpSession.getAttribute("clinic")!=null)
		  clinic=httpSession.getAttribute("clinic").toString();
		return clinic;
	}
	public String getCurrentRole(){
		String userId=this.getCurrentUser();
		UserId uid=this.repository.findByUserId(userId);
		if(uid!=null){
			long rid=uid.getRoleId().longValue();
			Role r=this.roleRepository.findOne(rid);
			if(r!=null)
				return r.getRole();
			
		}
		return "";
	}
	public long getCurrentDoctorId(){
		String role=this.getCurrentRole();
		if("DOCTOR".equals(role)){
			String userId=this.getCurrentUser();
			UserId uid=this.repository.findByUserId(userId);
			/*if(uid!=null){
				if(uid.getDoctorId()!=null)
					return uid.getDoctorId();		
			}*/
		}
		return 0l;
	}
	public boolean validateUser(String userId,String password){
		UserId u=this.repository.findByUserIdAndPassword(userId, password);
		if(u!=null)
			return true;
		else
			return false;
	}
	
	@Transactional
	public Long save(UserUI user){
		
		UserId u=new UserId();
		BeanUtils.copyProperties(user, u);
		u.setPassword(ServiceMd5.md5("123456"));
		UserId temp=this.repository.saveAndFlush(u);
		return temp.getId();
	}
	@Transactional
	public Boolean update(UserUI user){
		
		UserId u=this.repository.findOne(user.getId());
		if(u!=null){
			u.setRoleId(user.getRoleId());
			u.setEmail(user.getEmail());
			u.setTelNo(user.getTelNo());
			u.setUserName(user.getUserName());
			u.setUserId(user.getUserId());
			u.setLocale(user.getLocale());
			u.setDoctorId(user.getDoctorId());
			u.setLastUpdateDate(new Timestamp(new Date().getTime()));
			u.setLastUpdateUser(this.getCurrentUser());
			this.repository.saveAndFlush(u);
			return true;
		}
		
		return false;
	}
	@Transactional
	public Boolean changePassword(UserUI user){
		
		UserId u=this.repository.findOne(user.getId());
		if(u!=null){
			if(user.getPassword()!= null && !"".equals(user.getPassword())){
				String md5=ServiceMd5.md5(user.getPassword());
				u.setPassword(md5);
			}
			u.setLastUpdateDate(new Timestamp(new Date().getTime()));
			u.setLastUpdateUser(this.getCurrentUser());
			this.repository.saveAndFlush(u);
			return true;
		}
		
		return false;
	}
	
	
	@Transactional
	public String changePassword(String userId,String currentPassword,String newPassword){
		UserId u=this.repository.findByUserId(userId);
		if(u!=null){
			String md5=ServiceMd5.md5(currentPassword);
			if(u.getPassword().equals(md5)){			
				u.setPassword(ServiceMd5.md5(newPassword));
				u.setLastUpdateDate(new Timestamp(new Date().getTime()));
				u.setLastUpdateUser(this.getCurrentUser());
				this.repository.saveAndFlush(u);
				return "true";
			}
			else{
				return "invalidpassword";
			}
		}
		else				
		return "invaliduser";
	}
	@Transactional
	public Boolean destroy(UserUI user){
		
		UserId u=this.repository.findOne(user.getId());
		if(u!=null){
			this.repository.delete(u);
			return true;
		}
		return false;
	}
	private Predicate toPredicate(final List<FilterRequest> filters) {
		logger.info("Entering predicates :: " + filters);
		//QUserId user = QUserId.userId1;
		BooleanExpression result = null;

		try {
			/*for (FilterRequest filter : filters) {

				COLUMNS column = COLUMNS.valueOf(filter.getProperty()
						.toUpperCase());
				BooleanExpression expression = null;

				switch (column) {
				case USERNAME:
					if (filter.getValue() != null
							&& !"".equals(filter.getValue())) {
						expression = user.userName.like("%" + filter.getValue()
								+ "%");
					}
					break;
				case USERID:
					if (filter.getValue() != null
							&& !"".equals(filter.getValue())) {
						expression = user.userId.like("%"
								+ filter.getValue() + "%");
					}
					break;
				case EMAIL:
					if (filter.getValue() != null
							&& !"".equals(filter.getValue())) {
						expression = user.email.like("%" + filter.getValue()
								+ "%");
					}
					break;
				
				}
				if (expression != null) {
					if (result != null) {
						result = result.and(expression);
					} else {
						result = expression;
					}
				}
			}*/
		} catch (Exception ex) {
			logger.error(ex);
		}
		logger.info("Result Predicate :: "
				+ (result != null ? result.toString() : ""));

		logger.info("Exiting predicates");
		return result;
	}

}
