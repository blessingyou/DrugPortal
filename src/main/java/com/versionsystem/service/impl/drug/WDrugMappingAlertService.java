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
import com.versionsystem.persistence.model.WDrugMappingAlert;
import com.versionsystem.service.ConfigService;
import com.versionsystem.service.impl.UserService;
import com.versionsystem.service.repo.drug.WDrugListRepository;
import com.versionsystem.service.repo.drug.WDrugMappingAlertRepository;
import com.versionsystem.service.repo.drug.WDrugMappingRepository;
import com.versionsystem.web.model.drug.WDrugMappingAlertUI;

@Service
public class WDrugMappingAlertService {
	
	
	@Autowired
	private WDrugMappingAlertRepository drugMappingAlertRepository;
	
	@Autowired
	private WDrugMappingRepository drugMappingRepository;
	@Autowired
	private WDrugListRepository drugListRepository;
	
	@Autowired
	private UserService userService;
	@Autowired
	private ConfigService configService;
	
	
	
	private Logger logger = Logger.getLogger(WDrugMappingAlertService.class);
	
	public List<WDrugMappingAlertUI> findAll(){
		WDrugMappingAlertUI vo=null;
		List<WDrugMappingAlertUI> rl=new ArrayList<WDrugMappingAlertUI>();
		List<WDrugMappingAlert> list=this.drugMappingAlertRepository.findAll();
		
		for(WDrugMappingAlert o:list){
			vo=new WDrugMappingAlertUI();
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
		WDrugMappingAlert o=this.drugMappingAlertRepository.findOne(id);
		if(o!=null){
			this.drugMappingAlertRepository.delete(o);
			logger.info(this.userService.getCurrentUser()+" delete a  record WDrugMappingAlert="+o.getDrugCode()+" on "+new Date());
			return true;
		}else
			throw new ApplicationException(ApplicationError.ObjectNotFoundException.toString());
			
	}
	
	@Transactional
	public long create(WDrugMappingAlertUI temp){
				
				WDrugMappingAlert o=new WDrugMappingAlert();
				BeanUtils.copyProperties(temp, o);
				WDrugMappingAlert saved=this.drugMappingAlertRepository.saveAndFlush(o);
				return saved.getId();					
		
	}
	
	@Transactional
	public boolean update(WDrugMappingAlertUI temp){
		
		
			WDrugMappingAlert ci=this.drugMappingAlertRepository.findOne(temp.getId());
			if(ci==null)
				throw new ApplicationException(ApplicationError.ObjectNotFoundException.toString()+" WDrugMappingAlert"+temp.getId()+" not found");
			else{
				WDrugMappingAlert o=this.drugMappingAlertRepository.findOne(temp.getId());
				BeanUtils.copyProperties(temp, o);
				if(o.getMappingCode()!=null && o.getDrugUnit().equals(temp.getMappingUnit())){
					boolean saveMapping=false;
					if(o.getDrugBrand()==null && temp.getMappingBrand()==null)
						saveMapping=true;
					else if(o.getDrugBrand()!=null && temp.getMappingBrand()!=null && o.getDrugBrand().equals(temp.getMappingBrand())){
						saveMapping=true;
					}
					if(saveMapping){
						WDrugMapping map=new WDrugMapping();
						map.setDrugBrand(o.getDrugBrand());
						map.setDrugCode(o.getDrugCode());
						map.setOrgCode(o.getOrgCode());
						map.setMappingBrand(temp.getMappingBrand());
						map.setMappingCode(temp.getMappingCode());
						drugMappingRepository.saveAndFlush(map);
						o.setMapBy(this.userService.getCurrentUser());
						o.setMapDate(new Date());
						o.setMappingBrand(temp.getMappingBrand());
						o.setMappingCode(temp.getMappingCode());
						o.setMappingName(temp.getMappingName());
						o.setMappingPrice(temp.getMappingPrice());
						o.setMappingUnit(temp.getMappingUnit());
					
					}
					else
						throw new ApplicationException(ApplicationError.ParameterMappingException.toString());
				}
				this.drugMappingAlertRepository.saveAndFlush(o);
				return true;
			}
		
				
		
	}
	
	

}
