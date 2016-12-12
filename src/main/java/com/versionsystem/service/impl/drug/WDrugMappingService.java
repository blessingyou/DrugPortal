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
import com.versionsystem.persistence.model.WDrugMapping;
import com.versionsystem.service.ConfigService;
import com.versionsystem.service.impl.UserService;
import com.versionsystem.service.repo.drug.WDrugListRepository;
import com.versionsystem.service.repo.drug.WDrugMappingRepository;
import com.versionsystem.web.model.drug.WDrugMappingUI;

@Service
public class WDrugMappingService {
	
	
	@Autowired
	private WDrugMappingRepository drugMappingRepository;
	@Autowired
	private WDrugListRepository drugListRepository;
	
	@Autowired
	private UserService userService;
	@Autowired
	private ConfigService configService;
	
	
	
	private Logger logger = Logger.getLogger(WDrugMappingService.class);
	
	public List<WDrugMappingUI> findAll(){
		WDrugMappingUI vo=null;
		List<WDrugMappingUI> rl=new ArrayList<WDrugMappingUI>();
		List<WDrugMapping> list=this.drugMappingRepository.findAll();
		
		for(WDrugMapping o:list){
			vo=new WDrugMappingUI();
			BeanUtils.copyProperties(o,vo);
			WDrugList temp=null;
			if(o.getMappingBrand()==null){
				temp=this.drugListRepository.findByDrugBrandIsNullAndDrugCode(o.getMappingCode());
			}
			else
				temp=this.drugListRepository.findByDrugBrandAndDrugCode(o.getMappingBrand(), o.getMappingCode());
			vo.setMappingName(temp.getDrugName());
			vo.setMappingPrice(temp.getDrugPrice());
			vo.setMappingUnit(temp.getUnit());
			rl.add(vo);
			
		}
		return rl;
	}
	
	
	
	
	@Transactional
	public boolean delete(long id){
		WDrugMapping o=this.drugMappingRepository.findOne(id);
		if(o!=null){
			this.drugMappingRepository.delete(o);
			logger.info(this.userService.getCurrentUser()+" delete a  record WDrugMapping="+o.getDrugCode()+" on "+new Date());
			return true;
		}else
			throw new ApplicationException(ApplicationError.ObjectNotFoundException.toString());
			
	}
	
	@Transactional
	public long create(WDrugMappingUI temp){
				
				WDrugMapping o=new WDrugMapping();
				BeanUtils.copyProperties(temp, o);
				WDrugMapping saved=this.drugMappingRepository.saveAndFlush(o);
				return saved.getId();					
		
	}
	
	@Transactional
	public boolean update(WDrugMappingUI temp){
		
		
			WDrugMapping ci=this.drugMappingRepository.findOne(temp.getId());
			if(ci==null)
				throw new ApplicationException(ApplicationError.ObjectNotFoundException.toString()+" WDrugMapping"+temp.getId()+" not found");
			else{
				WDrugMapping o=this.drugMappingRepository.findOne(temp.getId());
				BeanUtils.copyProperties(temp, o);
				
				this.drugMappingRepository.saveAndFlush(o);
				return true;
			}
		
				
		
	}
	
	

}
