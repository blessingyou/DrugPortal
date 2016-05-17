package com.versionsystem.service.repo.drug;



import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import com.versionsystem.persistence.model.WDrugList;

public interface WDrugListRepository extends JpaRepository<WDrugList, Long>,QueryDslPredicateExecutor<WDrugList> {

	WDrugList findByDrugBrandAndDrugCode(String drugBrand,String drugCode);
	WDrugList findByDrugBrandIsNullAndDrugCode(String drugCode);
	

}
