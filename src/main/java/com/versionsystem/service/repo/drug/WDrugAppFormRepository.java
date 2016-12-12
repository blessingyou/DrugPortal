package com.versionsystem.service.repo.drug;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import com.versionsystem.persistence.model.WDrugAppForm;

public interface WDrugAppFormRepository extends JpaRepository<WDrugAppForm, Long>,QueryDslPredicateExecutor<WDrugAppForm> {

	List<WDrugAppForm> findByOrgCodeAndVoucherNoAndIncurredDate(String orgCode,String voucherNo,Date incurredDate);

	WDrugAppForm findByOrgCodeAndVoucherNoAndIncurredDateAndDrugBrandAndDrugCode(String orgCode,String voucherNo,Date incurredDate,String drugBrank,String dugCode);
	WDrugAppForm findByOrgCodeAndVoucherNoAndIncurredDateAndAppCode(String orgCode,String voucherNo,Date incurredDate,String appCode);
	
	@Query(value="select APP_CODE_SEQ.nextval from dual",nativeQuery=true)
	Long getAppCode();
	
}
