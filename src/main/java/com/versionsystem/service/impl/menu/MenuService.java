package com.versionsystem.service.impl.menu;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QueryDslUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.BooleanExpression;
import com.versionsystem.common.ApplicationException;
import com.versionsystem.common.BeanUtils;
import com.versionsystem.common.BooleanExpressionUtils;
import com.versionsystem.common.FilterRequest;
import com.versionsystem.common.ResponseMap;
import com.versionsystem.persistence.model.AppParaName;
import com.versionsystem.persistence.model.LastestMenuList;
import com.versionsystem.persistence.model.MenusCtrl;
import com.versionsystem.persistence.model.MenusCtrlAccess;
import com.versionsystem.persistence.model.MenusCtrlLocale;
//import com.versionsystem.persistence.model.QMenusCtrl;
//import com.versionsystem.persistence.model.QUserId;
import com.versionsystem.persistence.model.UserId;
import com.versionsystem.service.impl.UserService;
import com.versionsystem.service.repo.UserRepository;
import com.versionsystem.service.repo.menu.AppParaNameRepository;
import com.versionsystem.service.repo.menu.LastestMenuListRepository;
import com.versionsystem.service.repo.menu.MenusCtrlAccessRepository;
import com.versionsystem.service.repo.menu.MenusCtrlLocaleRepository;
import com.versionsystem.service.repo.menu.MenusCtrlRepository;
import com.versionsystem.web.model.menu.LastestMenuListUI;
import com.versionsystem.web.model.menu.MenuAccessUI;
import com.versionsystem.web.model.menu.MenuUI;
import com.versionsystem.web.model.role.RoleUI;

@Service
@Transactional
public class MenuService {
	
	

