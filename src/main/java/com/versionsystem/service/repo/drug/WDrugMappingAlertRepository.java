package com.versionsystem.service.repo.drug;



import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import com.versionsystem.persistence.model.WDrugMappingAlert;

public interface WDrugMappingAlertRepository extends JpaRepository<WDrugMappingAlert, Long>,QueryDslPredicateExecutor<WDrugMappingAlert> {

	WDrugMappingAlert findByOrgCodeAndDrugBrandAndDrugCode(String orgCode,String drugBrand,String drugCode);
	WDrugMappingAlert findByOrgCodeAndDrugBrandIsNullAndDrugCode(String orgCode,String drugCode);
	

	/*
	@Query("select u from userid u where u.role.role = :role")
	Page<UserId> findByRole(@Param("role") Integer role, Pageable pageable);*/
}
