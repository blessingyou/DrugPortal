package com.versionsystem.web.controller.role;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.versionsystem.common.ResponseMap;
import com.versionsystem.service.impl.role.RoleService;
import com.versionsystem.web.model.menu.MenuUI;
import com.versionsystem.web.model.role.InvoiceDetailVO;
import com.versionsystem.web.model.role.RoleUI;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
@RestController
@RequestMapping("/role")
public class RoleController {
	@Autowired
	private RoleService roleService;

	@Autowired
	private ResponseMap<MenuUI> responseMap;

	private Logger logger = Logger.getLogger(RoleController.class);
	

	
	@RequestMapping(value="/read")
	public List<RoleUI> list() throws Exception{
		try {								
				return roleService.findAll();
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	@RequestMapping(value="/readExcludeAdmin")
	public List<RoleUI> list2() throws Exception{
		List<RoleUI> rl=new ArrayList<RoleUI>();
		try {								
			List<RoleUI> list= roleService.findAll();
			for(RoleUI temp:list){
				if(!temp.getRole().equals("ADMIN"))
					rl.add(temp);
			}
			return rl;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	@RequestMapping(value="/update", method= RequestMethod.POST)
    public RoleUI update(@RequestBody RoleUI rolevo) throws RuntimeException {
		
        if( roleService.save(rolevo))
        	return rolevo;
        else
        	return null;
    }
	@RequestMapping(value="/create",method= RequestMethod.POST)
	public RoleUI create(@RequestBody RoleUI rolevo) throws RuntimeException{
		if( roleService.save(rolevo))
        	return rolevo;
        else
        	return null;
	}
	
	
	
	@RequestMapping(value="/destroy", method= RequestMethod.POST)
    public RoleUI destroy(@RequestBody RoleUI rolevo) throws Exception {
		
		if( roleService.delete(rolevo.getId()))
        	return rolevo;
        else
        	return null;
    }

}