	@Autowired
	private MenusCtrlRepository repository;
	@Autowired
	private LastestMenuListRepository lastmenuRepository;
	@Autowired
	private MenusCtrlLocaleRepository localeRepository;
	@Autowired
	private MenusCtrlAccessRepository accessRepository;
	@Autowired
	private AppParaNameRepository paraNameRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private ResponseMap<MenuUI> extJS;
	private Logger logger = Logger.getLogger(MenuService.class);
	public  Map<String, Object>  findAll() {
		String locale=userService.getCurrentLocale();
		
		List<MenuUI> rl=new ArrayList<MenuUI>();
	
		List<MenusCtrl> l=repository.findAll(new Sort(Sort.Direction.ASC,"displaySeqNo"));
		MenuUI vo = null;
		for(MenusCtrl o:l) {
			vo = new MenuUI();
			vo.setCls("folder");
			vo.setId(o.getId());
			vo.setHasChildren("Y".equals(o.getLeaf())?false:true);	
			vo.setSeqNo(o.getSeqNo());
			vo.setOrder(o.getDispalySeqNo().intValue());
			vo.setSysRole(o.getSysRole());
			vo.setWidget(o.getMenuName());
			vo.setAccessLock(o.getAccessLock());
			//get Admin menuId
			List<MenusCtrl> templist=this.repository.findBySeqNoAndSysRole(o.getSeqNo(),"ADMIN");
			if(templist.size()>0)
				vo.setText(this.localeRepository.findByMenusCtrlIdAndLocale(templist.get(0).getId(), locale).getFormLabel());
			boolean existed=false;
			for(MenuUI temp:rl) {
				if(temp.isHasChildren() && vo.getSeqNo().indexOf(temp.getSeqNo())>=0 && !vo.isHasChildren()) {
					//vo.setId(temp.getId()+"/"+String.valueOf(o.getId()));
					temp.getItems().add(vo);
					temp.setExpanded(true);
					existed=true;
					break;
				}
			}
			if(!existed) {
				if(vo.isHasChildren() && vo.getItems()==null)
					vo.setItems(new ArrayList<MenuUI>());
				rl.add(vo);
			}
		}
		
		return extJS.mapOK(rl);
	}
	/**
	 * please set displayseqno=1 for all level 1 menus(parent menu)
	 * 
	 */
	public  List<MenuUI>  findTreeMenu(boolean forRole,String roleId) {
		String locale=userService.getCurrentLocale();
		String role=userService.getCurrentRole();
		List<MenuUI> rl=new ArrayList<MenuUI>();
		if(!forRole){
			//get menu list for login user
			List<MenusCtrl> l=repository.findBySysRoleOrderByDispalySeqNoAsc(role);
			MenuUI vo = null;
			for(MenusCtrl o:l) {
				vo = new MenuUI();
				vo.setCls("folder");
				vo.setId(o.getId());
				vo.setHasChildren("Y".equals(o.getLeaf())?false:true);	
				if(vo.isHasChildren())
					vo.setExpanded(true);
				vo.setSeqNo(o.getSeqNo());
				vo.setOrder(o.getDispalySeqNo().intValue());
				vo.setSysRole(o.getSysRole());
				vo.setWidget(o.getMenuName());
				vo.setAccessLock(o.getAccessLock());
				vo.setAllowedAction(o.getAllowedAction());
				vo.setChecked(true);
				if(o.getMenusCtrlAccesses()!=null && o.getMenusCtrlAccesses().size()>0){
					vo.setOtherActions(new ArrayList<MenuAccessUI>());
					MenuAccessUI mui=null;
					for(MenusCtrlAccess mca:o.getMenusCtrlAccesses()){
						mui = new MenuAccessUI();
						mui.setParameterKey(mca.getParameterKey());
						mui.setParameterValue(mca.getParameterValue());
						vo.getOtherActions().add(mui);
					}
				}
				//get Admin menuId
				List<MenusCtrl> templist=this.repository.findBySeqNoAndSysRole(o.getSeqNo(),"ADMIN");
				if(templist.size()>0)
					vo.setText(this.localeRepository.findByMenusCtrlIdAndLocale(templist.get(0).getId(), locale).getFormLabel());
				
				if(vo.getItems()==null)
					vo.setItems(new ArrayList<MenuUI>());
				boolean existed=false;
				for(MenuUI temp:rl) {
					
					if(vo.getSeqNo().length()==4){
						if( vo.getSeqNo().substring(0, 2).equals(temp.getSeqNo()) ) {
							//vo.setId(temp.getId()+"/"+String.valueOf(o.getId()));
							
							temp.getItems().add(vo);
							
							existed=true;
							break;
						}
					}
					else if(vo.getSeqNo().length()==6){
						if( vo.getSeqNo().substring(0, 2).equals(temp.getSeqNo())) {
							//vo.setId(temp.getId()+"/"+String.valueOf(o.getId()));
							for(MenuUI sub:temp.getItems()){
								if(vo.getSeqNo().substring(0, 4).equals(sub.getSeqNo())){
									sub.getItems().add(vo);
									existed=true;
									break;
								}
							}						
							
						}
					}
					else if(vo.getSeqNo().length()==8){
						if( vo.getSeqNo().substring(0, 2).equals(temp.getSeqNo())) {
							//vo.setId(temp.getId()+"/"+String.valueOf(o.getId()));
							for(MenuUI sub:temp.getItems()){
								if(vo.getSeqNo().substring(0, 4).equals(sub.getSeqNo())){
									for(MenuUI sub2:sub.getItems()){
										if(vo.getSeqNo().substring(0, 6).equals(sub2.getSeqNo())){
											sub2.getItems().add(vo);
											existed=true;
											break;
										}
									}		
								}
							}						
							
						}
					}
				}
				if(!existed) {
					
					rl.add(vo);
				}
			}
		}else{
			//get system menus for role maintaining
			List<MenusCtrl> l=repository.findBySysRoleOrderByDispalySeqNoAsc("ADMIN");
			System.out.println("admin menu list:"+l.size());
			//get user menus,use roleId NOT current role
			List<MenusCtrl> l2=repository.findBySysRoleOrderByDispalySeqNoAsc(roleId);
			System.out.println("test menu list:"+l2.size());
			MenuUI vo = null;
			for(MenusCtrl o:l) {
				vo = new MenuUI();
				vo.setCls("folder");
				vo.setId(o.getId());
				vo.setHasChildren("Y".equals(o.getLeaf())?false:true);	
				if(vo.isHasChildren())
					vo.setExpanded(true);
				vo.setSeqNo(o.getSeqNo());
				vo.setOrder(o.getDispalySeqNo().intValue());
				vo.setSysRole(o.getSysRole());
				vo.setWidget(o.getMenuName());
				vo.setAccessLock(o.getAccessLock());
				
				if(o.getMenusCtrlAccesses()!=null && o.getMenusCtrlAccesses().size()>0){
					vo.setOtherActions(new ArrayList<MenuAccessUI>());
					MenuAccessUI mui=null;
					for(MenusCtrlAccess mca:o.getMenusCtrlAccesses()){
						mui = new MenuAccessUI();
						mui.setParameterKey(mca.getParameterKey());
						mui.setParameterValue(mca.getParameterValue());
						vo.getOtherActions().add(mui);
					}
				}
				
				
				for(MenusCtrl temp:l2){
					if(temp.getSeqNo().equals(vo.getSeqNo())){
						vo.setChecked(true);
						vo.setRoleMenuId(temp.getId());
						vo.setAllowedAction(temp.getAllowedAction());
						if(temp.getAllowedAction()==null)
							vo.setAllowedAction("N");
						break;
					}
				}
				//get Admin menuId
				List<MenusCtrl> templist=this.repository.findBySeqNoAndSysRole(o.getSeqNo(),"ADMIN");
				if(templist.size()>0){
					logger.info("templist.get(0).getId()="+templist.get(0).getId()+" seqNo:"+templist.get(0).getSeqNo());
					logger.info("locale"+locale);
					vo.setText(this.localeRepository.findByMenusCtrlIdAndLocale(templist.get(0).getId(), locale).getFormLabel());
					if(vo.isChecked() && !vo.isHasChildren())
						vo.setText(vo.getText()+"--["+(vo.getAllowedAction().equals("Y")?"Edit":"View")+"]");
				}
				if(vo.getItems()==null)
					vo.setItems(new ArrayList<MenuUI>());
				boolean existed=false;
				for(MenuUI temp:rl) {
					
					if(vo.getSeqNo().length()==4){
						if( vo.getSeqNo().substring(0, 2).equals(temp.getSeqNo()) ) {
							//vo.setId(temp.getId()+"/"+String.valueOf(o.getId()));
							
							temp.getItems().add(vo);
							
							existed=true;
							break;
						}
					}
					else if(vo.getSeqNo().length()==6){
						if( vo.getSeqNo().substring(0, 2).equals(temp.getSeqNo())) {
							//vo.setId(temp.getId()+"/"+String.valueOf(o.getId()));
							for(MenuUI sub:temp.getItems()){
								if(vo.getSeqNo().substring(0, 4).equals(sub.getSeqNo())){
									sub.getItems().add(vo);
									existed=true;
									break;
								}
							}						
							
						}
					}
					else if(vo.getSeqNo().length()==8){
						if( vo.getSeqNo().substring(0, 2).equals(temp.getSeqNo())) {
							//vo.setId(temp.getId()+"/"+String.valueOf(o.getId()));
							for(MenuUI sub:temp.getItems()){
								if(vo.getSeqNo().substring(0, 4).equals(sub.getSeqNo())){
									for(MenuUI sub2:sub.getItems()){
										if(vo.getSeqNo().substring(0, 6).equals(sub2.getSeqNo())){
											sub2.getItems().add(vo);
											existed=true;
											break;
										}
									}		
								}
							}						
							
						}
					}
				}
				if(!existed) {
					rl.add(vo);
				}
			}
		}
		
		return rl;
	}
	public  Map<String, Object>  findAllBySeqNo(String seqNo) {
		String locale=userService.getCurrentLocale();
		List<MenuUI> rl=new ArrayList<MenuUI>();
	
		List<MenusCtrl> l=repository.findBySeqNoStartingWith(seqNo);
		MenuUI vo = null;
		for(MenusCtrl o:l) {
			if(o.getSeqNo().length()>2) {
			vo = new MenuUI();
			vo.setCls("folder");
			vo.setId(o.getId());
			vo.setHasChildren("Y".equals(o.getLeaf())?true:false);	
			vo.setSeqNo(o.getSeqNo());
			vo.setOrder(o.getDispalySeqNo().intValue());
			vo.setSysRole(o.getSysRole());
			vo.setWidget(o.getMenuName());
			vo.setAccessLock(o.getAccessLock());
			vo.setAllowedAction(o.getAllowedAction());
			//get Admin menuId
			List<MenusCtrl> templist=this.repository.findBySeqNoAndSysRole(o.getSeqNo(),"ADMIN");
			if(templist.size()>0)
				vo.setText(this.localeRepository.findByMenusCtrlIdAndLocale(templist.get(0).getId(), locale).getFormLabel());
			
			rl.add(vo);
			}
			
		}
		
		return extJS.mapOK(rl);
	}
	
