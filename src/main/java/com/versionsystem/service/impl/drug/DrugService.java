package com.versionsystem.service.impl.drug;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.versionsystem.common.ApplicationParas;
import com.versionsystem.common.BeanUtils;
import com.versionsystem.common.ObjectConverter;
import com.versionsystem.common.ResponseMap;
import com.versionsystem.persistence.model.WAccessServiceLog;
import com.versionsystem.persistence.model.WDrugAppForm;
import com.versionsystem.persistence.model.WDrugAppFormLog;
import com.versionsystem.persistence.model.WDrugMapping;
import com.versionsystem.persistence.model.WDrugMappingAlert;
import com.versionsystem.persistence.model.WSystemMessage;
import com.versionsystem.persistence.model.WWebServiceKey;
import com.versionsystem.service.ConfigService;
import com.versionsystem.service.repo.drug.WAccessServiceLogRepository;
import com.versionsystem.service.repo.drug.WDrugAppFormLogRepository;
import com.versionsystem.service.repo.drug.WDrugAppFormRepository;
import com.versionsystem.service.repo.drug.WDrugListRepository;
import com.versionsystem.service.repo.drug.WDrugMappingAlertRepository;
import com.versionsystem.service.repo.drug.WDrugMappingRepository;
import com.versionsystem.service.repo.drug.WSystemMessageRepository;
import com.versionsystem.service.repo.drug.WWebServiceKeyRepository;
import com.versionsystem.web.model.drug.ClientDrugFormItem;
import com.versionsystem.web.model.drug.WDrugAppFormUI;


@Service
public class DrugService {
	
	@Autowired
	WWebServiceKeyRepository webServiceKeyRepository;
	@Autowired
	WSystemMessageRepository systemMessageRepository;
	@Autowired
	WAccessServiceLogRepository acessServiceLogRepository;
	@Autowired
	WDrugAppFormRepository  drugAppFormRepository;
	@Autowired
	WDrugAppFormLogRepository  drugAppFormLogRepository;
	@Autowired
	private ConfigService configService;
	@Autowired
	WDrugMappingRepository drugMappingRepository ;
	@Autowired
	WDrugMappingAlertRepository drugMappingAlertRepository ;
	@Autowired
	WDrugListRepository drugListRepository;
	
	private Logger logger = LoggerFactory.getLogger(DrugService.class); 
	

