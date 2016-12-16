package com.versionsystem.service.repo.menu;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.versionsystem.persistence.model.AppParaName;

public interface AppParaNameRepository extends JpaRepository<AppParaName, Long>,QueryDslPredicateExecutor<AppParaName> {
	
	AppParaName findByParameterKey(String key);
	
}
