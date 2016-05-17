package com.versionsystem.service.impl.role;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.management.RuntimeErrorException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.BooleanExpression;
import com.versionsystem.common.BeanUtils;
import com.versionsystem.common.BooleanExpressionUtils;
import com.versionsystem.common.FilterRequest;
import com.versionsystem.common.ResponseMap;
//import com.versionsystem.persistence.model.QRole;
import com.versionsystem.persistence.model.Role;
import com.versionsystem.service.impl.UserService;
import com.versionsystem.service.repo.role.RoleRepository;
import com.versionsystem.web.model.role.RoleUI;

@Service
@Transactional
public class RoleService {

	@Autowired
	private RoleRepository repository;
	@Autowired
	private UserService userService;
	
	private Logger logger = Logger.getLogger(RoleService.class);

	public List<RoleUI> findAll() {

		List<RoleUI> rl = new ArrayList<RoleUI>();

		List<Role> l = repository.findAll(new Sort(Sort.Direction.ASC, "role"));
		RoleUI vo = null;
		for (Role o : l) {
			vo = new RoleUI();
			BeanUtils.copyProperties(o, vo);
			rl.add(vo);
		}

		return rl;
	}
	public boolean save(RoleUI vo){
		try {
			Role r=new Role();
			r.setId(vo.getId());
			r.setRole(vo.getRole());
			r.setRoleDesc(vo.getRoleDesc());
			r.setLastUpdateDate(new Timestamp(new Date().getTime()));
			r.setLastUpdateUser(userService.getCurrentUser());
			this.repository.saveAndFlush(r);
			return true;
			
		} catch (Exception e) {
			logger.error(e);
			throw new RuntimeException(e);
		}
		
	}
	
	public boolean delete(long id){
		try {
			Role r=this.repository.findOne(id);		
			this.repository.delete(r);
			return true;
			
		} catch (Exception e) {
			logger.error(e);
			throw new RuntimeException(e);
		}
		
	}

	

	private Predicate toPredicate(RoleUI filter) {
		logger.info("Entering predicates :: " + filter);
		// QUserId user = QUserId.userId1;
		//QRole role=QRole.role1;
		BooleanExpression result = null;

		try {
			BooleanExpression expression = null;
			/*if(filter.getRole()!=null && !"".equals(filter.getRole())) {
				expression = role.role.like("%"+filter.getRole()+"%");
				result=BooleanExpressionUtils.and(result, expression);
			}
			if(filter.getRoleDesc()!=null && !"".equals(filter.getRoleDesc())) {
				expression = role.roleDesc.like("%"+filter.getRoleDesc()+"%");
				result=BooleanExpressionUtils.and(result, expression);
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
