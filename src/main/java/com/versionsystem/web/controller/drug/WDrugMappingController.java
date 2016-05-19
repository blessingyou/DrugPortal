package com.versionsystem.web.controller.drug;


import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.versionsystem.service.impl.drug.WDrugMappingService;
import com.versionsystem.web.model.drug.WDrugMappingUI;


@RestController
@RequestMapping("/drugMapping")
public class WDrugMappingController {
	@Autowired
	private WDrugMappingService drugMappingService;
	
	private Logger logger = Logger.getLogger(WDrugMappingController.class);
	
	
	@RequestMapping(value = "/list", method = RequestMethod.POST)
    public  List<WDrugMappingUI> listAllItems() {
		
        return drugMappingService.findAll();
    }
	
    
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public  WDrugMappingUI create(@RequestBody WDrugMappingUI WDrugMappingUI) throws Exception {
    	     
        //System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
       // Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       // System.out.println(authentication.getName());
       // if(authentication!=null)
       // System.out.println(authentication.getName());
       long id=this.drugMappingService.create(WDrugMappingUI);
       WDrugMappingUI.setId(id);     
        return WDrugMappingUI;
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public  WDrugMappingUI update(@RequestBody WDrugMappingUI WDrugMappingUI) throws Exception {
    	this.drugMappingService.update(WDrugMappingUI);
        return WDrugMappingUI;
    }
    
    @RequestMapping(value = "/destroy", method = RequestMethod.POST)
    public  WDrugMappingUI destroy(@RequestBody WDrugMappingUI WDrugMappingUI) throws Exception {
    	this.drugMappingService.delete(WDrugMappingUI.getId());
        return WDrugMappingUI;
    }
    
    
    
    

}
