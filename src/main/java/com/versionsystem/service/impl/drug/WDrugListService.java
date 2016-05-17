package com.versionsystem.service.impl.drug;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.versionsystem.common.ApplicationError;
import com.versionsystem.common.ApplicationException;
import com.versionsystem.common.ApplicationParas;
import com.versionsystem.common.BeanUtils;
import com.versionsystem.common.ObjectConverter;
import com.versionsystem.persistence.model.WDrugList;
import com.versionsystem.service.ConfigService;
import com.versionsystem.service.impl.UserService;
import com.versionsystem.service.repo.drug.WDrugListRepository;
import com.versionsystem.web.model.drug.WDrugListUI;

@Service
public class WDrugListService {
	
	
	@Autowired
	private WDrugListRepository drugListRepository;
	
	@Autowired
	private UserService userService;
	@Autowired
	private ConfigService configService;
	
	
	
	private Logger logger = Logger.getLogger(WDrugListService.class);
	
	public List<WDrugListUI> findAll(){
		WDrugListUI vo=null;
		List<WDrugListUI> rl=new ArrayList<WDrugListUI>();
		List<WDrugList> list=this.drugListRepository.findAll();
		String locale=this.userService.getCurrentLocale();
		for(WDrugList o:list){
			vo=new WDrugListUI();
			BeanUtils.copyProperties(o,vo);
			//String jsonDateFormat=configService.getProperty(ApplicationParas.APP_JSONDATEFORMAT);
			//vo.setEffectiveFrom(ObjectConverter.toUTCDate2(o.getEffectiveFrom(),jsonDateFormat));
			rl.add(vo);
		}
		return rl;
	}
	
	
	
	
	@Transactional
	public boolean delete(long id){
		WDrugList o=this.drugListRepository.findOne(id);
		if(o!=null){
			this.drugListRepository.delete(o);
			logger.info(this.userService.getCurrentUser()+" delete a  record drug code="+o.getDrugName()+" on "+new Date());
			return true;
		}else
			throw new ApplicationException(ApplicationError.ObjectNotFoundException.toString());
			
	}
	
	@Transactional
	public long create(WDrugListUI temp){
				
				WDrugList o=new WDrugList();
				BeanUtils.copyProperties(temp, o);
				WDrugList saved=this.drugListRepository.saveAndFlush(o);
				return saved.getId();					
		
	}
	
	@Transactional
	public boolean update(WDrugListUI temp){
		
		
			WDrugList ci=this.drugListRepository.findOne(temp.getId());
			if(ci==null)
				throw new ApplicationException(ApplicationError.ObjectNotFoundException.toString()+" WDrugList"+temp.getId()+" not found");
			else{
				WDrugList o=this.drugListRepository.findOne(temp.getId());
				BeanUtils.copyProperties(temp, o);
				
				this.drugListRepository.saveAndFlush(o);
				return true;
			}
		
				
		
	}
	
	

}