	public boolean saveLastestMenuList(List<LastestMenuListUI> list){
		//f(list.size()>0){
		
			//this.lastmenuRepository.deleteLastestMenu(list.get(0).getUserId());
			//System.out.println("userid :"+list.get(0).getUserId());
			List<LastestMenuList> l=this.lastmenuRepository.findByUserIdOrderByDisplaySeqAsc(list.get(0).getUserId());
			for(LastestMenuList o:l){
				this.lastmenuRepository.delete(o);
				//System.out.println("delete:"+o.getMenuId());
			}
			int seq=1;
			for(LastestMenuListUI vo:list){			 				
				LastestMenuList  o=new LastestMenuList();
				o.setMenuId(vo.getMenuId());
				o.setUserId(vo.getUserId());
				o.setDisplaySeq(new BigDecimal(seq));
				
				lastmenuRepository.saveAndFlush(o);
				seq++;
			}
		//}
		
		return true;
	}
	
	public boolean saveRoleMenuList(String roleId,String menuIds){
		
		try{
		String currentUser=this.userService.getCurrentUser();
		
		List<MenusCtrl> mlist=this.repository.findBySysRoleOrderByDispalySeqNoAsc(roleId);
		List<LastestMenuList> l=null;
		for(MenusCtrl temp:mlist){
			l=this.lastmenuRepository.findByMenuId(temp.getId());
			for(LastestMenuList o:l){
				this.lastmenuRepository.delete(o);
				//System.out.println("delete:"+o.getMenuId());
			}
			lastmenuRepository.flush();
			if(temp.getMenusCtrlAccesses()!=null){
				for(MenusCtrlAccess mca:temp.getMenusCtrlAccesses()){
					accessRepository.delete(mca);
				}
			}
			accessRepository.flush();
			this.repository.delete(temp);
			logger.info("delete menu id="+temp.getId()+" role="+temp.getSysRole()+" seqNo="+temp.getSeqNo());;
		}
		this.repository.flush();
		
		String[] ids=menuIds.split(",");
		//System.out.println("role:"+roleId+" old size:"+mlist.size()+" new list="+menuIds);
		for(int i=0;i<ids.length;i++){
			MenusCtrl o=new MenusCtrl();
			MenusCtrl adminMenus=this.repository.findOne(Long.parseLong(ids[i]));
			//BeanUtils.copyProperties(adminMenus, o);
			//o.setId(0l);
			o.setDispalySeqNo(adminMenus.getDispalySeqNo());
			o.setLeaf(adminMenus.getLeaf());
			o.setMenuName(adminMenus.getMenuName());
			o.setSeqNo(adminMenus.getSeqNo());
			o.setSysRole(roleId);
			o.setCreateDate(new Timestamp(new Date().getTime()));
			o.setLastUpdateDate(o.getCreateDate());
			o.setCreateUser(currentUser);
			o.setAllowedAction(adminMenus.getAllowedAction());
			this.repository.saveAndFlush(o);
			
		}
		
		return true;
		}catch(Exception e){
			
			logger.error("error",e);
			throw new ApplicationException(e.getMessage());
		}
	}
	public List<LastestMenuListUI> findLastestMenuListForUser(){
		String locale=userService.getCurrentLocale();
		String userId=userService.getCurrentUser();		
		LastestMenuListUI vo=null;
		List<LastestMenuListUI> rl=new ArrayList<LastestMenuListUI>();
		List<LastestMenuList> l=this.lastmenuRepository.findByUserIdOrderByDisplaySeqAsc(userId);
		for(LastestMenuList o :l){
			vo = new LastestMenuListUI();
			vo.setId(o.getId());
			vo.setMenuId(o.getMenuId());
			vo.setUserId(o.getUserId());
			vo.setSpriteCssClass("fa fa-paper-plane");
			
			MenusCtrl m=this.repository.findOne(vo.getMenuId());
			if(m!=null){
				vo.setUrl(m.getMenuName());
				vo.setAllowedAction(m.getAllowedAction());
				vo.setSysRole(m.getSysRole());
				//get Admin menuId
				MenusCtrl mc=this.repository.findOne(o.getMenuId());
				List<MenusCtrl> templist=this.repository.findBySeqNoAndSysRole(mc.getSeqNo(),"ADMIN");
				if(templist.size()>0)
					vo.setText(this.localeRepository.findByMenusCtrlIdAndLocale(templist.get(0).getId(), locale).getFormLabel());
			}
			rl.add(vo);
		}
		
		
		return rl;
	}
	
