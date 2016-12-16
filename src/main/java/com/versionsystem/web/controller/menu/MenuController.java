package com.versionsystem.web.controller.menu;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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
import com.versionsystem.common.ObjectConverter;
import com.versionsystem.common.ResponseMap;
import com.versionsystem.persistence.model.MenusCtrlAccess;
import com.versionsystem.service.impl.menu.MenuService;
import com.versionsystem.web.model.menu.LastestMenuListUI;
import com.versionsystem.web.model.menu.MenuAccessUI;
import com.versionsystem.web.model.menu.MenuUI;


@RestController
@RequestMapping("/menu")
public class MenuController {
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private MenuService menuService;

	@Autowired
	private ResponseMap<MenuUI> responseMap;

	private Logger logger = Logger.getLogger(MenuController.class);
	

	@RequestMapping(value = "/list2")	 
	public Map<String, ? extends Object> list(@RequestParam int page,
			@RequestParam int start, @RequestParam int limit,
			@RequestParam(required = false) String data) throws Exception {
		try {
			Pageable pageable = new PageRequest(page - 1, limit);
			ObjectMapper om=new ObjectMapper();
			MenuUI filter=om.readValue(data, MenuUI.class);
			return menuService.findAll(pageable,filter);
			
		} catch (Exception e) {
			logger.error(e);
			return responseMap
					.mapError("Error retrieving account from database.");
		}
	}
	@RequestMapping(value="/list")
	public Map<String, ? extends Object> list() throws Exception {
		try {	
			
			
				return menuService.findAll();
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	@RequestMapping(value="/tree/forUser")
	public List<MenuUI> treeForUser() throws Exception{
		try {	
						
				return menuService.findTreeMenu(false,"");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			return null;
		}
	}
	@RequestMapping(value="/tree/forRole/{roleId}")
	public List<MenuUI> tree(@PathVariable String roleId) throws Exception{
		try {	
			
			
			return menuService.findTreeMenu(true,roleId);
			
		} catch (Exception e) {
			logger.error("findTreeMenu exceptoin:",e);
			return null;
		}
	}
	
	@RequestMapping(value="/userTree")
	public List<LastestMenuListUI> findLastestMenu() throws Exception{
		try {	
				
				return menuService.findLastestMenuListForUser();
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	
	@RequestMapping(value = "/saveLastMenu", method = RequestMethod.POST)
    public @ResponseBody List<LastestMenuListUI> saveLastMenu(@RequestBody ArrayList<Map<String,Object>> models)  throws Exception{
    	List<LastestMenuListUI> mlist = new ArrayList<LastestMenuListUI>();
    	for (Map<String, Object> model : models) {
    		//System.out.println(model.toString());
    		LastestMenuListUI vo=new LastestMenuListUI();
    		vo.setMenuId(ObjectConverter.toLong(model.get("menuId")));
    		vo.setUserId((String)model.get("userId"));
    		vo.setText((String)model.get("text"));
    		vo.setUrl((String)model.get("url"));
    		vo.setSpriteCssClass((String)model.get("spriteCssClass"));
    		mlist.add(vo);
    	}
    	if(this.menuService.saveLastestMenuList(mlist))
    		return mlist;
    	else
    		return  new ArrayList<LastestMenuListUI>();
	}
	
	@RequestMapping(value = "/saveRoleMenu/{roleId}", method = RequestMethod.POST)
    public @ResponseBody String saveRoleMenu(@RequestBody String menuIds,@PathVariable String roleId) throws Exception {
    	
		if(this.menuService.saveRoleMenuList(roleId, menuIds))
			return menuIds;
		else
			return null;
	}
	
	@RequestMapping(value="/readLabel")
	public List<MenuUI> readLabel() throws Exception{
		try {								
				return menuService.readSystemMenuLabel();
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	@RequestMapping(value="/updateLabel", method= RequestMethod.POST)
    public MenuUI updateLabel(@RequestBody MenuUI menuUI) throws Exception {
		
        return menuService.updateSystemMenuLabel(menuUI);
        
    }
	
	@RequestMapping(value="/updateMenuAccessLevel", method= RequestMethod.POST)
    public MenuUI updateMenuAccessLevel(@RequestBody MenuUI menuUI) throws Exception {
		try {
			return menuService.updateMenuAccessLevel(menuUI);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("updateMenuAccessLevel",e);
			return menuUI;
		}
        
        
    }
	
	@RequestMapping(value="/saveMenuAllAccessLevel", method= RequestMethod.POST)
    public String saveMenuAllAccessLevel(@RequestBody List<MenuAccessUI> list) throws Exception {
		try {
			logger.info("saveMenuAllAccessLevel list size:"+list.size());
			return menuService.saveMenuAllAccessLevel(list);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("saveMenuAllAccessLevel",e);
			return "";
		}
        
        
    }
	
	@RequestMapping(value="/getMenuAccesses/{menuId}", method= RequestMethod.GET)
	public List<MenuAccessUI> getMenuAccesses(@PathVariable long menuId) throws Exception{
		try {	
						
				return menuService.getMenuAccessesById(menuId);
				
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			return null;
		}
	}
	

}
