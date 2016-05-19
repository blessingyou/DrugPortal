package com.versionsystem.web.controller.drug;


import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.versionsystem.service.impl.drug.WDrugMappingAlertService;
import com.versionsystem.web.model.drug.WDrugMappingAlertUI;


@RestController
@RequestMapping("/drugMapping")
public class WDrugMappingAlertController {
	@Autowired
	private WDrugMappingAlertService drugMappingAlertService;
	
	private Logger logger = Logger.getLogger(WDrugMappingAlertController.class);
	
	
	@RequestMapping(value = "/list", method = RequestMethod.POST)
    public  List<WDrugMappingAlertUI> listAllItems() {
		
        return drugMappingAlertService.findAll();
    }
	
    
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public  WDrugMappingAlertUI create(@RequestBody WDrugMappingAlertUI WDrugMappingAlertUI) throws Exception {
    	     
        //System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
       // Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       // System.out.println(authentication.getName());
       // if(authentication!=null)
       // System.out.println(authentication.getName());
       long id=this.drugMappingAlertService.create(WDrugMappingAlertUI);
       WDrugMappingAlertUI.setId(id);     
        return WDrugMappingAlertUI;
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public  WDrugMappingAlertUI update(@RequestBody WDrugMappingAlertUI WDrugMappingAlertUI) throws Exception {
    	this.drugMappingAlertService.update(WDrugMappingAlertUI);
        return WDrugMappingAlertUI;
    }
    
    @RequestMapping(value = "/destroy", method = RequestMethod.POST)
    public  WDrugMappingAlertUI destroy(@RequestBody WDrugMappingAlertUI WDrugMappingAlertUI) throws Exception {
    	this.drugMappingAlertService.delete(WDrugMappingAlertUI.getId());
        return WDrugMappingAlertUI;
    }
    
    
    
    

}