	public List<MenuUI> readSystemMenuLabel(){
		List<MenuUI> rl=new ArrayList<MenuUI>();
		String locale=this.userService.getCurrentLocale();
		//get system menus for role maintaining
		List<MenusCtrl> l=repository.findBySysRoleOrderByDispalySeqNoAsc("ADMIN");
		
		MenuUI vo = null;
		for(MenusCtrl o:l) {
			vo = new MenuUI();		
			vo.setId(o.getId());
			for(MenusCtrlLocale temp:o.getMenusCtrlLocales()){
				if(temp.getLocale().equals("en-US"))
					vo.setText(temp.getFormLabel());
				else if(temp.getLocale().equals("zh-HK"))
					vo.setHkText(temp.getFormLabel());
				else if(temp.getLocale().equals("zh-CN"))
					vo.setCnText(temp.getFormLabel());
			}
			rl.add(vo);
			
		}
		return rl;
	}
	
	public MenuUI updateSystemMenuLabel(MenuUI vo){
		
		List<MenusCtrlLocale> list=this.localeRepository.findByMenusCtrlId(vo.getId());
		for(MenusCtrlLocale o:list){
			if(o.getLocale().equals("en-US"))
				o.setFormLabel(vo.getText());
			else if(o.getLocale().equals("zh-HK"))
				o.setFormLabel(vo.getHkText());
			else if(o.getLocale().equals("zh-CN"))
				o.setFormLabel(vo.getCnText());
			this.localeRepository.saveAndFlush(o);
		}
		
		return vo;
	}
	
	
	public MenuUI updateMenuAccessLevel(MenuUI vo){
		MenusCtrl mc=null;
		MenusCtrl adminmc=this.repository.findOne(vo.getId());
		List<MenusCtrl> templist=this.repository.findBySeqNoAndSysRole(vo.getSeqNo(),vo.getSysRole());
		if(templist.size()>0){
			mc=templist.get(0);
			mc.setAllowedAction(vo.getAllowedAction());
			mc.setLastUpdateDate(new Timestamp(new Date().getTime()));
			mc.setLastUpdateUser(this.userService.getCurrentUser());
			this.repository.saveAndFlush(mc);
			if(adminmc!=null)
				vo.setText(this.localeRepository.findByMenusCtrlIdAndLocale(adminmc.getId(), this.userService.getCurrentLocale()).getFormLabel());
			if(vo.isChecked() && !vo.isHasChildren())
				vo.setText(vo.getText()+"--["+(vo.getAllowedAction().equals("Y")?"Edit":"View")+"]");
		}
		
		
		return  vo;
	}
	
