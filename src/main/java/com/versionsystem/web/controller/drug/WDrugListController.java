package com.versionsystem.web.controller.drug;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.versionsystem.common.ObjectConverter;
import com.versionsystem.service.impl.drug.WDrugListService;
import com.versionsystem.web.model.drug.WDrugListUI;


@RestController
@RequestMapping("/drugList")
public class WDrugListController {
	@Autowired
	private WDrugListService drugListService;
	
	//private Logger logger = Logger.getLogger(WDrugListController.class);
	
	
	@RequestMapping(value = "/list", method = RequestMethod.POST)
    public  List<WDrugListUI> listAllItems() {
		
        return drugListService.findAll();
    }
	
	@RequestMapping(value = "/listForSelect", method = RequestMethod.POST)
    public  List<WDrugListUI> listAllItemsForSelect() {
		List<WDrugListUI> rl=new ArrayList<WDrugListUI>();
		WDrugListUI ui=new WDrugListUI();
		ui.setId(0);
		ui.setDrugName("");
		rl.add(ui);
		rl.addAll(drugListService.findAll());
        return rl;
    }
	
    
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public  WDrugListUI create(@RequestBody WDrugListUI WDrugListUI) throws Exception {
    	     
        //System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
       // Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       // System.out.println(authentication.getName());
       // if(authentication!=null)
       // System.out.println(authentication.getName());
       long id=this.drugListService.create(WDrugListUI);
       WDrugListUI.setId(id);     
        return WDrugListUI;
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public  WDrugListUI update(@RequestBody WDrugListUI WDrugListUI) throws Exception {
    	this.drugListService.update(WDrugListUI);
        return WDrugListUI;
    }
    
    @RequestMapping(value = "/destroy", method = RequestMethod.POST)
    public  WDrugListUI destroy(@RequestBody WDrugListUI WDrugListUI) throws Exception {
    	this.drugListService.delete(WDrugListUI.getId());
        return WDrugListUI;
    }
    
    
    
    

}