	public Map<String,Object> getDrugList(String orgCode,String voucherNo,String incurredDateStr){
		ResponseMap<ClientDrugFormItem> rm=new ResponseMap<ClientDrugFormItem>();
		List<String> errorMessage=new ArrayList<String>();
		if(orgCode==null ||"".equals(orgCode))
			errorMessage.add(getErrorMessage("DP-10002","orgCode"));//required
		if(voucherNo==null ||"".equals(voucherNo))
			errorMessage.add(getErrorMessage("DP-10002","voucherNo"));//required		
		if(incurredDateStr==null ||"".equals(incurredDateStr))
			errorMessage.add(getErrorMessage("DP-10002","incurredDate"));//required
		else{
			//validate date
			String format=configService.getProperty(ApplicationParas.APP_SERVICE_DATEFORMAT);
			if(!ObjectConverter.isValidDate(incurredDateStr, format))
				errorMessage.add(getErrorMessage("DP-10004","incurredDate(value:"+incurredDateStr+")")+" The right format is '"+format+"'!");//invalid date
		}
		if(errorMessage.size()>0)
			return rm.mapError(errorMessage);
		else{
			List<WDrugAppForm> drugs = drugAppFormRepository.findByOrgCodeAndVoucherNoAndIncurredDate(orgCode, voucherNo,ObjectConverter.toDate(incurredDateStr, configService.getProperty(ApplicationParas.APP_SERVICE_DATEFORMAT)));
			List<ClientDrugFormItem> data=new ArrayList<ClientDrugFormItem>();
			ClientDrugFormItem item=null;
			for(WDrugAppForm o:drugs){
				item=new ClientDrugFormItem();
				BeanUtils.copyProperties(o, item);
				data.add(item);
			}
			ResponseMap<ClientDrugFormItem> map=new ResponseMap<ClientDrugFormItem>();
			return map.mapForDrug(data, data.size()>0?"Data found!":"No data found!", orgCode, voucherNo, incurredDateStr, "", "");
		}
		
	}
	
	
	private void saveServiceAccessLog(String orgCode,String apiKey,String actionIp){
		WAccessServiceLog log=new WAccessServiceLog();
		log.setActionDate(new Date());
		log.setActionIp(actionIp);
		log.setOrgCode(orgCode);
		log.setServiceKey(apiKey);
		acessServiceLogRepository.saveAndFlush(log);
	}
	private WDrugMapping alertMapping(WDrugAppForm o){
		WDrugMapping wm=null;
		if(o.getDrugBrand()==null ||"".equals(o.getDrugBrand()))
			wm=drugMappingRepository.findByOrgCodeAndDrugBrandIsNullAndDrugCode(o.getOrgCode(), o.getDrugCode());
		else
			wm= drugMappingRepository.findByOrgCodeAndDrugBrandAndDrugCode(o.getOrgCode(), o.getDrugBrand(), o.getDrugCode());
		if(wm==null){
			WDrugMappingAlert wml= null;
			if(o.getDrugBrand()==null ||"".equals(o.getDrugBrand()))
				wml= drugMappingAlertRepository.findByOrgCodeAndDrugBrandIsNullAndDrugCode(o.getOrgCode(), o.getDrugCode());
			else
				wml= drugMappingAlertRepository.findByOrgCodeAndDrugBrandAndDrugCode(o.getOrgCode(), o.getDrugBrand(), o.getDrugCode());
			if(wml==null){
				wml=new WDrugMappingAlert();
				wml.setDrugBrand(o.getDrugBrand());
				wml.setDrugCode(o.getDrugCode());
				wml.setOrgCode(o.getOrgCode());
				wml.setDrugPrice(o.getDrugPrice());
				wml.setDrugUnit(o.getDrugUnit());
				wml.setDrugName(o.getDrugName());
				wml.setRequestBy(o.getRequestBy());
				wml.setRequestDate(o.getRequestDate());
				wml.setMapFlag(false);
				drugMappingAlertRepository.saveAndFlush(wml);
			}
		}
		return wm;
	}
	private String generateAppCode(String orgCode){
		Long l=this.drugAppFormRepository.getAppCode();
		StringBuffer sb=new StringBuffer();
		int length=Long.toString(l).length();
		for(int i=length;i<8;i++){
			sb.append("0");
		}
		return orgCode+sb.toString()+l;
	}
	@Transactional
	public Map<String,Object> saveDrugList(Map<String,Object> objects){
		try {
			String userId=objects.get("userId").toString();
			String orgCode=objects.get("orgCode").toString();
			String doctorCode=objects.get("doctorCode").toString();
			String voucherNo=objects.get("voucherNo").toString();
			Date incurredDate=ObjectConverter.toDate(objects.get("incurredDate"), configService.getProperty(ApplicationParas.APP_SERVICE_DATEFORMAT));			
			List<WDrugAppForm> drugs = drugAppFormRepository.findByOrgCodeAndVoucherNoAndIncurredDate(orgCode, voucherNo,incurredDate);
			WDrugAppFormLog log=null;
			Date now=new Date();
			if(drugs.size()>0){
				//insert these records to table wappdrugformlog ;delete these records from table wappdrugform
				for(WDrugAppForm o:drugs){
					log=new WDrugAppFormLog();
					BeanUtils.copyProperties(o, log);
					log.setActionBy(userId);
					log.setActionDate(now);
					log.setActionType("Delete");
					drugAppFormLogRepository.saveAndFlush(log);
					drugAppFormRepository.delete(o);
					drugAppFormRepository.flush();
				}			
			}
			List<WDrugAppFormUI> list=(List<WDrugAppFormUI>)objects.get("data");
			WDrugAppForm o=null;
			List<ClientDrugFormItem> data=new ArrayList<ClientDrugFormItem>();
			ClientDrugFormItem item=null;
			for(WDrugAppFormUI vo:list){
				o=new WDrugAppForm();
				BeanUtils.copyProperties(vo, o);
				o.setRequestBy(userId);
				o.setRequestDate(now);
				WDrugMapping wm=alertMapping(o);
				if(wm==null){				
					o.setAppPrice(o.getDrugPrice());				
				}
				else{
					
					BigDecimal standardPrice=null;
					if(wm.getMappingBrand()==null||"".equals(wm.getMappingBrand()))
						standardPrice=this.drugListRepository.findByDrugBrandIsNullAndDrugCode( wm.getDrugCode()).getDrugPrice();
					else
						standardPrice=this.drugListRepository.findByDrugBrandAndDrugCode(wm.getMappingBrand(), wm.getDrugCode()).getDrugPrice();
					if(standardPrice.doubleValue()<o.getDrugPrice().doubleValue())
						o.setAppPrice(standardPrice);
					else
						o.setAppPrice(o.getDrugPrice());
						
				}
				o.setAppDate(now);
				o.setAppCode(this.generateAppCode(o.getOrgCode()));
				
				drugAppFormRepository.saveAndFlush(o);		
				
				item=new ClientDrugFormItem();
				BeanUtils.copyProperties(vo, item);
				item.setAppCode(o.getAppCode());
				item.setAppDate(o.getAppDate());
				item.setAppPrice(o.getAppPrice());
				data.add(item);
				log=new WDrugAppFormLog();
				BeanUtils.copyProperties(o, log);
				log.setActionBy(userId);
				log.setActionDate(now);
				log.setActionType("Insert");
				drugAppFormLogRepository.saveAndFlush(log);
			}
			ResponseMap<ClientDrugFormItem> rm=new ResponseMap<ClientDrugFormItem>();
			return rm.mapForDrug(data, "Data saved successfully!", orgCode, voucherNo, objects.get("incurredDate").toString(), doctorCode, userId);
		} catch (Exception e) {
			logger.info("Error:",e);
			return new ResponseMap<Object>().mapError("Failed to save!");
		}
	}
	public Map<String,Object> checkDrugList(Map<String,Object> objects){
		try {
			//String userId=objects.get("userId").toString();
			String orgCode=objects.get("orgCode").toString();
			//String doctorCode=objects.get("doctorCode").toString();
			String voucherNo=objects.get("voucherNo").toString();
			Date incurredDate=ObjectConverter.toDate(objects.get("incurredDate"), configService.getProperty(ApplicationParas.APP_SERVICE_DATEFORMAT));			
			ClientDrugFormItem item=null;
			List<String> errorMessage=new ArrayList<String>();
			/*List<WDrugAppForm> drugs = drugAppFormRepository.findByOrgCodeAndVoucherNoAndIncurredDate(orgCode, voucherNo,incurredDate);
			
			List<ClientDrugFormItem> approvedDatas=null;
			if(drugs.size()>0){			
				for(WDrugAppForm o:drugs){
					item=new ClientDrugFormItem();
					BeanUtils.copyProperties(o, item);
				}			
			}*/
			List<WDrugAppFormUI> list=(List<WDrugAppFormUI>)objects.get("data");

			List<ClientDrugFormItem> data=new ArrayList<ClientDrugFormItem>();
			
			for(WDrugAppFormUI vo:list){
							
				item=new ClientDrugFormItem();
				BeanUtils.copyProperties(vo, item);
				WDrugAppForm o=this.drugAppFormRepository.findByOrgCodeAndVoucherNoAndIncurredDateAndAppCode(orgCode, voucherNo, incurredDate, vo.getAppCode());
				if(o==null){
					errorMessage.add(getErrorMessage("DP-20002","appCode(value:"+vo.getAppCode()+")"));//no data found
				}
				else{
					if(o.getAppPrice().doubleValue()!=item.getAppPrice() .doubleValue()){//price
						errorMessage.add("AppCode:"+vo.getAppCode()+",Drug price[ ="+item.getAppPrice().doubleValue()+"  ] is not equal to the price approved[ ="+o.getAppPrice().doubleValue()+"  ]");//
					}
					if(o.getDrugBrand()!=null && !o.getDrugBrand().equals(item.getDrugBrand())){
						errorMessage.add("AppCode:"+vo.getAppCode()+",Drug brand[ ="+item.getDrugBrand()+"  ] is not equal to the brand approved[ ="+o.getDrugBrand()+"  ]");//
					}
					if(!o.getDrugCode().equals(item.getDrugCode())){
						errorMessage.add("AppCode:"+vo.getAppCode()+",Drug code[ ="+item.getDrugCode()+"  ] is not equal to the code approved[ ="+o.getDrugCode()+"  ]");//
					}
					if(!o.getDrugUnit().equals(item.getDrugUnit())){
						errorMessage.add("AppCode:"+vo.getAppCode()+",Drug unit[ ="+item.getDrugUnit()+"  ] is not equal to the unit approved[ ="+o.getDrugUnit()+"  ]");//
					}
				}
				data.add(item);		
				
			}
			ResponseMap<ClientDrugFormItem> rm=new ResponseMap<ClientDrugFormItem>();
			return rm.mapForCheckDrug(data, errorMessage, orgCode, voucherNo, objects.get("incurredDate").toString());
		} catch (Exception e) {
			logger.info("Error:",e);
			return new ResponseMap<Object>().mapError("Failed to check!");
		}
	}
	public Map<String,Object> checkParasForDrug(Map<String,Object> model,String actionIp,boolean check){
		try {
			String orgCode=model.get("orgCode")==null?null:(String)model.get("orgCode");
			String apiKey=model.get("apiKey")==null?null:(String)model.get("apiKey");
			ResponseMap<WDrugAppFormUI> rm=new ResponseMap<WDrugAppFormUI>();
			if(orgCode==null || "".equals(orgCode) || apiKey==null || "".equals(apiKey)){
				return rm.mapError(getErrorMessage("DP-10002","orgCode or apiKey"));
			}
			else{
				saveServiceAccessLog(orgCode,apiKey,actionIp);
				WWebServiceKey key=webServiceKeyRepository.findByOrgCode(orgCode);	
				if(key==null || !key.getServiceKey().equals(apiKey)){
					//invalid api key		
					return rm.mapError(getErrorMessage("DP-10001","orgCode or apiKey"));
				}
				else{
					//true api key
					List<String> errorMessage=new ArrayList<String>();
					String userId=null,doctorCode=null;
					if(!check){
						userId=model.get("userId")==null?null:model.get("userId").toString();		
						if(userId==null ||"".equals(userId))
							errorMessage.add(getErrorMessage("DP-10002","userId"));//required
						doctorCode=model.get("doctorCode")==null?null:model.get("doctorCode").toString();
						if(doctorCode==null ||"".equals(doctorCode))
							errorMessage.add(getErrorMessage("DP-10002","doctorCode"));//required
					}
					String voucherNo=model.get("voucherNo")==null?null:model.get("voucherNo").toString();				
					if(voucherNo==null ||"".equals(voucherNo))
						errorMessage.add(getErrorMessage("DP-10002","voucherNo"));//required
					String incurredDateStr=model.get("incurredDate")==null?null:model.get("incurredDate").toString();	
					Date incurredDate=null;
					if(incurredDateStr==null ||"".equals(incurredDateStr))
						errorMessage.add(getErrorMessage("DP-10002","incurredDate"));//required
					else{
						//validate date
						String format=configService.getProperty(ApplicationParas.APP_SERVICE_DATEFORMAT);
						if(!ObjectConverter.isValidDate(incurredDateStr, format))
							errorMessage.add(getErrorMessage("DP-10004","incurredDate(value:"+incurredDateStr+")")+" The right format is '"+format+"'!");//invalid date
						else{
							incurredDate=ObjectConverter.toDate(incurredDateStr, format);
						}
					}
					if(errorMessage.size()>0)
						return rm.mapError(errorMessage.toString());
					else{
						List<String> invalidParaMsg=new ArrayList<String>();
						ArrayList<Map<String,Object>> drugList=(ArrayList<Map<String,Object>>)model.get("data");
						String drugCode=null,drugBrand=null,drugName=null,drugUnit=null,drugPriceStr=null,drugQtyStr=null,drugDurationStr=null,appCode=null,appPriceStr=null;			
						Date now=new Date();
						List<WDrugAppFormUI> drugForms=new ArrayList<WDrugAppFormUI>();
						WDrugAppFormUI vo=null;
						for(Map<String,Object> m:drugList){
							 drugCode = m.get("drugCode")==null?null:m.get("drugCode").toString();
							 if(drugCode==null ||"".equals(drugCode))
								 invalidParaMsg.add(getErrorMessage("DP-10002", "drugCode"));
							 drugBrand = m.get("drugBrand")==null?null:m.get("drugBrand").toString();
							// if(drugBrand==null||"".equals(drugBrand))
							//	 invalidParaMsg.add(getErrorMessage("DP-10002", "drugBrand"));
							 drugName = m.get("drugName")==null?null:m.get("drugName").toString();
							 if(drugName==null||"".equals(drugName))
								 invalidParaMsg.add(getErrorMessage("DP-10002", "drugName"));
							 drugUnit = m.get("drugUnit")==null?null:m.get("drugUnit").toString();
							 if(drugUnit==null||"".equals(drugUnit))
								 invalidParaMsg.add(getErrorMessage("DP-10002", "drugUnit"));
							 drugPriceStr = m.get("drugPrice")==null?null:m.get("drugPrice").toString();
							 if(drugPriceStr==null||"".equals(drugPriceStr))
								 invalidParaMsg.add(getErrorMessage("DP-10002", "drugPrice"));
							 else{
								 if(!ObjectConverter.isNumeric(drugPriceStr)){
									 invalidParaMsg.add(getErrorMessage("DP-10003", "drugPrice(value:"+drugPriceStr+")"));
								 }
							 }
							 if(!check){
								 drugQtyStr = m.get("drugQty")==null?null:m.get("drugQty").toString();
								 if(drugQtyStr==null||"".equals(drugQtyStr))
									 invalidParaMsg.add(getErrorMessage("DP-10002", "drugQty"));
								 else{
									 if(!ObjectConverter.isNumeric(drugQtyStr)){
										 invalidParaMsg.add(getErrorMessage("DP-10003", "drugQty(value:"+drugQtyStr+")"));
									 }
								 }
								 
								 drugDurationStr = m.get("drugDuration")==null?null:m.get("drugDuration").toString();
								 if(drugDurationStr==null||"".equals(drugDurationStr))
									 invalidParaMsg.add(getErrorMessage("DP-10002", "drugDuration"));
								 else{
									 if(!ObjectConverter.isNumeric(drugDurationStr)){
										 invalidParaMsg.add(getErrorMessage("DP-10003", "drugDuration(value:"+drugDurationStr+")"));
									 }
								 }
							 }
							 if(check){
								 appCode = m.get("appCode")==null?null:m.get("appCode").toString();
								 if(appCode==null ||"".equals(appCode))
									 invalidParaMsg.add(getErrorMessage("DP-10002", "appCode"));
								 appPriceStr = m.get("appPrice")==null?null:m.get("appPrice").toString();
								 if(appPriceStr==null||"".equals(appPriceStr))
									 invalidParaMsg.add(getErrorMessage("DP-10002", "appPrice"));
								 else{
									 if(!ObjectConverter.isNumeric(appPriceStr)){
										 invalidParaMsg.add(getErrorMessage("DP-10003", "appPrice(value:"+appPriceStr+")"));
									 }
								 }
							 }
							 if(invalidParaMsg.size()==0){
								 vo = new WDrugAppFormUI();
								 vo.setDrugBrand(drugBrand);
								 vo.setDrugCode(drugCode);
								 vo.setDrugUnit(drugUnit);
								 vo.setDrugName(drugName);
								 vo.setDrugDuration(ObjectConverter.toBigDecimal(drugDurationStr));
								 vo.setDrugQty(ObjectConverter.toBigDecimal(drugQtyStr));
								 vo.setDrugPrice(ObjectConverter.toBigDecimal(drugPriceStr));
								 vo.setRequestBy(userId);
								 vo.setRequestDate(now);
								 vo.setDoctorCode(doctorCode);
								 vo.setOrgCode(orgCode);
								 vo.setVoucherNo(voucherNo);
								 vo.setIncurredDate(incurredDate);
								 if(check){
									 vo.setAppCode(appCode);
									 vo.setAppPrice(ObjectConverter.toBigDecimal(appPriceStr));
								 }
								 drugForms.add(vo);
							 }					 
						}
						if(invalidParaMsg.size()==0){
							return rm.mapForDrug(drugForms, "Valid parameters", orgCode, voucherNo, incurredDateStr, doctorCode, userId);
						}
						else
							return rm.mapError(invalidParaMsg);
					}
					
				}
			}
		} catch (Exception e) {
			logger.info("Error:",e.getMessage());
			return new ResponseMap<Object>().mapError("Failed to parse!");
		}
		
		
		
	}
	
	private String getErrorMessage(String msgType,String paraName){
		
		WSystemMessage m=systemMessageRepository.findByMsgType(msgType);
		return msgType+" : ["+paraName+"] "+(m==null?"no data found":m.getMsgContent());
	}

}