	public String saveMenuAllAccessLevel(List<MenuAccessUI> list){
		String user=this.userService.getCurrentUser();
		String accessText=null;
		for(MenuAccessUI mu:list){
			
			MenusCtrlAccess ma=this.accessRepository.findOne(mu.getId());
			if(ma==null){
				ma=new MenusCtrlAccess();
				ma.setCreateDate(new Timestamp(new Date().getTime()));
				ma.setCreateUser(user);
				MenusCtrl mc=this.repository.findOne(mu.getMenuId());
				ma.setMenusCtrl(mc);
				ma.setParameterKey(mu.getParameterKey());
				if(mu.getTicked())
					ma.setParameterValue("Y");
				else
					ma.setParameterValue("N");
				ma.setLastUpdateDate(ma.getCreateDate());
				ma.setLastUpdateUser(user);
				this.accessRepository.save(ma);
				if("EditView".equals(ma.getParameterKey())){
					if(mc!=null)
						mc.setAllowedAction(ma.getParameterValue());
				}
					
				
			}else{
				//ma.setParameterKey(mu.getParameterKey());
				if(mu.getTicked())
					ma.setParameterValue("Y");
				else
					ma.setParameterValue("N");
				ma.setLastUpdateDate(ma.getCreateDate());
				ma.setLastUpdateUser(user);
				this.accessRepository.saveAndFlush(ma);
				MenusCtrl mc=this.repository.findOne(mu.getMenuId());
				if("EditView".equals(ma.getParameterKey())){
					if(mc!=null)
						mc.setAllowedAction(ma.getParameterValue());
				}
			}
			if(mu.getTicked())
				accessText+=mu.getParameterKey()+"|";
		}
		
		return accessText;
	}
	
