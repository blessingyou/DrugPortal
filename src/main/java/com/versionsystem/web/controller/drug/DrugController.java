package com.versionsystem.web.controller.drug;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.versionsystem.common.ResponseMap;
import com.versionsystem.service.impl.drug.DrugService;
import com.versionsystem.web.model.drug.WDrugAppFormUI;

@RestController
@RequestMapping("/drug")
public class DrugController {
	
	@Autowired
	DrugService drugService;

	private Logger logger = LoggerFactory.getLogger(DrugController.class); 
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public Map<String ,Object> saveDrugAppForm(@RequestBody Map<String,Object> model,HttpServletRequest request){
		
		String actionIp=request.getRemoteAddr();
		
		Map<String,Object> rm=drugService.checkParasForDrug(model,actionIp,false);
		if((Boolean)rm.get("success")){
			return drugService.saveDrugList(rm);
		}
		else
			return rm;
		//return rm.mapOK(new WDrugAppFormUI());
	}
	@RequestMapping(value="/check",method=RequestMethod.POST)
	public Map<String ,Object> checkDrugAppForm(@RequestBody Map<String,Object> model,HttpServletRequest request){
		
		String actionIp=request.getRemoteAddr();
		
		Map<String,Object> rm=drugService.checkParasForDrug(model,actionIp,true);
		if((Boolean)rm.get("success")){
			return drugService.checkDrugList(rm);
		}
		else
			return rm;
		//return rm.mapOK(new WDrugAppFormUI());
	}
	
	
}