	private String getParaName(String key,String locale){
		AppParaName apn=this.paraNameRepository.findByParameterKey(key);
		if(apn!=null){
			if("en-US".equals(locale))
				return apn.getName();
			else if("zh-CN".equals(locale))
				return apn.getNameL3();
			else
				return apn.getNameL2();
		}
		return null;
	}
	
	public List<MenuAccessUI> getMenuAccessesById(long id){
		List<MenusCtrlAccess> l = this.accessRepository.findByMenusCtrlIdOrderByParameterKeyAsc(id);
		List<MenuAccessUI> rl=new ArrayList<MenuAccessUI>();
		MenuAccessUI vo=null;
		String locale=this.userService.getCurrentLocale();
		boolean excel = false,edit=false;
		for(MenusCtrlAccess o:l){
			vo = new MenuAccessUI();
			vo.setId(o.getId());
			vo.setParameterKey(o.getParameterKey());
			vo.setParameterName(this.getParaName(vo.getParameterKey(), locale));
			vo.setParameterValue(o.getParameterValue());
			vo.setMenuId(o.getMenusCtrl().getId());
			if("Y".equals(vo.getParameterValue()))
				vo.setTicked(true);
			else
				vo.setTicked(false);
			rl.add(vo);
			if(o.getParameterKey().equals("ExportExcel")){
				excel=true;
			}
			if(o.getParameterKey().equals("EditView")){
				edit=true;
			}
		}
		if(!edit){
			vo = new MenuAccessUI();
			vo.setId(-1l);
			vo.setParameterKey("EditView");
			vo.setParameterName(this.getParaName(vo.getParameterKey(), locale));
			vo.setParameterValue("Y");
			if("Y".equals(vo.getParameterValue()))
				vo.setTicked(true);
			else
				vo.setTicked(false);
			vo.setMenuId(id);
			rl.add(vo);
		}
		if(!excel){
			vo = new MenuAccessUI();
			vo.setId(-1l);
			vo.setParameterKey("ExportExcel");
			vo.setParameterName(this.getParaName(vo.getParameterKey(), locale));
			vo.setParameterValue("N");
			vo.setMenuId(id);
			if("Y".equals(vo.getParameterKey()))
				vo.setTicked(true);
			else
				vo.setTicked(false);
			rl.add(vo);
		}
		
		return rl;
	}
	
	
	
	public Map<String, Object> findAll(Pageable pageable, MenuUI filter) {
		List<MenuUI> rl=new ArrayList<MenuUI>();
		Predicate predicate = toPredicate(filter);
		Page<MenusCtrl> pages=repository.findAll(predicate, pageable);
		List<MenusCtrl> l=pages.getContent();
		MenuUI vo = null;
		for(MenusCtrl o:l) {
			vo = new MenuUI();
			vo.setCls("folder");
			vo.setId((o.getId()));
			vo.setHasChildren("Y".equals(o.getLeaf())?false:true);	
			vo.setText(o.getMenuName());
			vo.setSeqNo(o.getSeqNo());
			
			//vo.setText(o.getFormDesc());
			rl.add(vo);
		}

		return extJS.mapOK(rl);
				
	}
	private Predicate toPredicate(MenuUI filter) {
		logger.info("Entering predicates :: " + filter);
		//QUserId user = QUserId.userId1;
		BooleanExpression result = null;
		//QMenusCtrl menu=QMenusCtrl.menusCtrl;
		try {
			BooleanExpression expression = null;
			/*if(filter.getSeqNo()!=null && !"".equals(filter.getSeqNo())) {
				expression = menu.seqNo.like("%"+filter.getSeqNo()+"%");
				result=BooleanExpressionUtils.and(result, expression);
			}
			if(filter.getSysRole()!=null && !"".equals(filter.getSysRole())) {
				expression = menu.sysRole.like("%"+filter.getSysRole()+"%");
				result=BooleanExpressionUtils.and(result, expression);
			}
			if(filter.getText()!=null && !"".equals(filter.getText())) {
				expression = menu.menuName.like("%"+filter.getText()+"%");
				result=BooleanExpressionUtils.and(result, expression);
			}*/
			
		} catch (Exception ex) {
			logger.error(ex);
		}
		
		logger.info("Exiting predicates");
		return result;
	}

}
